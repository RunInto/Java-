package 注解.repetable;

import java.lang.reflect.Method;

@RepeatableAnnotation
@RepeatableAnnotation
public class Main {

	public static void main(String[] args) throws SecurityException, ClassNotFoundException {
		String className = "注解.repetable.Student";
		for (Method m : Class.forName(className).getMethods()) {
			//如果该方法有该注解，这儿为什么和方法上标注的不一样呢？因为方法上标注的多个相同注解最终都会放入这个注解容器内
			if (m.isAnnotationPresent(RepeatableAnnotations.class)) {
				//获取重复注解数组
				RepeatableAnnotation [] annos = m.getAnnotationsByType(RepeatableAnnotation.class);
				for (RepeatableAnnotation anno : annos) {
					System.out.println(anno.a() + "," + anno.b() + "," + anno.c());
					try {
						//分别就多个不同注解进行方法调用，这儿适用于编写完代码做测试用例的书写，可以根据不同的值多次调用方法
						m.invoke(null, anno.a(),anno.b(),anno.c());
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
