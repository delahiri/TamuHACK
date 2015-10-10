package Parser;

import java.util.List;

public class Run {

	public static void main(String[] args) {
		InputParser inputParser = new InputParser("/Users/debaniklahiri/Documents/TamuHACK/TamuHACK/CodeRunner/src/main/java/Parser/input.txt");
		List<String> mainMethods = inputParser.getMainMethod("myMethod");

	}

}
