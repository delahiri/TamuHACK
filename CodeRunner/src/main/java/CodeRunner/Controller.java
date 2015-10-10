package CodeRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.CodeTester;

@RestController
public class Controller {

	Map<String, Integer> tokens = new HashMap<String, Integer>();

	@RequestMapping("/getResult")
	public Result getResult(@RequestParam(value = "id") String id, @RequestParam(value = "token") String token,
			@RequestParam(value = "lang") String lang) {

		if (validateToken(token)) {
			String program = fetchProgram(Long.parseLong(id));
			System.out.println(program);

			CodeTester ct = new CodeTester();
			ct.codeString = program;
			try {
				ct.testCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Result(0, Long.parseLong(id), null, null);

		}

		else
			return null;
	}

	@RequestMapping("/getToken")
	public Token getToken() {

		int leftLimit = 97;
		int rightLimit = 122;
		int targetStringLength = 20;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		tokens.put(generatedString, 0);

		return new Token(generatedString);
	}

	private String fetchProgram(long parseLong) {
		String program = null;
		try {
			String sql = "select solution from user_solutions where solutionid = ?";
			Connection con = ConnectionManager.getConnection();

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, (int) parseLong);

			ResultSet rset = psmt.executeQuery();
			while (rset.next()) {
				program = rset.getString(1);
			}

		} catch (SQLException e)

		{
			e.printStackTrace();
		}

		return program;
	}

	private boolean validateToken(String token) {
		return true;
		// return tokens.containsKey(token);
	}
}
