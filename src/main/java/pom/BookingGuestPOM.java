package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BookingGuestPOM
{
	WebDriver driver;

	public BookingGuestPOM(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@ng-model='rData.emailId']")
	WebElement email;
	@FindBy(how = How.CSS, using = ".col-lg-11 > span:nth-child(1)")
	WebElement restriction;
	@FindBy(how = How.LINK_TEXT, using = "Continue as Guest")
	WebElement guest_button;

	public void bookFlight(String email_name) throws InterruptedException
	{
		Thread.sleep(10000);
		email.sendKeys(email_name);
		restriction.click();
		restriction.click();
		Thread.sleep(4000);
		guest_button.click();

	}

}
