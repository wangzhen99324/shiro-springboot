# shiro-springboot
基于 JWT ， shiro，Springboot  的，Maven工程 。提供了一个简单易上手的后台权限系统模板。

## 序言

本项目修改自：本项目修改自：[FEBS-Shiro](https://github.com/wuyouzhuguli/FEBS-Shiro) 
在此衷心的感谢这位大佬。

当前工程只包含后端代码
前端代码有兴趣的同学可以去上面那个大佬的GitHub上去找。

这里再推荐一个 前端大佬的  工程 [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin) 

```
### 使用教程

#### 后端
1. Eclipse安装lombok插件

2. 导入项目

3. 新建MySQL（版本5.7.x）数据库 xxx，然后 更改  application.properties 中 的  spring.datasource.url 的url（其实就是把数据库名改成 xxx），以及 username password  

4.运行 Springboot 启动类


```
### 技术选型

#### 后端
- [Spring Boot 2.0.4]
- [Mybatis]
- [MySQL 5.7]
- [Shiro]
- [flyway]
- [lombok]

```
### 补充
flyway 是用来做数据库版本控制. sql 文件在 classpath:db/migiration 目录下，并且工程启动后会自动运行 此目录的sql 语句，非常方便。


欢迎大家  Issues。
