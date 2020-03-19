package demo.controller;

import demo.model.SystemInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 测试
 *  ==> 分布式环境中如何解决 session 的问题
 *      粘性会话
 *      会话复制
 *      集中会话
 *  ==> 认识 Spring Session
 *      简化集群中的用户会话管理
 *      无需绑定容器特定解决方案
 *  支持的存储
 *      Redis
 *      MongoDB
 *      JDBC
 *      Hazelcast
 *  实现原理
 *      通过定制的 HttpServletRequest 返回定制的 HttpSession
 *  核心类
 *      SessionRepositoryRequestWrapper
 *      SessionRepositoryFilter
 *      DelegatingFilterProxy
 *  ==> 基于 Redis 的 HttpSession
 *      引入依赖 spring-session-data-redis
 *      基本配置
 *        @EnableRedisHttpSession
 *        提供 RedisConnectionFactory
 *        实现 AbstractHttpSessionApplicationInitializer
 *           配置 DelegatingFilterProxy
 *  ==> Spring Boot 对 Spring Session 的支持
 *   application.properties
 *      spring.session.store-type = redis
 *      spring.session.timeout =
 *        server.servlet.session.timeout=
 *      spring.session.redis.flush-mode = on-save
 *      spring.session.redis.namespace= spring:session
 */
@RestController
@EnableRedisHttpSession
@Api(tags = "系统相关信息")
@RequestMapping("/SystemInfo")
public class SystemInfoController {
    @Resource
    private SystemInfo systemInfo;

    /**
     * 编码设置 TODO
     */
    @GetMapping("/{version}")
    @ApiOperation(value = "查询系统信息")
    public SystemInfo getSystemInfo(HttpSession httpSession,@PathVariable(name = "version") String version) {
        SystemInfo sessionSystemInfo = (SystemInfo) httpSession.getAttribute(version);
        if(sessionSystemInfo == null) {
            httpSession.setAttribute("version1",systemInfo);
        }
        return sessionSystemInfo;
    }
    /**
     * ==> Spring Boot 四大核心
     * 自动配置 Auto Configuration
     * 起步依赖 Starter Dependency
     * 命令行界面 Spring Boot CLI
     * Actuator
     *
     * ==> 了解自动配置
     *  基于添加的 JPA 依赖自动对 Spring Boot 应用程序进行配置
     *  spring-boot-autoconfiguration
     *  开启自动配置
     * @EnableAutoConfiguration
     *  exclude = Class<?>[]
     * @SpringBootApplication
     */
}
