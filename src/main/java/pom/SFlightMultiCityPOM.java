package pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class SFlightMultiCityPOM
{
	WebDriver driver;
	String baseURL;
	int listNum = 1;
	int num = 1;
	int prev_month = 10;
	int prev_year = 2018;

	public SFlightMultiCityPOM(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//label[@for='switch__input_3']")
	WebElement multiCity_button;
	@FindBy(how = How.ID, using = "hp-widget__sfrom")
	WebElement From;
	@FindBy(how = How.ID, using = "hp-widget__sTo")
	WebElement To;
	@FindBy(how = How.ID, using = "hp-widget__depart")
	WebElement DepartDate;
	@FindBy(how = How.ID, using = "js-multiCitySearchTo_1")
	WebElement To1;

	@FindBy(how = How.CSS, using = "#hp-widget__paxCounter_pot")
	WebElement Passengers_Class;
	@FindBy(how = How.ID, using = "ModifySearchBtn")
	WebElement searchButton;
	// @FindBy(how = How.ID, using = "js-multiCitySearchTo_1" )WebElement
	// multiCityTo_1;
	@FindBy(how = How.XPATH, using = "//input[contains(@class,'multiCitySearchDepart1 multiCityDate checkSpecialCharacters')]")
	WebElement DepartDate1;
	@FindBys(@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div[3]/div/div[5]/div/div[1]/table/tbody/tr/td"))
	List<WebElement> departDateList1;
	@FindBy(how = How.CSS, using = "#deny")
	WebElement denyAlert;
	@FindBy(how = How.ID, using = "hp-widget__sTo_error")
	WebElement To_displayCityError;
	@FindBy(how = How.ID, using = "js-widget__sTo_Multicity_1_error")
	WebElement To_displayCityError2;
	@FindBy(how = How.ID, using = "hp-widget__paxCounter_error")
	WebElement To_displayPassengersError;

	public boolean searchFlight(String from, String to, String to1,
			String DepartDatee, String DepartDatee1, String Passenger,
			String Classs) throws InterruptedException
	{
		multiCity_button.click();
		boolean flag = false;
		From.click();
		From.sendKeys(from);
		To.click();
		To.sendKeys(to);
		Thread.sleep(2000);
		To.sendKeys(Keys.ENTER);

		boolean flag1 = verifyDestination(from, to);
		/*
		 * if(from.equals(to)){
		 * 
		 * if(To_displayCityError.isEnabled()){ String actual_errorDisplay =
		 * To_displayCityError.getText(); String expected_errorDisplay =
		 * "The 'Departure City' and 'Destination City' cannot be same. Please re-type."
		 * ; assertEquals(actual_errorDisplay,expected_errorDisplay); flag =
		 * true; } }
		 */

		checkPopup();

		DepartDate.click();

		int day = Integer.parseInt(DepartDatee.substring(0, 2));
		int month = Integer.parseInt(DepartDatee.substring(3, 5));
		int year = Integer.parseInt(DepartDatee.substring(6, 10));
		listNum += 1;
		DepartDateSelect(day, month, year);

		Passengers_Class.click();
		Thread.sleep(2000);

		String adult = Passenger.substring(0, 1);
		String children = Passenger.substring(2, 3);
		String infant = Passenger.substring(4, 5);
		boolean flag2 = Passengers(adult, children, infant, Classs);
		Thread.sleep(2000);

		To1.click();
		To1.sendKeys(to1);

		Thread.sleep(2000);
		To1.sendKeys(Keys.ENTER);

		boolean flag3 = verifyDestination(to, to1);
		/*
		 * if(to.equals(to1)){
		 * 
		 * if(To_displayCityError2.isEnabled()){ String actual_errorDisplay =
		 * To_displayCityError2.getText(); String expected_errorDisplay =
		 * "The 'Departure City' and 'Destination City' cannot be same. Please re-type."
		 * ; assertEquals(actual_errorDisplay,expected_errorDisplay); flag =
		 * true; } }
		 */

		DepartDate1.click();
		int day1 = Integer.parseInt(DepartDatee1.substring(0, 2));
		int month1 = Integer.parseInt(DepartDatee1.substring(3, 5));
		int year1 = Integer.parseInt(DepartDatee1.substring(6, 10));
		listNum += 1;
		DepartDateSelect(day1, month1, year1);

		return (flag1 || flag2 || flag3);

	}

	boolean verifyDestination(String from, String to)
	{
		boolean flagDestination = false;
		if (from.equals(to))
		{

			if (To_displayCityError2.isEnabled())
			{
				String actual_errorDisplay = To_displayCityError2.getText();
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
			if (To_displayPassengersError.isDisplayed())
			{
				System.out.println("entered");
				String actual_passengersErrorDisplay = To_displayPassengersError
						.getText();
				System.out.println(actual_passengersErrorDisplay);
				// System.out.println("bl;ahblah");
				String expected_passengersErrorDisplay = "Total guest count cannot exceed 9";
				assertEquals(actual_passengersErrorDisplay,
						expected_passengersErrorDisplay);
				flagPassengers = true;
			}
		}
		return flagPassengers;
	}

	boolean Passengers(String adult, String children, String infant,
			String classs) throws InterruptedException
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
		if (noAdult + noChildren > 9)
		{
			Thread.sleep(2000);
			flagPassengers = verifyPassenger();
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[2]/div[2]/ul/li["
						+ children + "]"))
				.click();
		if (noAdult + noChildren + noInfant > 9)
		{
			Thread.sleep(2000);
			flagPassengers = verifyPassenger();
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[3]/div[2]/ul/li["
						+ infant + "]"))
				.click();
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
			throws InterruptedException
	{
		listNum += 1;
		String DDL1 = "/html/body/div[2]/div[3]/div[3]/div/div[";
		String DDL2 = "]/div/div[1]/table/tbody/tr/td";
		String DDL = DDL1 + listNum + DDL2;

		String Arrow1 = "/html/body/div[2]/div[3]/div[3]/div/div[";
		String Arrow2 = "]/div/div[2]/div/a/span";
		String Arrow = Arrow1 + listNum + Arrow2;

		if (month != prev_month)
		{
			int num = (month - prev_month + 12 * (year - prev_year));

			for (int i = 0; i < num; i++)
			{
				driver.findElement(By.xpath(Arrow)).click();
			}
		}
		else
		{

		}
		prev_month = month;
		prev_year = year;

		List<WebElement> departDateList = driver.findElements(By.xpath(DDL));
		int total_dates = departDateList.size();

		Thread.sleep(1000);

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

	public boolean addCity(String cityTo, String DepartDate)
			throws InterruptedException
	{
		boolean flag = false;

		int day = Integer.parseInt(DepartDate.substring(0, 2));
		int month = Integer.parseInt(DepartDate.substring(3, 5));
		int year = Integer.parseInt(DepartDate.substring(6, 10));

		String To_displayAddCityError1 = "js-widget__sTo_Multicity_";
		String To_displayAddCityError2 = "_error";
		WebElement To_displayAddCityError = driver.findElement(
				By.id(To_displayAddCityError1 + num + To_displayAddCityError2));

		num += 1;

		driver.findElement(By.id("addModifyBtn")).click();
		Thread.sleep(2000);

		WebElement multiCityTo = driver
				.findElement(By.id("js-multiCitySearchTo_" + num));

		multiCityTo.click();
		multiCityTo.sendKeys(cityTo);

		Thread.sleep(1000);
		// To.sendKeys(Keys.ENTER);
		// multiCityTo.sendKeys(Keys.ENTER);

		String DDK1 = "/html/body/div[2]/div[3]/div[2]/div/div[";
		String DDK2 = "]/div[3]";

		String DDK = DDK1 + num + DDK2;
		driver.findElement(By.xpath(DDK)).click();

		// System.out.println(multiCityTo.getAttribute("value"));
		System.out.println(To1.getAttribute("value"));
		// if(cityTo.equals(to1)){
		if (To1.getAttribute("value").contains(cityTo))
		{
			// if(multiCityTo.getAttribute("value").equals(To1.getAttribute("value"))){
			if (To_displayAddCityError.isEnabled())
			{
				String actual_errorDisplay = To_displayAddCityError.getText();
				System.out.println("blahblah");
				System.out.println(actual_errorDisplay);
				String expected_errorDisplay = "The 'Departure City' and 'Destination City' cannot be same. Please re-type.";

				assertEquals(actual_errorDisplay, expected_errorDisplay);
				flag = true;

				// System.out.println("error found");
				// driver.quit();
			}
		}

		driver.findElement(By.xpath(DDK)).click();

		DepartDateSelect(day, month, year);
		// System.out.println(flag);
		return flag;
	}

	public void search() throws InterruptedException
	{
		searchButton.click();
		Thread.sleep(10000);
	}

	public void checkPopup()
	{
		try
		{
			WebElement popup = driver.findElement(By.id("webpush-bubble"));
			if (popup.isDisplayed())
			{
				System.out.println(popup.isDisplayed());
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

	public void selectFlight() throws InterruptedException
	{
		Thread.sleep(30000);
		// JavascriptExecutor js = (JavascriptExecutor) driver;

		/*
		 * for(int i=1; i < 5; i++){ WebElement Price_webelement =
		 * driver.findElement(By.xpath(
		 * "/html/body/div[4]/div/div[1]/div[1]/div[3]/div[3]/div/div[5]/div/div["
		 * +i+"]/div/div[1]/div[2]/div/div[3]/div[4]/span/p/b"));
		 * js.executeScript("arguments[0].scrollIntoView();", Price_webelement);
		 * String Price = Price_webelement.getText(); System.out.println(Price);
		 * }
		 */

		WebElement book = driver.findElement(By.cssSelector(
				"div.pkj_listing_row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > p:nth-child(2) > a:nth-child(1)"));
		// js.executeScript("arguments[0].scrollIntoView();",book);
		book.click();
		Thread.sleep(10000);
	}
}