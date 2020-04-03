package 注解.日志;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logger {
	String operateNote() default "";
	OperateType operateType() default OperateType.SELECT; 
}
