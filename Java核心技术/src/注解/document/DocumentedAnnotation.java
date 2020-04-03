package 注解.document;

import java.lang.annotation.*;

//表示这个注解是文档注解，用这个注解标注的方法会自动进入编译后的文档注释
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentedAnnotation {
	String value();
}
