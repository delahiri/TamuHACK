package backend;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Executor {

	public static ExecutorResult execute(String l, long timeInMillis) throws IOException {

		System.out.println("Code started executing.");
		ProcessBuilder p = null;
		if (l.equals("java")) {
			p = new ProcessBuilder("java", "Solution");
		}
		p.directory(new File("."));
		p.redirectErrorStream(true);
		String fileDir = new File(".").getCanonicalPath();
		String filePath = fileDir + File.separator + "out.txt";
		File out = new File(filePath);
		p.redirectOutput(out);
		if (out.exists())
			System.out.println("Output file generated " + out.getAbsolutePath());
		try {
			Process pp = p.start();
			if (!pp.waitFor(timeInMillis, TimeUnit.MILLISECONDS)) {
				return ExecutorResult.TLE;
			}
			int exitCode = pp.exitValue();
			if (exitCode != 0) {
				System.out.println("Code Execution Failed!");
				return ExecutorResult.RUN_ERROR;
			}
		} catch (IOException ioe) {
			System.err.println("in execute() " + ioe);
		} catch (InterruptedException ex) {
			System.err.println(ex);
		} finally {
			new File(fileDir + File.separator + "Solution.class").delete();
		}
		System.out.println("Code execution finished!");
		return ExecutorResult.RUN_SUCCESS;
	}

}
