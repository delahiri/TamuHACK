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

	public String codeString;

	public void testCode() throws IOException {
		StringBuilder javaFileString = new StringBuilder(codeString);
		List<String> mainMethodList = new ArrayList<String>();
		mainMethodList.add(
				"public static void main(String[] args) {Solution s = new Solution();System.out.println(s.giveSum(1,2));}");
		mainMethodList.add(
				"public static void main(String[] args) {Solution s = new Solution();System.out.println(s.giveSum(2,3));}");
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
