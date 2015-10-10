package backend;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

enum CompileResult {
	COMPILE_ERROR, COMPILE_SUCCESS
}

enum ExecutorResult {
	RUN_ERROR, RUN_SUCCESS, TLE
}

public class CodeTester {

	private final static StringBuilder javaFileString = new StringBuilder(
			"public class Solution {public boolean isPalindrome(int x) {if (x < 0) {return false;}int div = 1;while (x / div >= 10) {div = div * 10;}while (div >= 10) {if (x / div != x % 10) {return false;}x = (x % div) / 10;div = div / 100;}return true;}}");

	public static void main(String[] args) throws IOException {

		List<String> mainMethodList = new ArrayList<String>();
		mainMethodList.add(
				"public static void main(String[] args) {Solution s = new Solution();System.out.println(s.isPalindrome(1111));}");
		mainMethodList.add(
				"public static void main(String[] args) {Solution s = new Solution();System.out.println(s.isPalindrome(1112));}");
		for (String mainMethod : mainMethodList) {
			String javaFilePath = CompAndExecHelper.createJavaFile(javaFileString, mainMethod).getCanonicalPath();
			Compiler compiler = new Compiler();
			CompileResult cr = compiler.compile("java", javaFilePath);
			if (CompileResult.valueOf("COMPILE_ERROR").equals(cr)) {
				System.out.println("Compilation failed!");
				System.exit(1);
			}
			if (CompileResult.valueOf("COMPILE_SUCCESS").equals(cr)) {
				System.out.println("Compilation Completed Successfully!");
			}
			ExecutorResult execResult = Executor.execute("java", 500);
			if (ExecutorResult.valueOf("TLE").equals(execResult)) {
				System.out.println("Time Limit Exceeded");
				System.exit(1);
			}
		}

	}

}
