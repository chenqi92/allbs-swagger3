package cn.allbs.annotation;


import cn.allbs.swagger.Swagger3Config;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ChenQi
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Swagger3Config.class})
public @interface EnableAllbsSwagger3 {
}