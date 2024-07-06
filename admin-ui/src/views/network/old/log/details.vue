<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row"></div>
            <div class="mt-4">
              <h5 class="font-size-14 mb-3">
                <strong>RabbitMQ死信队列日志</strong>
              </h5>
              <div class="product-desc">
                <b-tabs
                  class="nav-tabs-custom"
                  content-class="border border-top-0 p-4"
                >
                  <b-tab title="日志内容">
                    <div>
                      <p>
                        D:\ZEnvironment\Java\jdk1.8.0_321\jre\bin\java.exe
                        -XX:TieredStopAtLevel=1 -noverify
                        -Dspring.output.ansi.enabled=always
                        -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true
                        -Dspring.liveBeansView.mbeanDomain
                        -Dspring.application.admin.enabled=true
                        "-javaagent:D:\JetBrains\IntelliJ IDEA
                        2021.3.2\lib\idea_rt.jar=53830:D:\JetBrains\IntelliJ
                        IDEA 2021.3.2\bin" -Dfile.encoding=UTF-8 -classpath
                        D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\charsets.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\deploy.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\access-bridge-64.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\cldrdata.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\dnsns.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\jaccess.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\jfxrt.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\localedata.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\nashorn.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\sunec.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\sunjce_provider.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\sunmscapi.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\sunpkcs11.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\ext\zipfs.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\javaws.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\jce.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\jfr.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\jfxswt.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\jsse.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\management-agent.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\plugin.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\resources.jar;D:\ZEnvironment\Java\jdk1.8.0_321\jre\lib\rt.jar;E:\Laborer\京茶吉鹿项目\A-终版项目\or-server\target\classes;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-web\2.5.5\spring-boot-starter-web-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter\2.5.5\spring-boot-starter-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot\2.5.5\spring-boot-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-logging\2.5.5\spring-boot-starter-logging-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\ch\qos\logback\logback-classic\1.2.6\logback-classic-1.2.6.jar;D:\ZEnvironment\Maven\maven-repository\ch\qos\logback\logback-core\1.2.6\logback-core-1.2.6.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\logging\log4j\log4j-to-slf4j\2.14.1\log4j-to-slf4j-2.14.1.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\logging\log4j\log4j-api\2.14.1\log4j-api-2.14.1.jar;D:\ZEnvironment\Maven\maven-repository\org\slf4j\jul-to-slf4j\1.7.32\jul-to-slf4j-1.7.32.jar;D:\ZEnvironment\Maven\maven-repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-core\5.3.10\spring-core-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-jcl\5.3.10\spring-jcl-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\yaml\snakeyaml\1.28\snakeyaml-1.28.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-json\2.5.5\spring-boot-starter-json-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.12.5\jackson-datatype-jdk8-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.12.5\jackson-datatype-jsr310-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.12.5\jackson-module-parameter-names-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-tomcat\2.5.5\spring-boot-starter-tomcat-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.53\tomcat-embed-core-9.0.53.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.53\tomcat-embed-el-9.0.53.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.53\tomcat-embed-websocket-9.0.53.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-web\5.3.10\spring-web-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-beans\5.3.10\spring-beans-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-webmvc\5.3.10\spring-webmvc-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-context\5.3.10\spring-context-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-expression\5.3.10\spring-expression-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\mysql\mysql-connector-java\8.0.27\mysql-connector-java-8.0.27.jar;D:\ZEnvironment\Maven\maven-repository\com\alibaba\druid\1.2.9\druid-1.2.9.jar;D:\ZEnvironment\Maven\maven-repository\com\alibaba\druid-spring-boot-starter\1.2.9\druid-spring-boot-starter-1.2.9.jar;D:\ZEnvironment\Maven\maven-repository\org\slf4j\slf4j-api\1.7.32\slf4j-api-1.7.32.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-autoconfigure\2.5.5\spring-boot-autoconfigure-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\projectlombok\lombok\1.18.22\lombok-1.18.22.jar;D:\ZEnvironment\Maven\maven-repository\com\baomidou\mybatis-plus-boot-starter\3.5.1\mybatis-plus-boot-starter-3.5.1.jar;D:\ZEnvironment\Maven\maven-repository\com\baomidou\mybatis-plus\3.5.1\mybatis-plus-3.5.1.jar;D:\ZEnvironment\Maven\maven-repository\com\baomidou\mybatis-plus-extension\3.5.1\mybatis-plus-extension-3.5.1.jar;D:\ZEnvironment\Maven\maven-repository\com\baomidou\mybatis-plus-core\3.5.1\mybatis-plus-core-3.5.1.jar;D:\ZEnvironment\Maven\maven-repository\com\baomidou\mybatis-plus-annotation\3.5.1\mybatis-plus-annotation-3.5.1.jar;D:\ZEnvironment\Maven\maven-repository\org\mybatis\mybatis\3.5.9\mybatis-3.5.9.jar;D:\ZEnvironment\Maven\maven-repository\org\mybatis\mybatis-spring\2.0.6\mybatis-spring-2.0.6.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-jdbc\2.5.5\spring-boot-starter-jdbc-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\com\zaxxer\HikariCP\4.0.3\HikariCP-4.0.3.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-jdbc\5.3.10\spring-jdbc-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-tx\5.3.10\spring-tx-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-swagger2\3.0.0\springfox-swagger2-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-spi\3.0.0\springfox-spi-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-core\3.0.0\springfox-core-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\net\bytebuddy\byte-buddy\1.10.22\byte-buddy-1.10.22.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-schema\3.0.0\springfox-schema-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-swagger-common\3.0.0\springfox-swagger-common-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\swagger\core\v3\swagger-annotations\2.1.2\swagger-annotations-2.1.2.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-spring-web\3.0.0\springfox-spring-web-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\github\classgraph\classgraph\4.8.83\classgraph-4.8.83.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-spring-webmvc\3.0.0\springfox-spring-webmvc-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\io\springfox\springfox-spring-webflux\3.0.0\springfox-spring-webflux-3.0.0.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\plugin\spring-plugin-core\2.0.0.RELEASE\spring-plugin-core-2.0.0.RELEASE.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\plugin\spring-plugin-metadata\2.0.0.RELEASE\spring-plugin-metadata-2.0.0.RELEASE.jar;D:\ZEnvironment\Maven\maven-repository\io\swagger\swagger-annotations\1.5.20\swagger-annotations-1.5.20.jar;D:\ZEnvironment\Maven\maven-repository\io\swagger\swagger-models\1.5.20\swagger-models-1.5.20.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\core\jackson-annotations\2.12.5\jackson-annotations-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\org\mapstruct\mapstruct\1.3.1.Final\mapstruct-1.3.1.Final.jar;D:\ZEnvironment\Maven\maven-repository\com\github\xiaoymin\swagger-bootstrap-ui\1.9.6\swagger-bootstrap-ui-1.9.6.jar;D:\ZEnvironment\Maven\maven-repository\org\javassist\javassist\3.25.0-GA\javassist-3.25.0-GA.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\boot\spring-boot-starter-security\2.5.5\spring-boot-starter-security-2.5.5.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\spring-aop\5.3.10\spring-aop-5.3.10.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\security\spring-security-config\5.5.2\spring-security-config-5.5.2.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\security\spring-security-core\5.5.2\spring-security-core-5.5.2.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\security\spring-security-crypto\5.5.2\spring-security-crypto-5.5.2.jar;D:\ZEnvironment\Maven\maven-repository\org\springframework\security\spring-security-web\5.5.2\spring-security-web-5.5.2.jar;D:\ZEnvironment\Maven\maven-repository\io\jsonwebtoken\jjwt\0.9.1\jjwt-0.9.1.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\core\jackson-databind\2.12.5\jackson-databind-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\com\fasterxml\jackson\core\jackson-core\2.12.5\jackson-core-2.12.5.jar;D:\ZEnvironment\Maven\maven-repository\org\apache\commons\commons-lang3\3.12.0\commons-lang3-3.12.0.jar;D:\ZEnvironment\Maven\maven-repository\com\alibaba\fastjson\1.2.79\fastjson-1.2.79.jar;D:\ZEnvironment\Maven\maven-repository\com\github\pagehelper\pagehelper\5.3.2\pagehelper-5.3.2.jar;D:\ZEnvironment\Maven\maven-repository\com\github\jsqlparser\jsqlparser\4.5\jsqlparser-4.5.jar
                        com.jingchao.BBSApplication . ____ _ __ _ _ /\\ / ___'_
                        __ _ _(_)_ __ __ _ \ \ \ \ ( ( )\___ | '_ | '_| | '_ \/
                        _` | \ \ \ \ \\/ ___)| |_)| | | | | || (_| | ) ) ) ) '
                        |____| .__|_| |_|_| |_\__, | / / / /
                        =========|_|==============|___/=/_/_/_/ :: Spring Boot
                        :: (v2.5.5) 2023-04-13 08:47:04.397 INFO 28112 --- [
                        main] com.jingchao.BBSApplication : Starting
                        BBSApplication using Java 1.8.0_321 on DESKTOP-2OFCF3R
                        with PID 28112
                        (E:\Laborer\京茶吉鹿项目\A-终版项目\or-server\target\classes
                        started by Aubuary in
                        E:\Laborer\京茶吉鹿项目\A-终版项目\or-server) 2023-04-13
                        08:47:04.403 INFO 28112 --- [ main]
                        com.jingchao.BBSApplication : No active profile set,
                        falling back to default profiles: default 2023-04-13
                        08:47:05.782 INFO 28112 --- [ main]
                        o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat
                        initialized with port(s): 8083 (http) 2023-04-13
                        08:47:05.789 INFO 28112 --- [ main]
                        o.apache.catalina.core.StandardService : Starting
                        service [Tomcat] 2023-04-13 08:47:05.790 INFO 28112 ---
                        [ main] org.apache.catalina.core.StandardEngine :
                        Starting Servlet engine: [Apache Tomcat/9.0.53]
                        2023-04-13 08:47:05.791 INFO 28112 --- [ main]
                        o.a.catalina.core.AprLifecycleListener : Loaded Apache
                        Tomcat Native library [1.2.34] using APR version
                        [1.7.0]. 2023-04-13 08:47:05.791 INFO 28112 --- [ main]
                        o.a.catalina.core.AprLifecycleListener : APR
                        capabilities: IPv6 [true], sendfile [true], accept
                        filters [false], random [true], UDS [true]. 2023-04-13
                        08:47:05.791 INFO 28112 --- [ main]
                        o.a.catalina.core.AprLifecycleListener : APR/OpenSSL
                        configuration: useAprConnector [false], useOpenSSL
                        [true] 2023-04-13 08:47:05.794 INFO 28112 --- [ main]
                        o.a.catalina.core.AprLifecycleListener : OpenSSL
                        successfully initialized [OpenSSL 1.1.1o 3 May 2022]
                        2023-04-13 08:47:05.871 INFO 28112 --- [ main]
                        o.a.c.c.C.[Tomcat].[localhost].[/] : Initializing Spring
                        embedded WebApplicationContext 2023-04-13 08:47:05.871
                        INFO 28112 --- [ main]
                        w.s.c.ServletWebServerApplicationContext : Root
                        WebApplicationContext: initialization completed in 1417
                        ms 2023-04-13 08:47:05.976 INFO 28112 --- [ main]
                        c.a.d.s.b.a.DruidDataSourceAutoConfigure : Init
                        DruidDataSource 2023-04-13 08:47:06.789 INFO 28112 --- [
                        main] com.alibaba.druid.pool.DruidDataSource :
                        {dataSource-1} inited _ _ |_ _ _|_. ___ _ | _ | |
                        |\/|_)(_| | |_\ |_)||_|_\ / | 3.5.1 2023-04-13
                        08:47:09.282 INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/register'] with [] 2023-04-13 08:47:09.282
                        INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/login'] with [] 2023-04-13 08:47:09.282 INFO
                        28112 --- [ main] o.s.s.web.DefaultSecurityFilterChain :
                        Will secure Ant [pattern='/logout'] with [] 2023-04-13
                        08:47:09.282 INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/css/**'] with [] 2023-04-13 08:47:09.282 INFO
                        28112 --- [ main] o.s.s.web.DefaultSecurityFilterChain :
                        Will secure Ant [pattern='/index.html'] with []
                        2023-04-13 08:47:09.282 INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/favicon.ico'] with [] 2023-04-13 08:47:09.282
                        INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/doc.html'] with [] 2023-04-13 08:47:09.282
                        INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/webjars/**'] with [] 2023-04-13 08:47:09.282
                        INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/swagger-resources/**'] with [] 2023-04-13
                        08:47:09.282 INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure Ant
                        [pattern='/v2/api-docs/**'] with [] 2023-04-13
                        08:47:09.312 INFO 28112 --- [ main]
                        o.s.s.web.DefaultSecurityFilterChain : Will secure any
                        request with
                        [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@6c075e9d,
                        org.springframework.security.web.context.SecurityContextPersistenceFilter@30a9e3db,
                        org.springframework.security.web.header.HeaderWriterFilter@7436364d,
                        org.springframework.security.web.authentication.logout.LogoutFilter@296edc75,
                        com.jingchao.config.security.JwtAuthenticationTokenFilter@76544c0a,
                        org.springframework.security.web.savedrequest.RequestCacheAwareFilter@2e0fdbe9,
                        org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@5b2b8d86,
                        org.springframework.security.web.authentication.AnonymousAuthenticationFilter@47044f7d,
                        org.springframework.security.web.session.SessionManagementFilter@23444a36,
                        org.springframework.security.web.access.ExceptionTranslationFilter@4026461d,
                        org.springframework.security.web.access.intercept.FilterSecurityInterceptor@580902cd]
                        2023-04-13 08:47:09.400 INFO 28112 --- [ main]
                        o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat started
                        on port(s): 8083 (http) with context path '' 2023-04-13
                        08:47:10.100 INFO 28112 --- [nio-8083-exec-2]
                        o.a.c.c.C.[Tomcat].[localhost].[/] : Initializing Spring
                        DispatcherServlet 'dispatcherServlet' 2023-04-13
                        08:47:10.100 INFO 28112 --- [nio-8083-exec-2]
                        o.s.web.servlet.DispatcherServlet : Initializing Servlet
                        'dispatcherServlet' 2023-04-13 08:47:10.104 INFO 28112
                        --- [nio-8083-exec-2] o.s.web.servlet.DispatcherServlet
                        : Completed initialization in 4 ms 2023-04-13
                        08:47:10.315 INFO 28112 --- [ main]
                        com.jingchao.BBSApplication : Started BBSApplication in
                        6.596 seconds (JVM running for 7.342)
                      </p>
                    </div>
                  </b-tab>
                  <b-tab title="日志属性">
                    <div class="table-responsive">
                      <table class="table table-nowrap mb-0">
                        <tbody>
                          <tr>
                            <th scope="row" style="width: 400px">标题</th>
                            <td>日志1</td>
                          </tr>
                          <tr>
                            <th scope="row">时间</th>
                            <td>2022-04-10</td>
                          </tr>
                          <tr>
                            <th scope="row">创建人</th>
                            <td>京茶吉鹿</td>
                          </tr>
                          <tr>
                            <th scope="row">创建时间</th>
                            <td>2023-04-17</td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </b-tab>
                </b-tabs>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layout/index";
import PageHeader from "@/layout/common/page-header";

export default {
  components: { Layout, PageHeader },

  data() {
    return {
      title: "日志详情",
      items: [
        {
          text: "日志管理",
        },
        {
          text: "日志详情",
          active: true,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped></style>
