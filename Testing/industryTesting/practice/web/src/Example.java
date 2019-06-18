import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class Example {
	
	static WebDriver driver;

    // Mooctest Selenium Example


    // <!> Check if selenium-standalone.jar is added to build path.

    public static void test(WebDriver driver) {
        // TODO Test script
        // eg:driver.get("https://www.baidu.com/")
        // eg:driver.findElement(By.id("wd"));
    	
    	Example.driver = driver;
    	driver.get("http://ectest.nisco.cn");
    	
    	selector("#username_1").sendKeys("QTEST002");
    	selector("#password").sendKeys("123456");
    	
    	selector("#submit_btn").click();
    	
    	driver.get("http://ectest.nisco.cn/ec/notice/list?moduleListNavId=notice");
    	
    	selector("body > div.box.customContent > section > div.row.box-body > a:nth-child(1)").click();
    	
    	selector("#title").sendKeys("web功能测试");
    	
    	selector("#adtype").click();
    	 
    	selector("#adtype > option:nth-child(2)").click();
    	
    	selector("#ispublic").click();
    	
    	selector("#ispublic > option:nth-child(2)").click();
    	
    	selector("#form > div.box-body > div:nth-child(5) > div > div.note-editor.note-frame.panel.panel-default > div.note-editing-area > div.note-editable").click();
    	
    	selector("#form > div.box-body > div:nth-child(5) > div > div.note-editor.note-frame.panel.panel-default > div.note-editing-area > div.note-editable").sendKeys("功能测试的一次练习");
    	
    	selector("#form > div.row.text-center > div > button").click();
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
