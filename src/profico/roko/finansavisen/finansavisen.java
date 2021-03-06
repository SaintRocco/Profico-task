package profico.roko.finansavisen;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class finansavisen {
	public static WebDriver driver = null;
	static Boolean mouseHover;
	static Boolean T = true;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		String title = driver.getTitle();
		System.out.println(title);
		
		//open web app
		driver.navigate().to("https://finansavisen.no/?autorefresh=true");
		driver.manage().window().maximize();
		
		//Podcast drop-down menu----------------------------------------------------------------------------------------------------------------------------
		WebElement podcast = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/nav/ul/li[8]/div[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(podcast).perform();
				
		mouseHover = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/nav/ul/li[8]/div[1]")).isDisplayed();	
		
		Assert.assertEquals(mouseHover, T);
		System.out.println("\n~~Podcast dropdown menu dropped~~\n");
		Thread.sleep(3000);
				
		
		//Nyheter drop-down menu ----------------------------------------------------------------------------------------------------------------------------
		WebElement nyheter = driver.findElement(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(nyheter).perform();
		
		mouseHover = driver.findElement(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]/div[2]/ul/li/a/span")).isDisplayed();
		
		Assert.assertEquals(mouseHover, T);
		System.out.println("\n~~Nyheter dropdown menu dropped~~\n");
		
		//check number of options----------------------------------------------------------------------------------------------------------------------------
		int ExpectedElementCount = 12;
		List <WebElement> dropdown_options1 = driver.findElements(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]/div[2]/ul/li/a/span"));
		System.out.println("---------------------\n");
		for (WebElement webElement : dropdown_options1) {
			System.out.println(webElement.getText());
		}
		System.out.println("-------------------\n" + "COUNT: " + dropdown_options1.size());
		
		Assert.assertEquals(dropdown_options1.size(), ExpectedElementCount, "Expected element count not matched");
		System.out.println("\n~~Options number test passed~~\n");
		
		//check if "Sieste24timer" index is 0 & "Leder" index is 1-------------------------------------------------------------------------------------------
		List <WebElement> dropdown_options = driver.findElements(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]/div[2]/ul/li/a/span"));
		System.out.println("---------------------\n");
		
		String index1 = dropdown_options.get(1).toString();
		String index0 = dropdown_options.get(0).toString();
		String ExpectedIndex0 = "Siste 24 timer";
		String ExpectedIndex1 = "Leder";
		
		try {
			Assert.assertEquals("Expected element not found on index [0]", ExpectedIndex0, index0);
			Assert.assertEquals("Expected element not found on index [1]", ExpectedIndex1, index1);
			System.out.println("\n~~Index test passed~~\n");
		} catch (Exception e) {
			driver.quit();
		}
			
	}
}
