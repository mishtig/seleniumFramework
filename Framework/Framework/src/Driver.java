/* 20 SFDC scripts by Sunday EOD
 * 
 * Tomorrow Divya will take session 
 * 
 * Press F3 --> Go to methods
 * Reflexive API or Java reflection 
 * 
 * 
 * 
 * 
 * */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Driver {
	static WebDriver driver;
	static int report = 0;
	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		String dtPath= "C:/Users/Abhis_lw0caw1/Google Drive/May 01 2017/Read Only/Framework/TestSuit.xls";
		String[][] recData = ReUsableMethods.readXlSheet(dtPath, "Sheet1");

		
		for(int i = 1; i <recData.length;i++){
			
			String tc = recData[i][1];
			ReUsableMethods.startReport(tc, "C:/Users/Abhis_lw0caw1/Google Drive/May 01 2017/Read Only/Framework/Report/");
			driver = new FirefoxDriver();
			
			/*Java Reflection or Reflexive API*/
			report = 0;
			Method testCase = AutomationScripts.class.getMethod(tc);
			testCase.invoke(testCase);
			ReUsableMethods.bw.close();
			
			if(report == 0){
				ReUsableMethods.writeXL(dtPath, "Sheet1", i, 3, "Pass");
			}else{
				ReUsableMethods.writeXL(dtPath, "Sheet1", i, 3, "Fail");
			}
		}
		
		
	}

}
