package 注解.document;

public class Main {

	public static void main(String arg[]) {
	      new Main().f1();
	      new Main().f2();
	   }
	   @CommonAnnotation (value="Hello CommonAnnotation")
	   public void f1() {
	      System.out.printf("111111111111");
	   }
	   @DocumentedAnnotation("Hello DocumentedAnnotation")
	   public void f2() {
	      System.out.printf("222222222222");
	   }
}
