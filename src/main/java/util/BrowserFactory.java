/**
 * 
 */
package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author Shrey
 *
 */
public class BrowserFactory
{
	static WebDriver driver = null;
	
	/**
	 * 
	 * @param browserName 
	 * @return driver
	 */
	public static WebDriver startBrowser(String browserName)
	{		
		if(browserName.equalsIgnoreCase(Constants.CHROME))
		{
			System.setProperty(Constants.KEY_CHROME_DRIVER, Constants.PATH_CHROME_DRIVER);
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase(Constants.FIREFOX))
		{
			System.setProperty(Constants.KEY_FIREFOX_DRIVER, Constants.PATH_FIREFOX_DRIVER);
			System.setProperty(Constants.KEY_FIREFOX_BIN, Constants.PATH_FIREFOX);
	        driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase(Constants.IE))
		{
			System.setProperty(Constants.KEY_IE_DRIVER, Constants.PATH_IE_DRIVER);
	        driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
}
