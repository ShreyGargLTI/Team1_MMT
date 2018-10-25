package login;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.LoginPOM;
import util.BrowserFactory;
import util.Constants;
import util.ReadExcel;

public class Login
{
	WebDriver driver;
	
	@Test(dataProvider = "loginCred")
	void runLogin(String username, String password) throws InterruptedException
	{
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);

		LoginPOM l = PageFactory.initElements(driver, LoginPOM.class);

		l.logIn(username, password);

		
	}

	@DataProvider(name = "loginCred")
	public String[][] readCred1() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readCred(Constants.PATH_EXCEL_LOGIN));
	}

	@AfterTest
	public void close()
	{
		driver.quit();
	}
}