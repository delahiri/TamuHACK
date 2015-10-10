package backend;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Compiler {

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
			String temp = null;
			try (BufferedReader b = new BufferedReader(new InputStreamReader(is))) {
				while ((temp = b.readLine()) != null) {
					compiled = false;
				}
				pp.waitFor();
			}

			if (!compiled) {
				is.close();
				return CompileResult.COMPILE_ERROR;
			}
			is.close();
			System.out.println("Code Compiled Successfully!");
			return CompileResult.COMPILE_SUCCESS;

		} catch (IOException e) {
			System.out.println("Compilation Failed : " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Compilation Failed : " + e.getMessage());
		}

		return CompileResult.COMPILE_ERROR;
	}

}
