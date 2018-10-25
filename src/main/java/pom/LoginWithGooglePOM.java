package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginWithGooglePOM
{
	WebDriver driver;

	public LoginWithGooglePOM(WebDriver driver)

	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "/html/body/div[2]/page-view/main/div/div/div[6]/div/div[5]/ul/li[9]/span/a[1]/span[2]")
	WebElement login_button;
	@FindBy(how = How.XPATH, using = "//*[@id='ch_login_google']")
	WebElement loginWithGoogleId;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/form/content/section/div/content/div[1]/div/div[2]/div[2]")
	WebElement invalidPwd;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/form/content/section/div/content/div[1]/div/div[2]/div[2]")
	WebElement invalidId;

	public void googleAccount(String username, String password)
			throws InterruptedException
	{
		WebElement googleID, googlePass;
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow + " parent window");

		login_button.click();

		Thread.sleep(3000);
		loginWithGoogleId.click();

		for (String winHandle : driver.getWindowHandles())
		{
			if (!winHandle.equalsIgnoreCase(parentWindow))
			{

				System.out.println(winHandle + " winHandle window");

				driver.switchTo().window(winHandle);

				Thread.sleep(5000);
				googleID = driver.findElement(By.id("identifierId"));
				googleID.sendKeys(username);

				driver.findElement(By.id("identifierNext")).click();

				Thread.sleep(5000);
				googlePass = driver.findElement(By.name("password"));
				googlePass.sendKeys(password);

				driver.findElement(By.id("passwordNext")).click();

				if (invalidPwd.isDisplayed())
				{

					String actual = invalidPwd.getText();
					String expected = "Wrong password. Try again or click Forgot password to reset it.";
					System.out.println(actual);
					Assert.assertEquals(actual, expected);
				}

			}

		}

		Thread.sleep(5000);
		driver.switchTo().window(parentWindow);
		driver.close();
	}
}
