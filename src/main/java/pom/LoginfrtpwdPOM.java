package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginfrtpwdPOM
{
	WebDriver driver;

	public LoginfrtpwdPOM(WebDriver driver)
	{
		this.driver = driver;

	}

	@FindBy(how = How.XPATH, using = "/html/body/div[2]/page-view/main/div/div/div[6]/div/div[5]/ul/li[9]/span/a[1]/span[2]")
	WebElement login_button;
	@FindBy(how = How.ID, using = "ch_login_forgetpassword")
	WebElement forgotPasswordId;
	@FindBy(how = How.ID, using = "ch_forgetpwd_email")
	WebElement forgotPwdemailId;
	@FindBy(how = How.ID, using = "ch_forgetpwd_submit")
	WebElement submitButtonId;
	@FindBy(how = How.XPATH, using = "(.//*[normalize-space(text()) and normalize-space(.)='Password reset OTP sent!'])[1]/following::button[1]")
	WebElement verifyButtonXpath;
	@FindBy(how = How.ID, using = "ch_set_new_password")
	WebElement setPwdId;
	@FindBy(how = How.CSS, using = "#set_new_password > button:nth-child(3)")
	WebElement submitbutton2Id;
	@FindBy(how = How.ID, using = "ch_forgetpwd_email_error")
	WebElement invalidAccId;
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/page-view/main/div/div/div[3]/div[3]/div[2]/p[1]")
	WebElement invalidOtpXpath;

	@FindBy(how = How.XPATH, using = "(.//*[normalize-space(text()) and normalize-space(.)='Set new password'])[1]/following::button[1]")
	WebElement changePass;

	@FindBy(how = How.ID, using = "ch_forgotpwd_sent_text")
	WebElement successMsg;

	public boolean FrtPwd(String email, String setPwd)
			throws InterruptedException
	{
		boolean flag = false;

		login_button.click();
		forgotPasswordId.click();
		forgotPwdemailId.sendKeys(email);
		System.out.println("Hello");
		submitButtonId.click();

		if (invalidAccId.isDisplayed())
		{
			String actual = invalidAccId.getText();
			System.out.println(actual);

			String expected = "User provided is not registered with MakeMyTrip.";

			Assert.assertEquals(actual, expected);
			flag = true;
		}

		Thread.sleep(30000);
		verifyButtonXpath.click();

		if (invalidOtpXpath.isDisplayed())
		{
			String actual = invalidOtpXpath.getText();
			System.out.println(actual + "actual message");
			String expected = "Invalid OTP";
			Assert.assertEquals(actual, expected);
			flag = true;
		}
		return flag;

	}

	public boolean setPassword(String setPwd) throws InterruptedException
	{
		boolean setPassFlag = false;
		setPwdId.sendKeys(setPwd);
		Thread.sleep(10000);

		changePass.click();

		if (successMsg.isDisplayed())
		{
			String expected = "Your password has been updated, please use your new password to login now!";
			Assert.assertEquals(successMsg.getText().trim(), expected);
		}
		return setPassFlag;
	}
}
