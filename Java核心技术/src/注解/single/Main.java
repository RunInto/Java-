package 注解.single;

import java.lang.reflect.Method;

public class Main {
	public static void main(String [] args) throws SecurityException, ClassNotFoundException {
		int passed = 0, failed = 0;
		String className = "注解.single.Foo";
		for (Method m : Class.forName(className).getMethods()) {
			if (m.isAnnotationPresent(SingleTest.class)) {
				System.out.println(m.getName());
				SingleTest s = m.getAnnotation(SingleTest.class);
				try {
					m.invoke(null,  s.value());
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
