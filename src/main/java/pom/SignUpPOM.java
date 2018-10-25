package pom;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebElement;

public class SignUpPOM
{
	private static final TimeUnit SECONDS = null;

	WebDriver driver;

	@FindBy(how = How.ID, using = "ch_signup_icon")
	WebElement signUP_button;

	@FindBy(how = How.ID, using = "ch_signup_email")
	WebElement emailId;

	@FindBy(how = How.ID, using = "ch_signup_phone")
	WebElement phoneId;

	@FindBy(how = How.ID, using = "ch_signup_password")
	WebElement passwordId;

	@FindBy(how = How.ID, using = "ch_signup_btn")
	WebElement clickButtuonId;

	@FindBy(how = How.ID, using = "ch_mobile_number")
	WebElement verifyMobNo;

	@FindBy(how = How.ID, using = "ch_otpButton_hdr")
	WebElement verifyButton;

	@FindBy(how = How.CSS, using = "span.ch__marL5:nth-child(2)")
	WebElement heyButtonId;

	@FindBy(how = How.ID, using = "ch_OTP_token")
	WebElement OTPtext;

	@FindBy(how = How.XPATH, using = "(.//*[normalize-space(text()) and normalize-space(.)='Enter OTP'])[1]/following::button[1]")
	WebElement OTPbutton;

	@FindBy(how = How.CSS, using = ".hdr_verifyMobText")
	WebElement Errmsg_invalidMobNo;

	@FindBy(how = How.CSS, using = "#ch_signup_password_error > span:nth-child(3)")
	WebElement Errmsg_passwordchar;

	@FindBy(how = How.CSS, using = "#ch_signup_error > span:nth-child(3)")
	WebElement Errmsg_alreadyreg;

	public SignUpPOM(WebDriver driver)
	{
		this.driver = driver;

	}

	// @SuppressWarnings("deprecation")
	public boolean signUp(String email, String phNum, String password)
			throws InterruptedException
	{
		boolean signUpFlag = false;
		signUP_button.click();
		emailId.sendKeys(email);
		phoneId.click();

		phoneId.sendKeys(Keys.BACK_SPACE);
		phoneId.sendKeys(Keys.BACK_SPACE);
		phoneId.sendKeys(Keys.BACK_SPACE);
		phoneId.click();
		phoneId.sendKeys(phNum);
		passwordId.sendKeys(password);
		clickButtuonId.click();
		Thread.sleep(3000);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if ((Errmsg_alreadyreg).isDisplayed())
		{
			String actual = Errmsg_alreadyreg.getText();
			String exp1 = "Sorry!This Email is already Registered.Please choose a different email.";
			System.out.println(exp1);
			signUpFlag = true;
			Assert.assertEquals(actual, exp1);

		}

		if ((Errmsg_passwordchar).isDisplayed())
		{
			String actual = Errmsg_passwordchar.getText();
			String exp1 = "The password should be minimum of 6 characters";
			String exp2 = "The password should be alphanumeric";
			if (actual.equals(exp1))
			{
				System.out.println(exp1);
				Assert.assertEquals(actual, exp1);
				signUpFlag = true;
			}
			else
			{
				System.out.println(exp2);
				Assert.assertEquals(actual, exp2);
				signUpFlag = true;
			}

		}

		Thread.sleep(5000);

		if (driver.findElement(By.id("ch_mobile_number")).isDisplayed())
		{
			verifyMobNo.sendKeys(phNum);
			verifyButton.click();

			if ((Errmsg_invalidMobNo).isDisplayed())
			{
				String exp1 = Errmsg_invalidMobNo.getText();
				System.out.println(exp1);
				String actual = "Please enter a valid Phone Number";
				Assert.assertEquals(actual, exp1);
				signUpFlag = true;
			}

			else
			{
				String nameExpected = heyButtonId.getText();
				System.out.println("SUCCESSFULLY SIGNED UP!!! ");
				Assert.assertEquals(nameExpected, "HEY");
			}
		}
		return signUpFlag;
	}

	public void getOtpButton() throws InterruptedException
	{
		Thread.sleep(30000);
		OTPbutton.click();
	}

	public void tearDown()
	{
		driver.quit();
	}
}