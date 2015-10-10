import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Compiler {

	public enum CompileResult {
		COMPILE_ERROR, COMPILE_SUCCESS
	}

	public static void main(String[] args) throws IOException {

		Compiler c = new Compiler();
		String javaFileString = "public class Solution { public boolean isPalindrome(int x) {if (x < 0) {return false;}int div = 1;while (x / div >= 10) {div = div * 10;}while (div >= 10) {if (x / div != x % 10) {return false;}x = (x % div) / 10;div = div / 100;}return true;}}";
		String javaFilePath = c.createJavaFile(javaFileString).getCanonicalPath();
		CompileResult cr = c.compile("java", javaFilePath);
		if (cr.equals(CompileResult.COMPILE_SUCCESS)) {
			System.out.println("Successfully Compiled the code");
		}
	}

	public CompileResult compile(String l, String javaFilePath) {
		System.out.println("Code compilation started...");
		ProcessBuilder p = null;
		boolean compiled = true;
		if (l.equals("java")) {
			p = new ProcessBuilder("javac", javaFilePath);
		}
		p.directory(new File("."));
		p.redirectErrorStream(true);

		try {
			Process pp = p.start();
			InputStream is = pp.getInputStream();
			String temp;
			try (BufferedReader b = new BufferedReader(new InputStreamReader(is))) {
				while ((temp = b.readLine()) != null) {
					compiled = false;
					System.out.println(temp);
				}
				pp.waitFor();
			}

			if (!compiled) {
				is.close();
				return CompileResult.COMPILE_ERROR;
			}
			is.close();
			return CompileResult.COMPILE_SUCCESS;

		} catch (IOException e) {
			System.out.println("in compile() " + e);
		} catch (InterruptedException e) {
			System.out.println("in compile() " + e);
		}

		return CompileResult.COMPILE_ERROR;
	}

	public File createJavaFile(String javaFileString) throws IOException {

		String javaFilePath = new File(".").getCanonicalPath() + File.separator + "Solution.java";
		File file = new File(javaFilePath);
		FileWriter out = new FileWriter(file);
		inputImports(out);
		out.write(javaFileString);
		out.flush();
		out.close();
		return file;

	}

	public void inputImports(FileWriter out) throws IOException {

		out.write("import java.util.*;");
		out.write("import java.lang.*;");

	}

}
