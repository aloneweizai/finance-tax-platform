## 财税平台SOA

本系统作为`财税平台`的后端业务支撑项目，为前端业务提供API调用.

### 安装说明

如果没有特别说明，下面的安装以macOS平台为例，其他平台的安装类似，可以参考对应的官方安装说明文档。在macOS平台，通常使用`brew`包管理工具安装，
关于brew的安装使用请参考[官方网站](https://brew.sh)。

#### apache kafka安装

安装命令：
> brew install kafka

启动服务(安装是会将kafka的bin目录加入PATH中)：

    zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties &
    kafka-server-start /usr/local/etc/kafka/server.properties &
    
kafka依赖zookeeper, 如果没有报错，说明启动成功。

##### 设置多个broker集群

到目前，只是单一的运行一个broker，没什么意思。对于Kafka,一个broker仅仅只是一个集群的大小, 所有多设几个broker.首先为每个broker创建一个配置文件:

    cp server.properties server-1.properties 
    cp server.properties server-2.properties

现在编辑这些新建的文件，设置以下属性：

server-1.properties: 

    broker.id=1 
    listeners=PLAINTEXT://:9093 
    log.dir=/tmp/kafka-logs-1

server-2.properties: 

    broker.id=2 
    listeners=PLAINTEXT://:9094 
    log.dir=/tmp/kafka-logs-2

broker.id是集群中每个节点的唯一且永久的名称，我们修改端口和日志分区是因为我们现在在同一台机器上运行，要防止broker在同一端口上注册和覆盖对方的数据。

我们已经运行了zookeeper和刚才的一个kafka节点，所有我们只需要在启动2个新的kafka节点。

    kafka-server-start /usr/local/etc/kafka/server-1.properties &
    kafka-server-start /usr/local/etc/kafka/server-2.properties &

[kafka-manager](https://github.com/yahoo/kafka-manager)是yahoo开源的kafka管理工具，它支持如下功能：
* 管理多个集群
* 方便查看集群状态
* 执行preferred replica election
* 批量为多个Topic生成并执行Partition分配方案
* 创建Topic
* 删除Topic（只支持0.8.2及以上版本，同时要求在Broker中将delete.topic.enable设置为true）
* 为已有Topic添加Partition
* 更新Topic配置
* 在Broker JMX Reporter开启的前提下，轮询Broker级别和Topic级别的Metrics
* 监控Consumer Group及其消费状态
* 支持添加和查看LogKafka

#### redis安装

安装命令：
> brew install redis

创建集群节点，单节点的redis也是可以支持分布式session共享的；但是为了高可靠性，我们创建redis集群。
redis集群至少要3个节点；现在创建一个3主3从的redis集群：
    
    mkdir redis_cluster && cd redis_cluster
    mkdir 7000 7001 7002 7003 7004 7005

每个节点中需要一个redis.conf配置文件，文件内容如下(注意修改对应的端口号)：

    port 7000
    cluster-enabled yes
    cluster-config-file node_7000.conf
    cluster-node-timeout 5000
    appendonly yes
    #bind 127.0.0.1

启动集群：

    redis-server /usr/local/Cellar/redis/redis_cluster/7000/redis.conf &
    redis-server /usr/local/Cellar/redis/redis_cluster/7001/redis.conf &
    redis-server /usr/local/Cellar/redis/redis_cluster/7002/redis.conf &
    redis-server /usr/local/Cellar/redis/redis_cluster/7003/redis.conf &
    redis-server /usr/local/Cellar/redis/redis_cluster/7004/redis.conf &
    redis-server /usr/local/Cellar/redis/redis_cluster/7005/redis.conf &

依次启动6个redis实例。查看redis实例：
> ps -ef | grep redis

这时候的6个实例还没有成为集群，只是6个单独的节点，要让6个节点成为集群，需要使用redis源码中的`redis-trib.rb`。
你首先要安装它(下载包的src目录中已经包含)：
> gem install redis

创建集群：
> ~/Downloads/redis-3.2.8/src/redis-trib.rb create --replicas 1 \
118.118.116.202:7000 \
118.118.116.202:7001 \
118.118.116.202:7002 \
118.118.116.202:7003 \
118.118.116.202:7004 \
118.118.116.202:7005

命令完成之后，你会看到大量的输出信息，安装完成。

redis常用命令：

    redis-cli -c -p 7000
    redis 127.0.0.1:7000> set foo bar
    -> Redirected to slot [12182] located at 127.0.0.1:7002
    OK
    redis 127.0.0.1:7002> set hello world
    -> Redirected to slot [866] located at 127.0.0.1:7000
    OK
    redis 127.0.0.1:7000> get foo
    -> Redirected to slot [12182] located at 127.0.0.1:7002
    "bar"
    redis 127.0.0.1:7000> get hello
    -> Redirected to slot [866] located at 127.0.0.1:7000
    "world"

#### 项目安装

项目由三个子项目组成，使用`maven`管理依赖，在安装之前请确保你已经安装了`mvn`,可以通过`mvn -v`查看是否安装，
或参考[官方文档](http://maven.apache.org)安装。项目目录结构如下：
    
    finance-tax-platform
    ├── README.md
    ├── RESTful.md
    ├── abc12366-bangbang
    ├── abc12366-cms
    ├── abc12366-gateway
    ├── abc12366-message
    ├── abc12366-uc
    ├── finance-tax-platform.iml
    └── pom.xml

项目结构介绍：
* `README.md`包含了项目的简单介绍及如何安装项目，使用之前必读
* `RESTful.md`开发接口定义的规范，开发本系统的接口必须严格安装规范执行
* `abc12366-bangbang`帮帮子系统
* `abc12366-cms`内容管理系统
* `abc12366-uc`财税平台的核心业务--用户中心,为管理系统提供接口
* `abc12366-gateway`通用网关、通用工具包,其他子项目都需要经过本项目才能访问对应子项目的API
* `abc12366-message`消息子系统，所以子项目需要发送消息都通过本项目
* `pom.xml`maven项目管理依赖配置文件

**在代码层面，原则上所有子项目除了依赖`abc12366-gateway`之外，相互之间不能存在依赖。**

开发环境项目安装(在项目根路径)，请确保application.properties的spring.profiles.active为dev：
> mvn clean install

跳过测试安装：
> mvn clean install -Dmaven.test.skip=true

启动`abc12366-uc`：
> cd abc12366-uc && mvn spring-boot:run

或调试模式启动：
> cd abc12366-uc && mvn spring-boot:run --debug

下面需要注意启动顺序，项目之间会有依赖；否则某些情况下可能会有警告。

正式环境安装：
因为项目同时安装在IDC和公司机房，两边的配置环境是不一样的；所以在打包项目时，要根据不同的配置打包。
通过指定application.properties的spring.profiles.active的值确定机房环境：dev为项目开发环境，
prod为IDC机房环境，comp为公司机房环境。每次打包都需要确认是否为正确的机房环境，除gateway项目外，
其他4个项目都需要修改。比如修改为公司环境：

    # 在application.properties文件中，改为公司环境
    spring.profiles.active=comp
  

在确认配置文件为正确的机房环境后，在项目根路径执行下面的命令：
> mvn clean install

命令执行完成后如果代码没问题会看到成功信息，可以在每个子项目的target文件下看到一个可执行jar文件，
通过下面的命令查看jar是否打包成功：
> java -jar ./abc12366-uc/target/abc12366-uc-1.0.0-SNAPSHOT.jar

通过下面的参数可以动态指定端口、环境、配置文件:
> java -jar ./abc12366-uc/target/abc12366-uc-1.0.0-SNAPSHOT.jar\ 
--spring.profiles.active=prod1\
--server.port=9000\
--spring.config.location=./application-prod1.properties\
--logging.path=./abc12366
* --spring.profiles.active=prod1 指定环境为prod1，即后面spring.config.location指定的配置文件
* --server.port=9000 指定端口为9000
* --spring.config.location 指定配置文件路径，上面的示例为当前目录，如果在类路径下，可以使用classpath:/application.properties
* --logging.path 指定日志输出路径

### 接口测试

推荐使用跨平台命令行方式的`curl`或图形化工具`postman`。

### 涉及到的技术和框架

- [x] RESTful接口[Spring WebMvc](http://spring.io)
- [x] [Spring Boot](http://spring.io)
- [x] 统一日志[logback based on SLF4J](https://www.slf4j.org)
- [x] 消息中间件[apache kafka](http://kafka.apache.org)
- [x] 数据校验[Hibernate Validator](http://hibernate.org/validator/)
- [x] 数据访问DAO[Spring Mybatis](http://www.mybatis.org/spring/)
- [x] 数据库连接池[Druid](https://github.com/alibaba/druid/)
- [x] 数据缓存[redis](https://redis.io)
- [x] [Spring Data Redis](http://projects.spring.io/spring-data-redis/)
- [x] 数据库[Mysql](https://www.mysql.com)
- [x] 操作JSON[fastjson](https://github.com/alibaba/fastjson)

### alipay-sdk, hnds-security安装

在工程根目录执行：
> mvn install:install-file -DgroupId=com.alipay -DartifactId=alipay-sdk -Dversion=20170324180803 -Dpackaging=jar -Dfile=alipay-sdk-java20170324180803.jar
  mvn install:install-file -DgroupId=hnds -DartifactId=hnds-security -Dversion=1.0 -Dpackaging=jar -Dfile=hnds-security.jar
  mvn install:install-file -DgroupId=aliyun-sdk -DartifactId=aliyun-sdk-core -Dversion=3.3.1 -Dpackaging=jar -Dfile=aliyun-java-sdk-core-3.3.1.jar
  mvn install:install-file -DgroupId=aliyun-sdk -DartifactId=aliyun-sdk-dysmsapi -Dversion=1.0.0 -Dpackaging=jar -Dfile=aliyun-java-sdk-dysmsapi-1.0.0.jar

### 接口规范

参考项目根目录结构中的`RESTful.md`