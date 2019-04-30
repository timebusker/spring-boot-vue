## Spring-Boot-Vue实战总结
## [我的blog地址：https://www.timebusker.top/，持续更新.....](https://timebusker.github.io/)

-----------------

<h2 align="center">:heart::heart::heart:如果觉得我的文章或者代码对您有帮助,可以请我喝杯咖啡哦:heart::heart::heart:</h2>
<div  align="center">    
  <img src="https://raw.githubusercontent.com/timebusker/timebusker.github.io/master/mine/wxpay.png?raw=true" width = "200" height = "200" alt="WXPAY" align=center />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://raw.githubusercontent.com/timebusker/timebusker.github.io/master/mine/alipay.png?raw=true" width = "200" height = "200" alt="ALIPAY" align=center />
</div>  
<h2 align="center">:smile::smile::smile:您的支持将鼓励我继续创作...谢谢!:smile::smile::smile:</h2>

-----------------

#### [lombok的使用]()

```
# 安装插件，新增maven插件
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.6</version>
</dependency>

@Getter/@Setter:     自动生成Getter/Setter方法 
@NoArgsConstructor:  自动生成无参数构造函数
@AllArgsConstructor: 自动生成全参数构造函数
@Data:               自动为所有字段添加@ToString, @EqualsAndHashCode, @Getter方法，为非final字段添加@Setter,和@RequiredArgsConstructor
@Cleanup:            自动帮我们调用close()方法
@NonNull:            可以帮助我们避免空指针
```

#### [通用Mapper快速使用](https://blog.csdn.net/isea533/article/details/83045335)

```
<!--mybatis通用Mapper-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
<!--mybatis-starter-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>
<!--分页控制-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.10</version>
    <exclusions>
        <exclusion>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

#### 使用PostgreSQL

```
# 安装并建库
CREATE DATABASE "minedb" WITH  OWNER = postgres ENCODING = 'UTF8' CONNECTION LIMIT = -1;
COMMENT ON DATABASE "spring-boot-vue" IS 'spring-boot-vue实战项目数据库';
# drop database minedb

# 创建表模式
create schema spring-boot-vue;

# 增加列
ALTER TABLE table_name ADD column_name datatype;
# 删除列
ALTER TABLE table_name DROP column_name;
# 更改列的数据类型
ALTER TABLE table_name ALTER column_name TYPE datatype;
# 表重命名
ALTER TABLE table_name RENAME TO new_name;
# 更改列的名字
ALTER TABLE table_name RENAME column_name to new_column_name;
# 字段的not null设置
ALTER TABLE table_name ALTER column_name {SET|DROP} NOT NULL;
# 给列添加default
ALTER TABLE table_name ALTER column_name SET DEFAULT expression;
```

#### Mybatis菜单管理查询级缓存使用

```aidl

```


#### 文件MD5值校验文件

从服务性能、技术上，文件MD5加密校验需要放在前端完成，减少数据传输和后端服务器压力。目前主流的采用`spark-md5`组件。

```
# 安装组件
npm install spark-md5 --save

# 引入组件
import {getFileMD5} from '../../../utils/FileUtil'

<el-upload
   :file-list="fileList"
   :auto-upload="false"
   action="/api/file/single-image"
   list-type="picture-card"
   :on-preview="handlePreview"
   :on-change="handleChange"
   :on-remove="handleRemove">
   <i class="el-icon-plus"></i>
</el-upload>

# 核心JS
handleChange (file, fileList) {
  let _this = this;
  getFileMD5(file, function (md5) {
    console.log("==============>", md5)
    let image = _this.queryImage(md5);
    if (image != undefined && JSON.stringify(image) != "{}") {
	  // 设置图片是否自动上传
      _this.autoUpload = false;
    } else {
	  // 设置图片是否自动上传
      _this.autoUpload = true;
    }
  })
},

# 获取MD5值
import SparkMD5 from 'spark-md5'

export function getFileMD5(file, callback) {
  let fileRaw = file.raw
  //声明必要的变量
  let fileReader = new FileReader()
  //文件每块分割2M，计算分割详情
  let chunkSize = 2097152
  let chunks = Math.ceil(file.size / chunkSize)
  let currentChunk = 0
  //创建md5对象（基于SparkMD5）
  let spark = new SparkMD5()
  //每块文件读取完毕之后的处理
  fileReader.onload = function (e) {
    //每块交由sparkMD5进行计算
    spark.appendBinary(e.target.result)
    currentChunk++
    //如果文件处理完成计算MD5，如果还有分片继续处理
    if (currentChunk < chunks) {
      loadNext()
    } else {
      callback(spark.end())
    }
  }
  //处理单片文件的上传
  function loadNext() {
    let start = currentChunk * chunkSize
    let end = start + chunkSize >= file.size ? file.size : start + chunkSize
    fileReader.readAsBinaryString(fileRaw.slice(start, end))
  }

  loadNext()
}
```

#### [Springboot项目与vue项目整合打包](https://www.cnblogs.com/kevinZhu/p/9931317.html)

在一些公司，部署实施人员的技术无法和互联网公司的运维团队相比，由于各种不定的环境也无法做到自动构建，
容器化部署等。因此在这种情况下尽量减少部署时的服务软件需求，打出的包数量也尽量少。针对这种情况这里采用的在开发中做到前后端独立开发，打包时在后端springboot打包发布时将前端的构建输出一起打入，
最后只需部署springboot的项目即可，无需再安装nginx服务器

```
<properties>
    <vue-modle-path>${basedir}/../spring-boot-vue-web/</vue-modle-path>
</properties>


<!--在执行打包时，进行npm 打包-->
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>exec-npm-install</id>
            <phase>validate</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>npm</executable>
                <arguments>
                    <argument>install</argument>
                </arguments>
                <workingDirectory>${vue-modle-path}</workingDirectory>
            </configuration>
        </execution>
        <execution>
            <id>exec-cnpm-run-build</id>
            <phase>validate</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>npm</executable>
                <arguments>
                    <argument>run</argument>
                    <argument>build</argument>
                </arguments>
                <workingDirectory>${vue-modle-path}</workingDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
<!--资源复制：将项目的前端文件打包到boot项目的classes中-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <configuration>
        <encoding>${project.build.sourceEncoding}</encoding>
    </configuration>
    <executions>
        <execution>
            <id>copy-vue-resources</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <configuration>
                <encoding>${project.build.sourceEncoding}</encoding>
                <outputDirectory>${basedir}/src/main/resources/static</outputDirectory>
                <resources>
                    <resource>
                        <directory>${vue-modle-path}/dist</directory>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
```

- `注意maven的生命周期选择 否则编译的web无法被打包进项目`
maven常用生命周期:

```
validate
generate-sources
process-sources
generate-resources
process-resources     复制并处理资源文件，至目标目录，准备打包。
compile     编译项目的源代码。
process-classes
generate-test-sources 
process-test-sources 
generate-test-resources
process-test-resources     复制并处理资源文件，至目标测试目录。
test-compile     编译测试源代码。
process-test-classes
test     使用合适的单元测试框架运行测试。这些测试代码不会被打包或部署。
prepare-package
package     接受编译好的代码，打包成可发布的格式，如 JAR 。
pre-integration-test
integration-test
post-integration-test
verify
install     将包安装至本地仓库，以让其它项目依赖。
deploy     将最终的包复制到远程的仓库，以让其它开发人员与项目共享。
```


#### 自定义Spring Security服务（适应前后端分离）

