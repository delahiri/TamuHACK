package CodeRunner;

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

@RestController
public class Controller {
	
	Map<String,Integer> tokens = new HashMap<String,Integer>();
	


    @RequestMapping("/getResult")
    public Result getResult(@RequestParam(value="id") String id, @RequestParam(value="token") String token, @RequestParam(value="lang") String lang) {
    	
    	
    if(validateToken(token))
    {
    	
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
            int randomLimitedInt = leftLimit + (int) 
              (new Random().nextFloat() * (rightLimit - leftLimit));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        tokens.put(generatedString,0);
     
   
    	return new Token(generatedString);
    }
    
    
    

	private String fetchProgram(long parseLong) 
	{
		String program = null;
		try {
		String sql ="select input from USERINPUT where id = ?";
		Connection con = ConnectionManager.getConnection();
		
		
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setLong(0, parseLong);
			
			ResultSet rset = psmt.executeQuery();
			while(rset.next())
			{
				 program = rset.getString(0);
			}
			
		} catch (SQLException e) 
		
		{
			e.printStackTrace();
		}
		
		return program;
	}

	private boolean validateToken(String token) {
		return tokens.containsKey(token);
	}
}
