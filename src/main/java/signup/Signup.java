package signup;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import pom.SignUpPOM;
import util.BrowserFactory;
import util.Constants;
import util.ReadExcel;

public class Signup
{

	WebDriver driver;

	@Test(dataProvider = "signUpCred")
	void runSignUp(String email, String phNum, String password)
			throws InterruptedException
	{
		boolean flag = false;
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);

		SignUpPOM s = PageFactory.initElements(driver, SignUpPOM.class);

		flag = s.signUp(email, phNum, password);
		if (!flag)
		{
			s.getOtpButton();
		}
	}

	@DataProvider(name = "signUpCred")
	public String[][] readCred1() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readRegValues(Constants.PATH_EXCEL_SIGNUP));
	}

	@AfterTest
	public void close()
	{
		driver.quit();
	}
}
