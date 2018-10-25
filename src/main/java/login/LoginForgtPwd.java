package login;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.LoginfrtpwdPOM;
import util.BrowserFactory;
import util.Constants;
import util.ReadExcel;

public class LoginForgtPwd
{
	WebDriver driver;

	
	@Test(dataProvider = "loginfrtpwdCred")
	void runFgtPwd(String email, String setPwd) throws InterruptedException
	{
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);
		
		boolean flag = false;
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);

		LoginfrtpwdPOM lf = PageFactory.initElements(driver,
				LoginfrtpwdPOM.class);
		flag = lf.FrtPwd(email, setPwd);
		if (!flag)
		{
			flag = lf.setPassword(setPwd);
		}
	}

	@DataProvider(name = "loginfrtpwdCred")
	public String[][] readCred1() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readCred(Constants.PATH_EXCEL_FRGT_PASS));
	}

	@AfterTest
	public void close()
	{
		driver.quit();
	}

}