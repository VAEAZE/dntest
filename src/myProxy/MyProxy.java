package myProxy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {

	public static String rn = "\r\n";

	public static Object newProxyInstance(ClassLoader loader,
			Class<?>[] interfaces, MyInvocationHandler h) {

		// 我们要生一个java类
		String proxyJava = "package myProxy;"
				+ rn
				+ "import java.lang.reflect.Method;"
				+ rn
				+ "public final class $proxy0 extends MyProxy implements DataService"
				+ rn + "{" + rn + "MyInvocationHandler h;" + rn
				+ "public $proxy0(MyInvocationHandler h)" + rn + "{" + rn
				+ "this.h = h;" + rn + "}" + rn + createMethods(interfaces)
				+ rn + "}";

		String filePath = "E:/Workspaces/newWork/dntest/src/myProxy/$proxy0.java";
		File proxyFile = new File(filePath);

		try {
			FileWriter fw = new FileWriter(proxyFile);
			fw.write(proxyJava);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		proxyFile.delete();

		// 动态编译
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,
				null, null);

		Iterable unti = fileMgr.getJavaFileObjects(filePath);

		compiler.getTask(null, fileMgr, null, null, null, unti).call();

		// 加载到内存中
		MyClassLoader loader1 = new MyClassLoader(
				"E:/Workspaces/newWork/dntest/src/myProxy/");

		try {
			Class proxyClass = loader1.findClass("$proxy0");

			Constructor ct = proxyClass
					.getConstructor(MyInvocationHandler.class);

			return ct.newInstance(h);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String createMethods(Class<?>[] interfaces) {

		String methodStr = "";

		for (Class intf : interfaces) {
			Method[] methods = intf.getMethods();

			for (Method method : methods) {
				methodStr += "public final void "
						+ method.getName()
						+ "(String paramString) throws Exception"
						+ rn
						+ "{"
						+ rn
						+ "Method md = "
						+ intf.getName()
						+ ".class.getMethod(\""
						+ method.getName()
						+ "\",new Class[]{String.class});"
						+ rn
						+ "this.h.invoke(this, md, new Object[] { paramString });"
						+ rn + "}" + rn;
			}
		}

		return methodStr;
	}

	private static String createMethods1(Class<?>[] interfaces) {
		String methodStr = "";
		for (Class intf : interfaces) {
			Method[] methods = intf.getMethods();

			for (Method method : methods) {
				methodStr += "public final void " + method.getName()
						+ "(String paramString) throws Exception" + rn + "{"
						+ rn + "Method md = " + intf.getName()
						+ ".class.getMethod(\"" + method.getName()
						+ "\",new Class[]{String.class});" + rn
						+ "this.h.invoke(this,md,new Object[]{paramString});"
						+ rn + "}" + rn;
			}
		}

		return methodStr;
	}
}
