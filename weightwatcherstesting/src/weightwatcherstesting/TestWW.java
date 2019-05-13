package weightwatcherstesting;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestWW {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
	}
	@AfterTest
	 public void tearDown(){
	     driver.close();
	     driver.quit();
	 }

	@Test(priority=1)
	public void ChkWWPage() throws InterruptedException {
		driver.get("https://www.weightwatchers.com/us/");

		String actualTitle = driver.getTitle();
		String expTitle = "WW (Weight Watchers): Weight Loss & Wellness Help";
		Assert.assertEquals(actualTitle, expTitle);
	}

	@Test(priority=2)
	public void chkTitle() throws InterruptedException {
		driver.findElement(By.id("ela-menu-visitor-desktop-supplementa_find-a-studio")).click();
		SoftAssert softAssert = new SoftAssert();
		String pageTitle = driver.getTitle();
		String exPgTitle = "Find WW Studios & Meetings Near You | WW USA";
		try {
			softAssert.assertEquals(pageTitle, exPgTitle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Test");
		}
		softAssert.assertAll();
		Thread.sleep(3000);
	}

	@Test(priority=3)
	public void chkloc() {
		driver.findElement(By.id("meetingSearch")).click();
		driver.findElement(By.id("meetingSearch")).sendKeys("10011");
		driver.findElement(By.cssSelector(".spice-translated:nth-child(3)")).click();

		System.out.println(driver.findElement(By.cssSelector("#ml-1180510 > result-location > div > div.meeting-location__top > a > location-address > div > div > div.location__top")).getText());
        String actLocname=(driver.findElement(By.cssSelector("#ml-1180510 > result-location > div > div.meeting-location__top > a > location-address > div > div > div.location__top > div.location__name")).getText());
		driver.findElement(By.cssSelector("#ml-1180510 > result-location > div > div.meeting-location__top > a > location-address > div > div > div.location__top")).click();
		String getLocname=(driver.findElement(By.cssSelector(".location__name")).getText());
		SoftAssert softAssert = new SoftAssert();
		try {
			softAssert.assertEquals(actLocname, getLocname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Test");
		}
		
		softAssert.assertAll();
		String day=LocalDate.now().getDayOfWeek().name().substring(0, 3);
		List<WebElement> days=driver.findElements(By.xpath("//div[@class='hours-list-item-day']"));
		for(int i=0;i<days.size();++i)
		{
			if(days.get(i).getText().toUpperCase().equals(day))
			{
				String xpath="//ul[@class='hours-list list-unstyled hours-list--count-7']/li[@class='hours-list-item']["+i+"]/div[1]/div[2]/div";
				List<WebElement> times=driver.findElements(By.xpath(xpath));
				for(WebElement t:times)
				{
					System.out.println(t.getText());
				}
			}
		}
		printMeetings("MON",driver);
		}
	public void printMeetings(String day,WebDriver driver)
	{
		HashMap<String, Integer> data=new HashMap<String,Integer>();
		List<WebElement> days=driver.findElements(By.xpath("//div[@class='schedule-detailed-day']/div[1]"));
		
		for(int i=0;i<days.size();++i)
		{
			if(days.get(i).getText().toUpperCase().equals(day))
			{
				String xpath="//div[@class='schedule-detailed']/div[@class='schedule-detailed-day']["+(i+1)+"]/div[2]/div/div[2]";
				List<WebElement> persons=driver.findElements(By.xpath(xpath));
				for(WebElement e:persons)
				{
					if(data.isEmpty())
						data.put(e.getText(), 1);
					else
					{
						if(data.containsKey(e.getText()))
							data.put(e.getText(), data.get(e.getText())+1);
						else
							data.put(e.getText(), 1);
					}
				}
			}
		}
		for(@SuppressWarnings("unused") Map.Entry<String, Integer> m:data.entrySet())

		{
			
			System.out.println(m.getKey() + ":" + m.getValue());
		}
		
	}
	}
