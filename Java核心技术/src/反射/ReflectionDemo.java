package 反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {
	
	public static void printNum() {
		System.out.println(5);
	}
	
	public static void main(String [] args) throws IllegalArgumentException, InvocationTargetException {
	
		try{
			Object obj = Class.forName("ReflectionDemo").newInstance();
			Method m = Class.forName("ReflectionDemo").getMethod("printNum");
			m.invoke(obj);
			
			ReflectionDemo r = (ReflectionDemo) Class.forName("ReflectionDemo").newInstance();
			r.printNum();
			
			Constructor<ReflectionDemo> c = ReflectionDemo.class.getConstructor();
			ReflectionDemo c1 = c.newInstance();
			c1.printNum();
		}catch(NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException l) {
			l.printStackTrace();
		}
	}
}


