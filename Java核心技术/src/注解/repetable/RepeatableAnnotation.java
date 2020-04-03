package 注解.repetable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatableAnnotations.class)
public @interface RepeatableAnnotation {
	
	int a() default 0;
	int b() default 0;
	int c() default 0;
}
