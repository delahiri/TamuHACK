package CodeRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @RequestMapping("/getResult")
    public Result getResult(@RequestParam(value="id") String id, @RequestParam(value="token") String token, @RequestParam(value="lang") String lang) {
    	
    	
    if(validateToken(token))
    {
    //	String program = fetchProgram(Long.parseLong(id));
    	
    	
    	
    	return new Result(0, Long.parseLong(id), null, null);
    	
    }
    
    else 
    	return null;
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
		return true;
	}
}
