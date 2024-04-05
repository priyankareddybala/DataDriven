package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
public static boolean adminLogin(String user,String pass) {
	driver.get(conpro.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("objReset"))).click();
	driver.findElement(By.xpath(conpro.getProperty("objUser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
	String Expected="dashboard";
	String Actual=driver.getCurrentUrl();
	if(Actual.contains(Expected)) {
		Reporter.Log("valid username and password:: "+Expected+"  "+Actual,true);
		driver.findElement(By.xpath(conpro.getProperty("objlogout"))).click();
		return true;
				}
	else {
		String errormessage=driver.findElement(By.xpath(conpro.getProperty("objErrormessage"))).getText();
		driver.findElement(By.xpath(conpro.getProperty("objOk"))).click();
		Reporter.Log("Error message:: "+ " "+Expected+" "+Actual,true);
		return false;
}
}
