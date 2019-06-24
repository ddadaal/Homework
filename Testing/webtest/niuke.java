import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Example {

    // Mooctest Selenium Example


    // <!> Check if selenium-standalone.jar is added to build path.
	
	public static WebDriver driver;

    public static void test(WebDriver driver) {
        // TODO Test script
        // eg:driver.get("https://www.baidu.com/")
        // eg:driver.findElement(By.id("wd"));
    	Example.driver = driver;
    
    	driver.get("https://www.nowcoder.com");
    	
    	driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
    	
    	// 5
    	selector("body > div.nk-container > div.nowcoder-header.js-nowcoder-header > div > ul:nth-child(2) > li:nth-child(2) > a").click();
    	
    	// 10
    	selector("body > div.nk-container > div.paper-filter-box > div:nth-child(2) > div.paper-filter-cont > div > a:nth-child(3)").click();
    	
    	// 15, maximize Chrome window to avoid clicking on another element
    	selector("a.refresh-tag").click();
    	
    	// 35
    	selector("body > div.nk-container > div.paper-filter-box > div:nth-child(2) > div.paper-filter-cont > div > a:nth-child(1)").click();
    	selector("body > div.nk-container > div.paper-filter-box > div:nth-child(2) > div.paper-filter-cont > div > a:nth-child(2)").click();
    	selector("body > div.nk-container > div.paper-filter-box > div:nth-child(3) > div.paper-filter-cont > div > a:nth-child(1)").click();
    	selector("body > div.nk-container > div.nk-main.papers-center.with-banner-page.clearfix > div:nth-child(2) > div.menu-box > ul > li:nth-child(2) > a").click();
    	
    	// 40
    	selector("body > div.nk-container > div.nk-main.papers-center.with-banner-page.clearfix > div:nth-child(2) > div.module-body > ul > li:nth-child(1) > a").click();

//    	driver.get("https://www.nowcoder.com/test/9763997/summary");
    	
    	selector("body > div.nk-container > div.nk-main.ranking-main.clearfix > div.nk-content > div > div.module-body > a").click();
    
    	// wait for login complete
    	waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector("#next")));
    	
    	// 55
    	selector("#next").click();
    	selector("#runCode").click();
    	selector(".btn.btn-primary.confirm-btn").click();
    	
    	// 75
    	selector(".js-follow").click();
    	selector("input.js-input").sendKeys("≤‚ ‘");
    	selector("input.js-input").sendKeys(Keys.ENTER);
    	selector(".btn.btn-primary.confirm-btn").click();
    	selector(".js-mark").click();
    	
    	// 100!
    	selector("#next").click();
    	selector("#aheadFinish").click();
    	selector(".btn.btn-primary.confirm-btn").click();
    	
    	selector("body > div.nk-container > div.nk-main.analytic-page.clearfix > div.menu-box > ul > li:nth-child(2) > a").click();
    	
    	selector(".click-reply").click();;
    	selector("textarea.reply-input").sendKeys("—ßœ∞¡À£°");
    	selector(".js-main-reply").click();
    	
    }
    
    static <T> void waitUntil(ExpectedCondition<T> condition) {
    	new WebDriverWait(driver, 30000).until(condition);
    }
    
    static void sleep(int ms) {
    	try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    static void toNewPage() {
    	ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
    	driver.switchTo().window(list.get(list.size()-1));
    }
    
    static WebElement xpath(String xpath) {
    	return driver.findElement(By.xpath(xpath));
    }
    
    public static WebElement selector(String selector) {
    	return driver.findElement(By.cssSelector(selector));
    }

    public static void main(String[] args) {
        // Run main function to test your script.
        WebDriver driver = new ChromeDriver();
        try { test(driver); } 
        catch(Exception e) { e.printStackTrace(); }
        finally { driver.quit(); }
    }

}
