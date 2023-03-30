# springdoc

## 使用
### 添加依赖
#### maven

```xml
<dependency>
  <groupId>cn.allbs</groupId>
  <artifactId>allbs-swagger3</artifactId>
  <version>2.0.1</version>
</dependency>
```
#### Gradle

```
implementation 'cn.allbs:allbs-swagger3:2.0.1'
```


### 开启swagger功能

启动类添加`@EnableAllbsSwagger3`注解

### 过滤uri不映射出来
如果使用了通用包中的`@AllbsResponseAdvice`包装了返回结果则需要添加以下配置

```yaml
ignore:
  urls:
    - /v3/api-docs
```

### 配置说明（根据自己实际情况配置）

```yaml
swagger:
  title: springDoc文档
  description: 这是springdoc文档说明
  version: 1.0
  securitySchemes:
    token:
      type: APIKEY  # 类型
      in: HEADER    # 放header里面
      name: token # header-key
  servers:
    - url: http://127.0.0.1:8888 # 自定义服务器 URL，如部署在docker中时可以配置
      description: 本地服务器
```

### 其他配置说明

```yml
springdoc:
  api-docs:
    # 通过此处关闭生产环境的api-docs
    enabled: false
  # 配置需要生成接口文档的扫描包
  packages-to-scan: cn.allbs.swagger.controller
  # 配置需要生成接口文档的接口路径，如果增加了该节点，那么只将/user开头的接口文档生成出来
  paths-to-match: /user/**
```



### 访问地址

http://{ip}:{port}/doc.html

### 接口中使用示例

```java
package cn.allbs.swagger.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类 SwaggerEntity
 * </p>
 *
 * @author ChenQi
 * @since 2022/8/8 15:24
 */
@Data
@Schema(title = "swagger类", name = "SwaggerEntity")
public class SwaggerEntity {

    @Schema(description = "时间", name = "time", implementation = LocalDateTime.class, example = "2022-08-09'T'12:00:00")
    private LocalDateTime time;

    @Schema(description = "名称", name = "name", implementation = String.class, example = "张三")
    private String name;

    @Schema(description = "计数", name = "count", implementation = Integer.class, example = "12")
    private Integer count;
}
```

```java
package cn.allbs.swagger.controller;

import cn.allbs.common.utils.R;
import cn.allbs.swagger.entity.SwaggerEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 类 TestNoAuthController
 * </p>
 *
 * @author ChenQi
 * @since 2022/8/9 9:49
 */
@Tag(name = "B类")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class TestNoAuthController {

    @Operation(summary = "B类A方法")
    @Parameters({
            @Parameter(name = "name", description = "这是名称的注释", required = true, schema = @Schema(implementation = String.class), in = ParameterIn.QUERY),
            @Parameter(name = "count", description = "这是计数的注释", required = false, schema = @Schema(implementation = Integer.class), in = ParameterIn.QUERY),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SwaggerEntity.class),
                            examples = {@ExampleObject(value = "{\n" +
                                    "  \"code\": 200,\n" +
                                    "  \"success\": true,\n" +
                                    "  \"msg\": \"操作成功\",\n" +
                                    "  \"data\": {\n" +
                                    "    \"time\": \"2022-08-09T15:44:40.991\",\n" +
                                    "    \"name\": \"xxx\",\n" +
                                    "    \"count\": 123\n" +
                                    "  }\n" +
                                    "}")})})
    })
    @GetMapping("swaggerTest")
    public R<SwaggerEntity> swaggerTest(String name, Integer count) {
        SwaggerEntity swaggerEntity = new SwaggerEntity();
        swaggerEntity.setName(name);
        swaggerEntity.setTime(LocalDateTime.now());
        swaggerEntity.setCount(count);
        return R.ok(swaggerEntity);
    }
}
```

## swagger2及swagger3注解对应关系


| SpringFox | SpringDoc                                                    |
| -- |--------------------------------------------------------------|
| @Api | @Tag                                                         |
| @ApiIgnore | @Parameter(hidden = true)或@Operation(hidden = true)或@Hidden |
| @ApiImplicitParams | @Parameters                                                  |
| @ApiImplicitParam | @Parameter                                                   |
| @ApiModel | @Schema                                                      |
| @ApiModelProperty | @Schema                                                      |
| @ApiOperation(value = "foo", notes = "bar") | @Operation(summary = "foo", description = "bar")             |
| @ApiParam | @Parameter                                                   |
| @ApiResponse(code = 404, message = "foo") | ApiResponse(responseCode = "404", description = "foo")       |
