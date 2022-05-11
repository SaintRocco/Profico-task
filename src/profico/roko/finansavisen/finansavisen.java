package profico.roko.finansavisen;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class finansavisen {
	public static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		String title = driver.getTitle();
		System.out.println(title);
		
		//open web app
		driver.navigate().to("https://finansavisen.no/?autorefresh=true");
		driver.manage().window().maximize();
		
		//Nyheter drop-down menu
		WebElement nyheter = driver.findElement(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]"));
		Actions action = new Actions(driver);
		action.moveToElement(nyheter).perform();
		
		//select option
		WebElement option1 = driver.findElement(By.xpath("//*[@id=\"js-expand-menu\"]/div/nav/ul/li[2]/div[2]/ul/li[11]"));
		Actions action1 = new Actions(driver);
		action.moveToElement(option1).perform();
	
		WebElement option = driver.findElement(By.id("option"));
		Select dropdown = new Select(option);
		
		dropdown.selectByVisibleText("Blogg");
	}
}
