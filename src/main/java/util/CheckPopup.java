package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckPopup implements Runnable
{
	WebDriver driver;
	Thread main;

	public CheckPopup(WebDriver driver, Thread main)
	{
		this.driver = driver;
		this.main = main;
	}

	public void run()
	{
		checkPopup();

	}

	public void checkPopup()
	{
		synchronized (main)
		{
			while (true)
			{
				try
				{
					Thread.sleep(500);
					WebElement popup = driver.findElement(By.id("webpush-bubble"));
					if (popup.isDisplayed())
					{
//						if (driver.findElement(By.cssSelector(".webpush-prompt"))
//								.isDisplayed())
//						{
							main.wait();
							WebElement denyBtn = driver.findElement(By.id("deny"));
							driver.switchTo().frame(popup);
							denyBtn.click();
							driver.switchTo().defaultContent();
							notify();
							System.out.println(popup.isDisplayed());
//						}

					}
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}

			}
			
		}
	}

}
