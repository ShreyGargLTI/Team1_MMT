package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BookingGuestPOM2
{
	WebDriver driver;

	public BookingGuestPOM2(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/div[2]/div[4]/div[1]/div/div[1]/div/div/div/div/p[1]/span[2]/input")
	WebElement seat_button;

	public void seatselect() throws InterruptedException
	{
		seat_button.click();
	}
}