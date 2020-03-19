# coffeeSystem
写的一个简单的咖啡店，用于学习

使用的技术

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
==>Spring 缓存抽象
==>定制 Web 容器的运行参数
    curl -H "Accept-Encoding:gzip" -v http://localhost:8080/coffee/1 
==>配置 HTTPS 支持
      
