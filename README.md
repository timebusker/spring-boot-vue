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
<!--mapStruct依赖-->
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-jdk8</artifactId>
    <version>1.3.0.Final</version>
</dependency>
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>${org.mapstruct.version}</version>
    <version>1.3.0.Final</version>
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