package cn.allbs.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

/**
 * 类 Swagger3Config
 *
 * @author ChenQi
 * @since 2022/8/9 13:36
 */
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
public class Swagger3Config {

    private final SwaggerProperties swaggerProperties;

    /**
     * 配置参数
     *
     * @param swaggerProperties 配置参数
     */
    public Swagger3Config(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    /**
     * 配置openApi
     *
     * @param buildProperties
     * @return
     */
    @Bean
    @Profile({"dev", "test"})
    public OpenAPI openApi(ObjectProvider<BuildProperties> buildProperties) {
        OpenAPI openApi = new OpenAPI();

        if (null != swaggerProperties.getSecuritySchemes() && !swaggerProperties.getSecuritySchemes().isEmpty()) {
            //添加header
            openApi.components(new Components().securitySchemes(swaggerProperties.getSecuritySchemes()));
            swaggerProperties.getSecuritySchemes().keySet().forEach(key -> openApi.addSecurityItem(new SecurityRequirement().addList(key)));
        }

        // 基本信息
        openApi.info(new Info().title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(Optional.ofNullable(buildProperties.getIfAvailable()).map(BuildProperties::getVersion).orElse(swaggerProperties.getVersion())));
        if (!(swaggerProperties.getServers() == null || swaggerProperties.getServers().isEmpty())) {
            openApi.servers(swaggerProperties.getServers());
        }

        // 使用knife4j的ui
        openApi.externalDocs(new ExternalDocumentation());
        return openApi;
    }

    @Bean
    @Profile({"dev", "test"})
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi
                .builder()
                .group(swaggerProperties.getTitle()).pathsToMatch("/**").build();
    }
}
