package 注解.repetable;

public class Student {
	
	
	@RepeatableAnnotation(a=1,b=2,c=3)
	@RepeatableAnnotation(b=1,a=2,c=4)
	public static void add(int a, int b, int c)
	{
		if(c != a+b)
		{
			throw new ArithmeticException("Wrong");
		}
	}
}
