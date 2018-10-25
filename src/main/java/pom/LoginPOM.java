package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginPOM
{
	WebDriver driver;

	public LoginPOM(WebDriver driver)
	{
		this.driver = driver;

	}

	@FindBy(how = How.XPATH, using = "/html/body/div[2]/page-view/main/div/div/div[6]/div/div[5]/ul/li[9]/span/a[1]/span[2]")
	WebElement login_button;
	@FindBy(how = How.ID, using = "ch_login_email")
	WebElement emailId;
	@FindBy(how = How.ID, using = "ch_login_password")
	WebElement passwordId;
	@FindBy(how = How.ID, using = "ch_login_btn")
	WebElement loginButtonId;
	@FindBy(how = How.CLASS_NAME, using = "ch__marL5")
	WebElement heyButtonId;
	@FindBy(how = How.ID, using = "ch_logged-in")
	WebElement Logout;
	// @FindBy(how = How.XPATH, using = "//*[@id='ch_logged-inlogout']")
	// WebElement logoutXpath;
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/page-view/main/div/div/div[4]/div/div/div[1]/div/div[3]/span[2]")
	WebElement invalidMsg;

	@FindBy(how = How.XPATH, using = "By.xpath(\"(.//*[normalize-space(text()) and normalize-space(.)='Hey'])[1]/following::span[1]\")")
	WebElement LogOutXpath;

	public void logIn(String username, String password)
			throws InterruptedException
	{
		login_button.click();
		emailId.sendKeys(username);
		passwordId.sendKeys(password);
		loginButtonId.click();

		if (heyButtonId.isDisplayed())
		{
			String actual = heyButtonId.getText();
			String expected = "HEY";
			System.out.println("You have successfully logged in.");

			Thread.sleep(5000);

			// heyButtonId.click();
			// Actions builder = new Actions(driver);
			// builder.moveToElement(heyButtonId).build().perform();
			// builder.moveToElement(Logout).build().perform();
			//
			Actions action = new Actions(driver);

			action.moveToElement(Logout)
					.moveToElement(
							driver.findElement(By.id("ch_logged-inlogout")))
					.click().build().perform();
			// build().perform();
			// action.moveToElement(driver.findElement(By.id("ch_logged-inlogout"))).click().build().perform();

			if (login_button.isDisplayed())
			{
				System.out.println("You have successfully logged out.");
				assert true;
			}

		}

		if (invalidMsg.isDisplayed())
		{
			String actual = invalidMsg.getText();
			String exp1 = "Username and Password do not match.";
			String exp2 = "Invalid mobile number entered.";
			String exp3 = "Your account has been temporarily locked due to continuous failed attempts. Please try after 59 minutes";

			if (actual.equals(exp1))
			{
				System.out.println(actual);
				Assert.assertEquals(actual, exp1);
			}

			if (actual.equals(exp2))
			{
				System.out.println(actual);
				Assert.assertEquals(actual, exp2);
			}
			if (actual.equals(exp3))
			{
				System.out.println(actual);
				Assert.assertEquals(actual, exp3);
			}
		}

	}
}