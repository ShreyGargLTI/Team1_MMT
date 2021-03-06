package pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class SFlightTwoWayPOM
{
	WebDriver driver;
	String baseURL;

	public SFlightTwoWayPOM(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//label[@for='switch__input_2']")
	WebElement twoWay_button;
	@FindBy(how = How.ID, using = "hp-widget__sfrom")
	WebElement From;
	@FindBy(how = How.ID, using = "hp-widget__sTo")
	WebElement To;
	@FindBy(how = How.ID, using = "hp-widget__depart")
	WebElement DepartDate;
	@FindBy(how = How.ID, using = "hp-widget__return")
	WebElement ReturnDate;

	@FindBys(@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div[3]/div/div[3]/div/div[1]/table/tbody/tr/td"))
	List<WebElement> departDateList;
	@FindBys(@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div[3]/div/div[4]/div/div[1]/table/tbody/tr/td"))
	List<WebElement> ReturnDateList;
	@FindBy(how = How.CSS, using = "#hp-widget__paxCounter_pot")
	WebElement Passengers_Class;
	@FindBy(how = How.ID, using = "searchBtn")
	WebElement searchButton;
	@FindBy(how = How.ID, using = "deny")
	WebElement allowAlert;

	@FindBy(how = How.ID, using = "buckets_morning_dep")
	WebElement morningSlotdep;
	@FindBy(how = How.ID, using = "buckets_noon_dep")
	WebElement noonSlotdep;
	@FindBy(how = How.ID, using = "buckets_evening_dep")
	WebElement eveningSlotdep;
	@FindBy(how = How.ID, using = "buckets_night_dep")
	WebElement nightSlotdep;

	@FindBy(how = How.ID, using = "hp-widget__sTo_error")
	WebElement To_displayError;

	@FindBy(how = How.ID, using = "hp-widget__paxCounter_error")
	WebElement To_displayPassengersError;

	/*
	 * @FindBy(how = How.ID, using = "buckets_morning_ret") WebElement
	 * morningSlotret;
	 * 
	 * @FindBy(how = How.ID, using = "buckets_noon_ret") WebElement noonSlotret;
	 * 
	 * @FindBy(how = How.ID, using = "buckets_evening_ret") WebElement
	 * eveningSlotret;
	 * 
	 * @FindBy(how = How.ID, using = "buckets_night_ret") WebElement
	 * nightSlotret;
	 */

	public boolean searchFlight(String from, String to, String DepartDatee,
			String ReturnDatee, String Passenger, String Classs)
			throws InterruptedException
	{
		twoWay_button.click();
		boolean flag = false;
		From.click();
		From.sendKeys(from);
		To.click();
		To.sendKeys(to);
		Thread.sleep(2000);
		To.sendKeys(Keys.ENTER);

		// System.out.println(From.getText());
		// System.out.println(To.getText());
		//// if(From.getText().equalsIgnoreCase(To.getText())){

		Thread.sleep(2000);

		boolean flag1 = verifyDestination(from, to);
		System.out.println("Destination");
		System.out.println(flag1);

		Thread.sleep(2000);
		checkPopup();

		DepartDate.click();
		int departday = Integer.parseInt(DepartDatee.substring(0, 2));
		int departmonth = Integer.parseInt(DepartDatee.substring(3, 5));
		int departyear = Integer.parseInt(DepartDatee.substring(6, 10));
		DepartDateSelect(departday, departmonth, departyear);

		ReturnDate.click();
		int returnday = Integer.parseInt(ReturnDatee.substring(0, 2));
		int returnmonth = Integer.parseInt(ReturnDatee.substring(3, 5));
		int returnyear = Integer.parseInt(ReturnDatee.substring(6, 10));
		ReturnDateSelect(returnday, returnmonth, returnyear);

		Passengers_Class.click();
		Thread.sleep(2000);
		String adult = Passenger.substring(0, 1);
		String children = Passenger.substring(2, 3);
		String infant = Passenger.substring(4, 5);
		boolean flag2 = Passengers(adult, children, infant, Classs);
		Thread.sleep(2000);

		return (flag1 || flag2);

	}

	boolean verifyDestination(String from, String to)
	{
		boolean flagDestination = false;
		if (from.equals(to))
		{
			if (To_displayError.isEnabled())
			{
				String actual_errorDisplay = To_displayError.getText();
				String expected_errorDisplay = "The 'Departure City' and 'Destination City' cannot be same. Please re-type.";
				assertEquals(actual_errorDisplay, expected_errorDisplay);
				flagDestination = true;
			}
		}
		return flagDestination;
	}

	boolean verifyPassenger()
	{
		boolean flagPassengers = false;
		if (To_displayPassengersError.isEnabled())
		{
			String actual_passengersErrorDisplay = To_displayPassengersError
					.getText();
			String expected_passengersErrorDisplay = "Total guest count cannot exceed 9";
			assertEquals(actual_passengersErrorDisplay,
					expected_passengersErrorDisplay);
			flagPassengers = true;
		}
		return flagPassengers;
	}

	public boolean Passengers(String adult, String children, String infant,
			String classs)
	{
		boolean flagPassengers = false;
		int noAdult = Integer.parseInt(adult);
		int noChildren = Integer.parseInt(children);
		int noInfant = Integer.parseInt(infant);

		String economy = "Economy", premEco = "Premium Economy",
				business = "Business";
		int num = 0;
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[1]/div[2]/ul/li["
						+ adult + "]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[2]/div[2]/ul/li["
						+ children + "]"))
				.click();
		if (noAdult + noChildren > 9)
		{
			flagPassengers = verifyPassenger();
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[3]/div[2]/ul/li["
						+ infant + "]"))
				.click();
		if (noAdult + noChildren + noInfant > 9)
		{
			flagPassengers = verifyPassenger();
		}
		if (classs.equalsIgnoreCase(economy))
		{
			num = 1;
		}
		else if (classs.equalsIgnoreCase(premEco))
		{
			num = 2;
		}
		else if (classs.equalsIgnoreCase(business))
		{
			num = 3;
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[2]/ul/li[" + num
						+ "]"))
				.click();
		return flagPassengers;
	}

	void DepartDateSelect(int day, int month, int year)
	{
		int num = (month - 10 + 12 * (year - 2018));
		for (int i = 0; i < num; i++)
		{
			driver.findElement(By.xpath(
					"/html/body/div[2]/div[3]/div[3]/div/div[3]/div/div[2]/div/a/span"))
					.click();
		}
		int total_dates = departDateList.size();

		for (int i = 0; i < total_dates; i++)
		{
			String date;
			String date_price = departDateList.get(i).getText();
			if (date_price.length() >= 2)
			{
				date = date_price.substring(0, 2);
			}
			else
			{
				date = date_price.substring(0, 1);
			}
			if (date.equals("" + day))
			{
				departDateList.get(i).click();
				break;
			}
		}

	}

	void ReturnDateSelect(int day, int month, int year)
	{
		int num = (month - 10 + 12 * (year - 2018));
		for (int i = 0; i < num; i++)
		{
			driver.findElement(By.xpath(
					"/html/body/div[2]/div[3]/div[3]/div/div[4]/div/div[2]/div/a/span"))
					.click();
		}
		int total_dates = ReturnDateList.size();

		for (int i = 0; i < total_dates; i++)
		{
			String date;
			String date_price = ReturnDateList.get(i).getText();
			if (date_price.length() >= 2)
			{
				date = date_price.substring(0, 2);
			}
			else
			{
				date = date_price.substring(0, 1);
			}
			if (date.equals("" + day))
			{
				ReturnDateList.get(i).click();
				break;
			}
		}

	}

	public void checkPopup()
	{
		try
		{
			WebElement popup = driver.findElement(By.id("webpush-bubble"));
			if (popup.isDisplayed())
			{
				System.out.println(popup.isDisplayed());
				// driver.switchTo().frame("webpush-bubble");
				driver.switchTo().frame(popup);
				driver.findElement(By.id("deny")).click();
				driver.switchTo().defaultContent();
				System.out.println(popup.isDisplayed());
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void selectFlight(String journeyTimedep, String journeyTimeret)
			throws InterruptedException
	{
		Thread.sleep(10000);
		if (journeyTimedep.equals("Before 6AM"))
		{
			morningSlotdep.click();
		}
		else if (journeyTimedep.equals("6AM-12PM"))
		{
			noonSlotdep.click();
		}
		else if (journeyTimedep.equals("12PM-6PM"))
		{
			eveningSlotdep.click();
		}
		else if (journeyTimedep.equals("After 6AM"))
		{
			nightSlotdep.click();
		}

		/*
		 * if (journeyTimeret.equals("Before 6AM")) { morningSlotret.click(); }
		 * else if (journeyTimeret.equals("6AM-12PM")) { noonSlotret.click(); }
		 * else if (journeyTimeret.equals("12PM-6PM")) { eveningSlotret.click();
		 * } else if (journeyTimeret.equals("After 6AM")) {
		 * nightSlotret.click(); }
		 */
		Thread.sleep(4000);

		// String totalFlightsString =
		// driver.findElement(By.xpath("/html/body/div[5]/div/div[6]/div[5]/div[2]/div[2]/div/div/div/p[2]/span")).getText();
		// int totalFlights = Integer.parseInt(totalFlightsString.substring(0,
		// 2));

		/*
		 * for(int i=0; i < 10; i++){ int num = i+5; String Price =
		 * driver.findElement(By.xpath(
		 * "/html/body/div[5]/div/div[6]/div[5]/div[2]/div["
		 * +num+"]/div/div[2]/div[6]/p[1]/span[2]")).getText();
		 * System.out.println(Price); }
		 */
		// Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html/body/div[5]/div/div[3]/div[4]/div[2]/div[2]/div[1]/div[1]/div[2]/div/div/div[1]/div[1]/div[1]/div[1]/span[1]/span"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div[5]/div/div[3]/div[4]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[1]/div[1]/div[1]/span[1]/span"))
				.click();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Book")).click();
		Thread.sleep(10000);
	}

	public void search() throws InterruptedException
	{
		searchButton.click();
		Thread.sleep(10000);
	}

}