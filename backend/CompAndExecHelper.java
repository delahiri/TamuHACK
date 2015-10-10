import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompAndExecHelper {

	public static File createJavaFile(String javaFileString) throws IOException {

		String javaFilePath = new File(".").getCanonicalPath() + File.separator + "Solution.java";
		File file = new File(javaFilePath);
		FileWriter out = new FileWriter(file);
		inputImports(out);
		out.write(javaFileString);
		out.flush();
		out.close();
		return file;

	}

	public static String readFileToString() throws IOException {

		File dirs = new File(".");
		String filePath = dirs.getCanonicalPath() + File.separator + "Solution.java";

		StringBuilder stringBuff = new StringBuilder();
		List<String> fileString = Files.readAllLines(Paths.get(filePath));
		System.out.println(fileString);
		for (String line : fileString) {
			stringBuff.append(line);
			stringBuff.append(System.getProperty("line.separator"));
		}
		return stringBuff.toString();

	}

	public static void inputImports(FileWriter out) throws IOException {

		out.write("import java.util.*;");
		out.write("import java.lang.*;");

	}

}
