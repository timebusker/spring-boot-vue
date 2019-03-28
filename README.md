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
```

#### Mybatis菜单管理查询级缓存使用

```aidl

```
