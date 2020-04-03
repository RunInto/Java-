package 注解.repetable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 	注解容器
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableAnnotations {
	RepeatableAnnotation[] value();
}
