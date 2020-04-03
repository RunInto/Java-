package 注解.marker;

public class Foo {
	@Test
	public static void m1() {
	}

	public static void m2() {
	}

	@Test
	public static void m3() {
		throw new RuntimeException("i am Foo's m3 method!");
	}

	public static void m4() {
	}

	@Test
	public static void m5() {
	}

	public static void m6() {
	}

	@Test
	public static void m7() {
		throw new RuntimeException("i am Foo's m7 method!");
	}

	public static void m8() {
	}
}
