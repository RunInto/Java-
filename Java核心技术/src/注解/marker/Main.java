package 注解.marker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(String [] args) throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int passed = 0,failed = 0;
		String className = "注解.marker.Foo";
		for (Method m : Class.forName(className).getMethods()) {
			//判断这个方法上是否有Test注解
			if (m.isAnnotationPresent(Test.class)) {
				try {
					m.invoke(null);
					++passed;
				}catch(Exception e) {
					System.out.printf("Test %s failed: %s %n",m,e.getCause());
					++failed;
				}
				
			}
		}
		System.out.printf("passed: %d,failed %d%n",passed,failed);
	}
}
