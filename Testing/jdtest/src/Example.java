import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class Example {

    // Mooctest Selenium Example


    // <!> Check if selenium-standalone.jar is added to build path.

	
	public static WebDriver driver;
	
    public static void test(WebDriver driver) {
    	
    	Example.driver = driver;
    
    	
//        // TODO Test script
        driver.get("https://jd.com/");

        xpath("/html/body[@class='index']/div[@id='app']/div[@id='shortcut']/div[@class='w']/ul[@class='fl']/li[@id='ttbar-mycity']/div[@class='dt cw-icon ui-areamini-text-wrap']/span[@class='ui-areamini-text']").click();
        xpath("/html/body[@class='index']/div[@id='app']/div[@id='shortcut']/div[@class='w']/ul[@class='fl']/li[@id='ttbar-mycity']/div[@class='dd dropdown-layer']/div[@class='ui-areamini-content-wrap']/div[@class='ui-areamini-content']/div[@class='ui-areamini-content-list']/div[@class='item'][12]/a[@class='selected']").click();
        
        selector("a[href='//book.jd.com/']").click();
        sleep(1000);
//        
        // switch to new window
        
        toNewPage();
    
        
        WebElement input = xpath("/html/body/div[@class='w']/div[@id='search-2014']/div[@class='form']/input[@id='key']");
        input.sendKeys("软件测试朱少民");
        input.sendKeys(Keys.ENTER);
        sleep(3000);
        
        xpath("/html/body/div[@id='J_searchWrap']/div[@id='J_container']/div[@id='J_selector']/div[@class='J_selectorLine s-category'][2]/div[@class='sl-wrap']/div[@class='sl-value']/div[@class='sl-v-list']/ul[@class='J_valueList']/li[1]/a").click();
       

        
        driver.findElement(By.xpath("/html/body/div[@id='J_searchWrap']/div[@id='J_container']/div[@id='J_main']/div[@class='m-list']/div[@class='ml-wrap']/div[@id='J_filter']/div[@class='f-line top']/div[@id='J_selectorPrice']/div[@class='f-price-set']/div[@class='fl'][1]/input[@class='input-txt']")).sendKeys("35");
        driver.findElement(By.xpath("/html/body/div[@id='J_searchWrap']/div[@id='J_container']/div[@id='J_main']/div[@class='m-list']/div[@class='ml-wrap']/div[@id='J_filter']/div[@class='f-line top']/div[@id='J_selectorPrice']/div[@class='f-price-set']/div[@class='fl'][2]/input[@class='input-txt']")).sendKeys("60");
//        driver.findElement(By.cssSelector(".J-price-confirm")).click();
        
        // hover to input and click the button
        
        xpath("/html/body/div[@id='J_searchWrap']/div[@id='J_container']/div[@id='J_main']/div[@class='m-list']/div[@class='ml-wrap']/div[@id='J_filter']/div[@class='f-line top']/div[@id='J_selectorPrice']/div[@class='f-price-set']/div[@class='fl'][2]/input[@class='input-txt']").click();
        selector(".J-price-confirm").click();
        
        driver.findElements(By.cssSelector(".f-sort > a")).get(4).click();
        sleep(500);
        driver.findElements(By.cssSelector(".f-sort > a")).get(4).click();

        
       selector("#J_store_selector").click();
        driver.findElements(By.cssSelector(".ui-switchable-item")).get(2).click();
       selector("a[data-id='3377']").click();
        
        sleep(2000);
        selector("a[data-field='wtype']").click();
        
        driver.findElements(By.cssSelector(".gl-i-wrap")).get(2).click();
        sleep(1000);
//      
      // switch to new window
      
  	toNewPage();
      
    	
    	driver.get("https://item.jd.com/11563286.html?dist=jd");
    	
      selector(".btn-add").click();
      selector(".btn-append").click();
      
      toNewPage();
      
      selector(".btn-addtocart").click();

      sleep(3000);
      
      selector(".decrement").click();
      
    }
    
    static void toNewPage() {
      for (String winHandle: driver.getWindowHandles()) {
  	driver.switchTo().window(winHandle);
  }
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
