
import java.io.IOException;

enum CompileResult {
	COMPILE_ERROR, COMPILE_SUCCESS
}

enum ExecutorResult {
	RUN_ERROR, RUN_SUCCESS, TLE
}

public class CodeTester {

	public static void main(String[] args) throws IOException {

		String javaFileString = CompAndExecHelper.readFileToString();
		String javaFilePath = CompAndExecHelper.createJavaFile(javaFileString).getCanonicalPath();
		Compiler compiler = new Compiler();
		CompileResult cr = compiler.compile("java", javaFilePath);
		if (CompileResult.valueOf("COMPILE_ERROR").equals(cr)) {
			System.out.println("Compilation failed!");
			System.exit(1);
		}
		if (CompileResult.valueOf("COMPILE_SUCCESS").equals(cr)) {
			System.out.println("Compilation Completed Successfully!");
		}
		ExecutorResult execResult = Executor.execute("java", 0);
		if (ExecutorResult.valueOf("TLE").equals(execResult)) {
			System.out.println("Time Limit Exceeded");
			System.exit(1);
		}

	}

}
