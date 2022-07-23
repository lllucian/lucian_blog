# lucian_blog
使用SpringBoot + Vue3 + TypeScript做的一个博客系统

# 环境要求
+ springboot 2.7.0
+ jdk1.8
+ maven
+ docker(使用docker-compose运行譬如mysql、elasticsearch等环境)
+ mysql8.0+
+ logstash
+ minio
+ elasticsearch 7.17.3
# docker mysql用户创建并允许远程连接
1. 使用 `docker-compose exec db mysql -uroot -p`连接mysql并输入密码
2. 执行以下代码
   ```mysql
   use mysql;
   CREATE USER 'lucian'@'%' IDENTIFIED BY '123456';
   GRANT ALL PRIVILEGES ON *.* TO 'lucian'@'%';
   FLUSH PRIVILEGES;
   ```

# 开始并使用
1. 前台和后台分了两个Application启动器`FrontApplication` `LucianBlogApplication`分别启动两个启动器
2. 后台默认密码`lucian` `123456`
# 功能清单
+ 后台功能
  - [X] 管理员登陆
  - [ ] 管理员管理
  - [X] 后台登出
  - [X] 文章管理
  - [X] 分类管理
  - [X] 标签管理
  - [X] 轮播图管理类
  - [ ] 日志管理
+ 前台功能
  - [X] 轮播图展示
  - [X] 文章关键词检索
  - [X] 文章列表
  - [X] 文章详情展示
  - [ ] 分类展示
  - [ ] 标签展示
  - [ ] 每日一句展示