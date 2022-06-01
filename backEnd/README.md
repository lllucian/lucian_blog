# 环境要求
+ springboot 2.7.0
+ jdk1.8
+ maven
+ docker(使用docker-compose运行譬如mysql、elasticsearch等环境)
+ mysql8.0+
+ canal-server v1.1.6
+ minio
+ elasticsearch 7.17.3
+ canal-adapter(这个会在下面解释如何安装)

# 安装并使用canal-server
1. [下载canal-adapter](https://github.com/alibaba/canal/releases/download/canal-1.1.6/canal.adapter-1.1.6.tar.gz)
2. 解压该压缩包并将该压缩包里的内容放至你想放的位置
3. 编辑canal-adapter下面的application.yml文件
```yaml
server:
  port: 8081 #注意端口不要冲突，自行修改亦可
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    default-property-inclusion: non_null

canal.conf:
  mode: tcp
  flatMessage: true
  zookeeperHosts:
  syncBatchSize: 1000
  retries: -1
  timeout:
  accessKey:
  secretKey:
  consumerProperties:
    # canal tcp consumer
    canal.tcp.server.host: 127.0.0.1:11111 #canal-server地址
    canal.tcp.zookeeper.hosts:
    canal.tcp.batch.size: 500
    canal.tcp.username:
    canal.tcp.password:

  srcDataSources:
    defaultDS:
      url: jdbc:mysql://127.0.0.1:3307/lucian_blog?useUnicode=true #数据库地址
      username: lucian
      password: 123456
  canalAdapters:
    - instance: example # canal instance Name or mq topic name
      groups:
        - groupId: g1
          outerAdapters:
            - name: logger
            - name: es7
              hosts: http://127.0.0.1:9200 # elasticsearch地址
              properties:
                mode: rest
                cluster.name: docker-cluster 
```
4. 进入es7目录中移除目录下的所有文件并增加以下`post.yml`文件
```yaml
dataSourceKey: defaultDS
destination: example
groupId: g1
esMapping:
  _index: posts
  _id: _id
  upsert: true
#  pk: id
  sql: "select p.id AS _id, p.title, p.description, p.content from posts p "
#  objFields:
#    _labels: array:;
  etlCondition: "where p.updated_at>={}"
  commitBatch: 3000
```
5. 进入bin目录并启动startup.sh脚本文件
6. 进入logs目录查看是否启动成功
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