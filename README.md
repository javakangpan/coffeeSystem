# coffeeSystem
写的一个简单的咖啡店，用于学习

使用的技术

==>认识 SpringBoot 的各类 Actuator Endpoint
Actuator
目的
    监控并管理应用程序
访问方式
    HTTP JMX
依赖
    spring-boot-starter-actuator
一些常用的 Endpoint
    beans 显示容器 bean 列表
    caches 显示应用中的缓存
    conditions 显示配置条件的计算情况
    configprops 显示 @ConfigurationProperties 的信息
    env 显示 ConfigurableEnvironment 环境中的属性
    health 显示健康检查信息
    httptrace 显示 HTTP Trace 的信息
    info 显示设置好的应用信息
    loggers
    metrics 显示应用的度量信息
    mapping 显示所有的 @RequestMapping 信息
    scheduledtasks 显示应用的调度任务信息
    shutdown 优雅地关闭应用程序
    threaddump 执行 Thread Dump
    heapdump 返回 Heap Dump 文件,格式为 HPROF
    prometheus 返回可供 Prometheus 抓取的信息

如何访问 Actuator Endpoint
    HTTP 访问 /actuator/<id>
端口与路径
    management.server.address=
    management.server.port=
    management.endpoint.web.base-path=/actuator
    management.endpoint.web.path-mapping.<id>=路径
开启 Endpoint
    management.endpoint.<id>.enable=true
    management.endpoint.enabled-by-default=false
暴露 Endpoint
    management.endpoint.jmx.exposure.exclude=
    management.endpoint.jmx.exposure.include=*
    management.endpoint.web.exposure.exclude=
    management.endpoint.web.exposure.include=info,health
    
    
==>HiKariCP 为什么快
    https://github.com/brettwooldridge/HikariCP
    1.字节码级别优化 (很多方法通过 JavaAssist 生成)
    2.大量小改进
        用 FastStatementList 代替 ArrayList
        无锁集合 ConcurrentBag
        代理类的优化 (比如用 invokestatic 代替了 invokevirtual)
        
        
==>Druid
    https://github.com/alibaba/druid/wiki/
    实用的功能
        详细的监控
        ExceptionSorter,针对主流数据库的返回码都有支持
        SQL 防注入
        内置加密配置
        众多扩展点,方便进行配置
        
        
==>swagger2
    http://localhost:8080/swagger-ui.html#/
    
==>caffeine
    https://github.com/ben-manes/caffeine/blob/master/README.md
==>Redis
    String,Hash, List, Set, Sorted Set
        
==>Spring 缓存抽象

==>定制 Web 容器的运行参数
    curl -H "Accept-Encoding:gzip" -v http://localhost:8080/coffee/1 
    
==>配置 HTTPS 支持

==>Sentinel

==>Docker 

==>prometheus
    https://prometheus.fuckcloudnative.io/
    https://prometheus.io/docs/introduction/overview/

==>p6spy
    https://p6spy.readthedocs.io/en/latest/install.html
    
==>springfox
    http://springfox.github.io/springfox/docs/current/#introduction
   
==>Spring Boot Admin
    目的:为应用程序提供一套管理界面
    主要功能:集中展示应用程序 Actuator 相关的内容,变更通知(集成邮箱报警功能)

==>MySql
    主从复制 分库分表 读写分离 
    中间件 MyCat MaxScale(MariaDB https://mariadb.com/) seata(分布式事务框架)

==>待学习
    http://doc.jeecg.com/1273753
    https://martinfowler.com/bliki/CircuitBreaker.html
    
==>网易云https://c.163yun.com/hub#/home

==>Cron表达式

==>Quartz
    
==>Hutool https://www.hutool.cn/
    Hutool是一个Java工具包类库，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类
    
==>Vue.js https://cn.vuejs.org/v2/guide/

==>Element-UI http://element-cn.eleme.io/#/zh-CN

==>Netty

==>Apollo

      
