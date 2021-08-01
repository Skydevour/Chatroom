package test1;

import java.io.Closeable;

//π§æﬂ¿‡
public class Close 
{
	public static void close(Closeable... target)
	{
		for(Closeable targets:target)
		{
			try
			{
				if(targets!=null)
					targets.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
