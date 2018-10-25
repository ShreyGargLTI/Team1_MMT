package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class BookingGuestPOM1
{
	WebDriver driver;

	public BookingGuestPOM1(WebDriver driver)
	{
		this.driver = driver;
	}

	// @FindBy(how=How.XPATH,using="/html/body/div[4]/div[1]/div[2]/div[4]/div[1]/div/div[1]/div/div/div/div/p[1]/span[2]/input")WebElement
	// firstName;
	// @FindBy(how=How.XPATH,using="/html/body/div[4]/div[1]/div[2]/div[4]/div[1]/div/div[1]/div/div/div/div/p[1]/span[3]/input")WebElement
	// lastName;
	// @FindBy(how=How.XPATH,using="/html/body/div[4]/div[1]/div[2]/div[4]/div[1]/div/div[1]/div/div/div/div/p[3]/span[2]/a[1]")WebElement
	// gender;
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[3]/p[2]/span[3]/input")
	WebElement Phone;
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[3]/div[7]/p/a")
	WebElement continue_button;

	// html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[3]/p[2]/span[3]/input
	// html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[3]/div[7]/p/a
	public void adultDetails(String firstname, String lastname, int noAdult,
			int child, int infant) throws InterruptedException
	{
		Thread.sleep(5400);

		Thread.sleep(4000);
		String adultFirst1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String adultFirst2 = "]/div/p[1]/span[2]/input";

		String adultLast1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String adultLast2 = "]/div/p[1]/span[3]/input";

		String gender1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String gender2 = "]/div/p[3]/span[2]/a[1]";

		for (int i = 1; i <= noAdult; i++)
		{
			String adultFirst = adultFirst1 + i + adultFirst2;
			WebElement FirstName = driver.findElement(By.xpath(adultFirst));
			FirstName.sendKeys(firstname);

			String adultLast = adultLast1 + i + adultLast2;
			WebElement LastName = driver.findElement(By.xpath(adultLast));
			LastName.sendKeys(lastname);

			String gender = gender1 + i + gender2;
			WebElement Gender = driver.findElement(By.xpath(gender));
			Gender.click();
		}
	}

	public void childDetails(String firstname, String lastname, int noAdult,
			int child, int infant, String childage) throws InterruptedException
	{
		Thread.sleep(5400);

		Thread.sleep(4000);
		String childFirst1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String childFirst2 = "]/div/p[1]/span[2]/input";

		String childLast1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String childLast2 = "]/div/p[1]/span[3]/input";

		String gender1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String gender2 = "]/div/p[3]/span[2]/a[1]";

		String age1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String age2 = "]/div/p[3]/span[3]/input";

		for (int i = 1 + noAdult; i <= child; i++)
		{
			String childFirst = childFirst1 + i + childFirst2;
			WebElement FirstName = driver.findElement(By.xpath(childFirst));
			FirstName.sendKeys(firstname);

			String childLast = childLast1 + i + childLast2;
			WebElement LastName = driver.findElement(By.xpath(childLast));
			LastName.sendKeys(lastname);

			String gender = gender1 + i + gender2;
			WebElement Gender = driver.findElement(By.xpath(gender));
			Gender.click();

			String Child = age1 + i + age2;
			WebElement ageChild = driver.findElement(By.xpath(Child));
			ageChild.sendKeys(childage);

		}
	}

	public void infantDetails(String firstname, String lastname, int noAdult,
			int child, int noInfant) throws InterruptedException
	{
		Thread.sleep(5400);

		Thread.sleep(4000);
		String infantFirst1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String infantFirst2 = "]/div/p[1]/span[2]/input";

		String infantLast1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String infantLast2 = "]/div/p[1]/span[3]/input";

		String gender1 = "/html/body/div[4]/div[2]/div[2]/div[4]/div[1]/div/div[1]/div/div/div[";
		String gender2 = "]/div/p[3]/span[2]/a[1]";

		for (int i = 1 + noAdult + child; i <= noInfant; i++)
		{
			String infantFirst = infantFirst1 + i + infantFirst2;
			WebElement FirstName = driver.findElement(By.xpath(infantFirst));
			FirstName.sendKeys(firstname);

			String infantLast = infantLast1 + i + infantLast2;
			WebElement LastName = driver.findElement(By.xpath(infantLast));
			LastName.sendKeys(lastname);

			String gender = gender1 + i + gender2;
			WebElement Gender = driver.findElement(By.xpath(gender));
			Gender.click();

			Select ageInfant = new Select(driver.findElement(By.className(
					"input-md form-control light_gray ng-valid ng-touched ng-dirty ng-valid-parse")));
			ageInfant.selectByValue("1");
		}
	}

	public void continue_but(String phone) throws InterruptedException
	{
		Phone.sendKeys(phone);
		continue_button.click();
		Thread.sleep(4000);
	}
}
