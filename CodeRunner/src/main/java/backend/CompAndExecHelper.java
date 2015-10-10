package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompAndExecHelper {

	public static File createJavaFile(StringBuilder javaFileString, String mainMethod) throws IOException {

		String javaFilePath = new File(".").getCanonicalPath() + File.separator + "Solution.java";
		File file = new File(javaFilePath);
		FileWriter out = new FileWriter(file);
		inputImports(out);
		StringBuilder local = javaFileString;
		int index = local.lastIndexOf("}");
		// System.out.println("INDEX : " + index);
		// System.out.println("CHAR : "+ javaFileString.charAt(index));
		local.deleteCharAt(index);
		System.out.println(local);
		// javaFileString.substring(0, javaFileString.)
		// System.out.println(javaFileString);
		out.write(local.toString());
		local.append("}");
		out.write("\n");
		out.write(mainMethod);
		out.write("\n}");
		out.flush();
		out.close();
		return file;

	}

	public static StringBuilder readFileToString() throws IOException {

		File dirs = new File(".");
		String filePath = dirs.getCanonicalPath() + File.separator + "Solution.java";

		StringBuilder stringBuff = new StringBuilder();
		List<String> fileString = Files.readAllLines(Paths.get(filePath));
		for (String line : fileString) {
			stringBuff.append(line);
			stringBuff.append(System.getProperty("line.separator"));
		}
		return stringBuff;

	}

	public static void inputImports(FileWriter out) throws IOException {

		out.write("import java.util.*;\n");
		out.write("import java.lang.*;\n");

	}

}
