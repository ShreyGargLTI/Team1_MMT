package login;

import pom.LoginWithGooglePOM;
import util.BrowserFactory;
import util.Constants;
import util.ReadExcel;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginwithGoogle
{

	WebDriver driver;

	@Test(dataProvider = "GoogleCred")
	void runLoginWithGoogle(String username, String password)
			throws InterruptedException
	{
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);

		LoginWithGooglePOM l = PageFactory.initElements(driver,
				LoginWithGooglePOM.class);
		l.googleAccount(username, password);

	}

	@DataProvider(name = "GoogleCred")
	public String[][] readCred1() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readCred(Constants.PATH_EXCEL_GOOGLE_LOGIN));
	}

	@AfterTest
	public void close()
	{
		driver.quit();
	}
}
