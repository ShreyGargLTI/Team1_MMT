package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader
{

	Properties p;

	public ConfigReader()
	{
		try
		{
			File src = new File("./configuration/config.property");
			FileInputStream fis = new FileInputStream(src);

			p = new Properties();
			p.load(fis);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public String getProperty(String key)
	{
		return p.getProperty(key);
	}
}
