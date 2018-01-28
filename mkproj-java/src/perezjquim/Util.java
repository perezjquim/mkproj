package perezjquim;
import java.io.*;

public class Util
{
	public static void writeFile(String destination,String data)
	{
		try
		{
			PrintWriter out = new PrintWriter(destination,"UTF-8");
			out.println(data);
			out.close();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{ e.printStackTrace(); }
	}
}
