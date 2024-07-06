package com.record.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


/* @OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "京茶吉鹿",
                        email = "jc.top@qq.com",
                        url = "https://o-record.com"
                ),
                description = "接口文档",
                title = "接口规范 - 京茶吉鹿",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "https://192.168.157.117"
        ),
        servers = {
                @Server(
                        description = "本地环境",
                        url = "http://localhost:8417"
                ),
                @Server(
                        description = "生成环境",
                        url = "https://o-record.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "....",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
) */
public class OpenApiConfig {
}
