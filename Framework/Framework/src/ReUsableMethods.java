/* Selenium Lib 
 * */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.WebElement;


public class ReUsableMethods {
	
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;

	/* Name of the method: enterText
	 * Description: Enter text to text field
	 * Arguments: obj --> Object, textVal --> value to be entered , objName --> name of the object
	 * Created by: automation team
	 * Creation Date: June 08 2017
	 * Last Modified: June 08 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{

		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass ", "enterText", textVal + " is entered in " + objName + " field ");
			//System.out.println("Pass: " + textVal + " is entered in " + objName + " field ");
		}else{
			Update_Report("Fail", "enterText", objName + " field does not displayed please check your application.");
			//System.out.println("Fail: " + objName + " field does not displayed please check your application.");
		}

	}

	/* Name of the method: clickObject
	 * Description: Click on the Object
	 * Arguments: objXpath  --> Xpath of the object,  objName --> Name of the object
	 * Created by: automation team
	 * Creation Date: June 12 2017
	 * Last Modified: June 12 2017
	 * */
	public static void clickObject(WebElement obj, String objName){
		if(obj.isDisplayed()){
			obj.click();
			System.out.println("Pass:" + objName + " object is clicked.");
		}
		else{
			System.out.println("Fail:" + objName + " object is not displayed.");
		}
	}
	/*Name of the method: checkRememberMe
	 * Description: Select checkBox
	 * Arguments: objXpath--> xpath of the object, objName --> Name of the object
	 * Created by: authomation team
	 * Creation Date: June 12 2017
	 * Last Modified: June 12 20117
	 */
	public static void checkBoxOn(WebElement obj, String objName ){
		if(obj.isDisplayed()){
			if(!(obj).isSelected())
				obj.click();
			System.out.println("Pass:" + objName+ "check box is checked");
		}else{
			System.out.println("Fail:" + objName+ "check box is not checked");
		}
	}

	/*Name of the method: checkRememberMe
	 * Description: De-select checkBox 
	 * Arguments: objXpath--> xpath of the object, objName --> Name of the object
	 * Created by: authomation team
	 * Creation Date: June 12 2017
	 * Last Modified: June 12 20117
	 */
	public static void checkBoxOff(WebElement obj, String objName ){
		if(obj.isDisplayed()){
			if(obj.isSelected())
				obj.click();
			System.out.println("Pass:" + objName+ "check box is checked");
		}else{
			System.out.println("Fail:" + objName+ "check box is already deselected");
		}
	}

	/*Name of the method: validateMSG
	 * Description: validate error message displayed
	 * Arguments: objXpath--> xpath of the object, objName --> Name of the object
	 * Created by: authomation team
	 * Creation Date: June 12 2017
	 * Last Modified: June 12 20117
	 */
	public static void validateMSG(WebElement obj, String expMsg, String objName){
		if(obj.isDisplayed()){
			String actualMsg = obj.getText();
			if(expMsg.equals(actualMsg)){
				System.out.println("Pass: Actual message is matching with expected message " + expMsg);
			}else{
				System.out.println("Fail: Actual message" +actualMsg+ "is not matching with expected message" +expMsg);
			}
		}
	}
	
	/* Method Name: writeXL
	 * Brief description: Write XL content
	 * Arguments: dt_path --> data path, sheetName--> name of xl sheet
	 * Created by: Automation Team
	 * Creation date: Jun 14 2017
	 * Last Modified: Jun 14 2017
	 * */

	public static void writeXL(String dt_path, String sheetName, int iRow, int iCol, String textVal) throws IOException{
		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_path);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		/*Step5: Access Row*/
		HSSFRow row = sheet.getRow(iRow);


		/*Step5: Access Column*/
		HSSFCell cell = row.getCell(iCol);


		cell.setCellValue(textVal);

		if(textVal.equals("Pass")){
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}else{
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.RED().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}



		FileOutputStream fout = new FileOutputStream(dt_path);
		wb.write(fout);
		fout.flush();
		fout.close();

	}



	/* Method Name: readXlSheet
	 * Brief description: Read XL content
	 * Arguments: dt_path --> data path, sheetNam e--> name of xl sheet
	 * Created by: Automation Team
	 * Creation date: Jun 12 2017
	 * Last Modified: Jun 12 2017
	 * */

	public static String[][] readXlSheet(String dt_path, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_path);

		/*Step2: Access the Xl File*/
		FileInputStream  xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);


		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();

		String [][] xlData = new String[iRowCount][iColCount];

		for(int i = 0; i < iRowCount; i++){
			for(int j = 0; j <iColCount; j++){
				xlData[i][j]= sheet.getRow(i).getCell(j).getStringCellValue();

			}

		}
		return xlData;
	}
	
	
	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 
	}

}




