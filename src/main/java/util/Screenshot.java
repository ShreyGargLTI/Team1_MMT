/**
 * 
 */
package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Shrey
 *
 */
public class Screenshot
{
	/**
	 * 
	 * @param driver
	 * @param fileWithPath
	 */
	public static void takeSnap(WebDriver driver, String fileWithPath)
	{
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File src = scrShot.getScreenshotAs(OutputType.FILE);

		File dest = new File(fileWithPath);

		try
		{
			FileUtils.copyFile(src, dest);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
