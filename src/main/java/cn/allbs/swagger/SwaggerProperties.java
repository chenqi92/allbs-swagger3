package cn.allbs.swagger;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 类 SwaggerProperties
 *
 * @author ChenQi
 * @since 2022/8/9 13:38
 */
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

    public SwaggerProperties() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getVersion() {
        return this.version;
    }

    public Map<String, SecurityScheme> getSecuritySchemes() {
        return this.securitySchemes;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SwaggerProperties)) return false;
        final SwaggerProperties other = (SwaggerProperties) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (!Objects.equals(this$title, other$title)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (!Objects.equals(this$description, other$description))
            return false;
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        if (!Objects.equals(this$version, other$version)) return false;
        final Object this$securitySchemes = this.getSecuritySchemes();
        final Object other$securitySchemes = other.getSecuritySchemes();
        if (!Objects.equals(this$securitySchemes, other$securitySchemes))
            return false;
        final Object this$servers = this.getServers();
        final Object other$servers = other.getServers();
        if (!Objects.equals(this$servers, other$servers)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SwaggerProperties;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $version = this.getVersion();
        result = result * PRIME + ($version == null ? 43 : $version.hashCode());
        final Object $securitySchemes = this.getSecuritySchemes();
        result = result * PRIME + ($securitySchemes == null ? 43 : $securitySchemes.hashCode());
        final Object $servers = this.getServers();
        result = result * PRIME + ($servers == null ? 43 : $servers.hashCode());
        return result;
    }

    public String toString() {
        return "SwaggerProperties(title=" + this.getTitle() + ", description=" + this.getDescription() + ", version=" + this.getVersion() + ", securitySchemes=" + this.getSecuritySchemes() + ", servers=" + this.getServers() + ")";
    }
}
