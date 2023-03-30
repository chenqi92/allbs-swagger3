package cn.allbs.swagger;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 类 SwaggerProperties
 * </p>
 *
 * @author ChenQi
 * @since 2022/8/9 13:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 版本
     */
    private String version = "1.0.0";

    /**
     * 自定义的权限头
     */
    private Map<String, SecurityScheme> securitySchemes;

    /**
     * 自定义server名称
     */
    private List<Server> servers;
}