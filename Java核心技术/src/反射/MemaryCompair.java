package 反射;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MemaryCompair {
	public static void main(String [] args) {
//		successCompile();
//		failCompile();
		compileJavaFromString();
	}
	
	public static void compileJavaFromString() {
		StringBuilder sb = new StringBuilder();
		String className = "Hello";
		sb.append("public class Hello{\n");
		sb.append("public static void main(String [] args) {\n");
		sb.append("System.out.print(\"hello world\");\n");
		sb.append("}\n");
		sb.append("}");
		
		//编译上述字符串
		Class<?> c = compile(className,sb.toString());
		try {
			Object obj = c.newInstance();
			Method m = c.getMethod("main", String [].class);
			m.invoke(obj, new Object[] {new String[] {}});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Class<?> compile(String className, String javaCoders) {
		//将字符串包装为SimpleJavaFileObject对象
		JavaSourceFromString srcObject = new JavaSourceFromString(className, javaCoders);
		System.out.println(srcObject.getCode());
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();
		//设置编译的输出目录，并包装再options中
		String flag = "-d";
		String outDir = "";
		try {
			File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			outDir = classPath.getAbsolutePath() + File.separator;
			System.out.println("输出路径："+outDir);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Iterable<String> options = Arrays.asList(flag,outDir);
		//JavaCompiler.getTask方法：以future的任务形式(多线程)，来执行编译任务
		// 第一个参数：额外输出流，null表示默认使用system.err
		// 第二个参数：文件管理器，null表示编译器标准文件管理器
		// 第三个参数：诊断监听器，null表示使用编译器默认方法来报告诊断信息
		// 第四个参数：编译器参数，null表示无参数
		// 第五个参数：需要经过annotation处理的类名，null表示没有类需要annotation处理
		// 第六个参数：待编译的类
		JavaCompiler.CompilationTask task = 
				compiler.getTask(null, fileManager, diagnosticCollector, options, null, fileObjects);
		
		//等待编译结束
		boolean result = task.call();
		if (result == true) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else			
		{
			//打印诊断软件信息
            for  (Diagnostic diagnostic : diagnosticCollector.getDiagnostics())  
            {  
                System.out.println("Error on line: "   
                        + diagnostic.getLineNumber() + "; URI: "   
                        + diagnostic.getSource().toString());  
            }  
		}
		return null;
	}

	public static void failCompile() {
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result = compiler.run(null, null, err, "C:\\Users\\YongShuai L\\Desktop\\Java核心技术\\src\\反射\\Hello3.java");
		if (result == 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
			System.out.println(new String(err.toByteArray(), Charset.defaultCharset()));
		}
	}
	
	public static void successCompile() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		/**
		 * param1 输入流，null表示默认使用System.in
		 * param2 输出流，null表示默认使用System.out
		 * param3 错误流，null表示默认使用System.err
		 * param4 String...需要编译的文件名
		 */
		int result = compiler.run(null, null, null, "C:\\Users\\YongShuai L\\Desktop\\Java核心技术\\src\\反射\\Hello1.java","C:\\Users\\YongShuai L\\Desktop\\Java核心技术\\src\\反射\\Hello2.java");
		System.out.println(0 == result ? "success" : "fail");
	}
}
