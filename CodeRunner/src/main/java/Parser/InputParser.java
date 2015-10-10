package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class InputParser {
	
	String fileName;
	
	public InputParser(String fileName)
	{
		this.fileName = fileName;
	}
	
	public List<String> getMainMethod(String methodName)
	{
		List<String> mainMethods = null;
		String mainMethod = "public static void main(String args[])\n {\n";
		try 
		{
			
		BufferedReader br = new BufferedReader(new FileReader(this.fileName));
		String line = br.readLine();
		StringBuilder prog = null;
		
		mainMethods = new ArrayList<String>();
		
		while(line != null)
		{
			prog = new StringBuilder();
			StringBuilder mainBuilder = null;
			JSONObject json = new JSONObject(line);
			
			JSONArray arr = json.getJSONArray("variables");
			for(int i=0;i<arr.length();i++)
			{
				JSONObject obj = (JSONObject)arr.get(i);
				
				String type = (String)obj.get("type");
				String value = (String)obj.get("value");
				
				String stmt = generateLine(type,value,i)+"\n";
				prog.append(stmt);
			
			}
			mainBuilder = new StringBuilder(mainMethod);
			mainBuilder.append(prog.toString()+"\n");
			mainBuilder.append(methodName+"(");
			for(int i=0;i<arr.length();i++)
			{
				mainBuilder.append((char)('a'+i));
				if(i<=arr.length()-2);
				mainBuilder.append(",");
			}
			mainBuilder.append(");");
			mainBuilder.append("\n}");
			mainMethods.add(mainBuilder.toString());
			line = br.readLine();
		}
		br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainMethods;
	}

	private String generateLine(String type, String value, int i) {
		StringBuilder stmt = new StringBuilder();
		
		String variableName = ""+(char)('a'+ i);
		
		switch(type)
		{
			case "int":
				stmt.append("int "+variableName+"="+value+";");
				break;
				
			case "float":
				stmt.append("float "+variableName+"="+value+";");
				break;
				
			case "double":
				stmt.append("double "+variableName+"="+value+";");
				break;
				
			case "1DintArray":
				stmt.append("int[] "+variableName+"={"+value+"};");
				break;
				
			case "2DintArray":
				String[] values = value.split(",,");
				stmt.append("int[][] "+variableName+"={");
				for(int j=0;j<values.length;j++)
				{
					String row = values[j];
					stmt.append("{");
					stmt.append(row);
					stmt.append("}");
					if(j<=values.length-2)
					stmt.append(",");
					
				}
				stmt.append("};");
				break;
				
			default:
				
				break;
		}
		return stmt.toString();
		
		
	}

}
