import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class Example {

    // Mooctest Selenium Example


    // <!> Check if selenium-standalone.jar is added to build path.

	
	public static WebDriver driver;
	
    public static void test(WebDriver driver) {
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	Example.driver = driver;
    
    	
//        // TODO Test script
        driver.get("https://jd.com/");

        selector("#ttbar-mycity > div.dt.cw-icon.ui-areamini-text-wrap").click();
        selector("#ttbar-mycity > div.dd.dropdown-layer > div.ui-areamini-content-wrap > div > div > div:nth-child(12) > a").click();
        
        selector("#J_cate > ul > li:nth-child(14) > a:nth-child(1)").click();
        toNewPage();

    	driver.get("https://book.jd.com");
    	
        selector("#qiye-activity > a").click();
        selector("#key").sendKeys("软件测试朱少民");
        selector("#search-2014 > div > button").click();
        
        selector("#J_selector > div:nth-child(2) > div > div.sl-value > div.sl-v-list > ul > li:nth-child(1) > a").click();

        
        selector("#J_selectorPrice > div.f-price-set > div:nth-child(1) > input").sendKeys("35");
        
        selector("#J_selectorPrice > div.f-price-set > div:nth-child(3) > input").sendKeys("60");
        
        selector("#J_selectorPrice > div.f-price-set > div:nth-child(3) > input").click();
        
        selector("#J_selectorPrice > div.f-price-edit > a.item2.J-price-confirm").click();
        
        selector("#J_filter > div.f-line.top > div.f-sort > a:nth-child(5)").click();
        
        sleep(1000);
        
        selector("#J_filter > div.f-line.top > div.f-sort > a:nth-child(5)").click();
        
        selector("#J_store_selector > div.ui-area-text-wrap").click();
    
        selector("#J_store_selector > div.ui-area-content-wrap.ui-area-w-max > div.ui-area-tab > a:nth-child(3)").click();
        
        selector("#J_store_selector > div.ui-area-content-wrap.ui-area-w-max > div.ui-area-content > div.ui-switchable-panel.ui-switchable-panel-selected > ul > li:nth-child(8) > a").click();
        
        sleep(1000);
        selector("#J_feature > ul > li:nth-child(1) > a").click();
        
        sleep(1000);
        
        List<WebElement> goods = driver.findElements(By.cssSelector("#J_goodsList > ul > li"));

        
        goods.get(2).findElement(By.cssSelector("div > div.p-name > a")).click();
        
        toNewPage();
        
        selector("#choose-btns > div.choose-amount.fl > div > a.btn-add").click();
        
        selector("#InitCartUrl").click();
        
        selector("#GotoShoppingCart").click();
        
        selector(".decrement").click();
        
        sleep(500);
        
        selector(".submit-btn").click();
    }
    
    
    static void waitForPresence(WebElement element) {
    	new WebDriverWait(driver, 6000).until(ExpectedConditions.elementToBeClickable(element));
    }
    
    static void toNewPage() {
    	ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
    	driver.switchTo().window(list.get(list.size()-1));
    }
    
    static WebElement xpath(String xpath) {
    	return driver.findElement(By.xpath(xpath));
    }
    
    static WebElement selector(String selector) {
    	return driver.findElement(By.cssSelector(selector));
    }

    static void sleep(int seconds) {
    	try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        // Run main function to test your script.
        WebDriver driver = new ChromeDriver();
        try { test(driver); } 
        catch(Exception e) { e.printStackTrace(); }
        finally { driver.quit(); }
    }

}
