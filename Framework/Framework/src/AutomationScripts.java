/* 1. Create reusable method to click object
 * 2. Create reusable method to select check box 
 * 3. Create reusable method to de select check box
 * 4. Create RUM to validate error message displayed 
 * */
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AutomationScripts extends ReUsableMethods{

	
	
	public static void LoginToSFDC() throws IOException{
		Driver.driver.get("https://login.salesforce.com/");

		/*enter user name in user name field*/
		WebElement unObj = Driver.driver.findElement(By.xpath("//*[@id='username']"));
		enterText(unObj, "user@gmail.com", "UserName");


		/*Enter password in password field*/
		WebElement pwdObj = Driver.driver.findElement(By.xpath("//*[@id='password']"));
		enterText(pwdObj, "pass@321", "Password");

	}

	
	public static void forgotPassword(){
		System.out.println("Forgot pwd executed...");
	}
	
	
	
}
