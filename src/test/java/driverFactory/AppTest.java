package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class AppTest extends FunctionLibrary
{
String inputpath="";
String outputpath="";
ExtentReports report;
ExtentTest logger;
@Test
public void startTest() {
	boolean res=false;
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("Login");
	Reporter.log("no of rows:: "+rc,true);
	for(int i=1;i<=rc;i++)
	{
		logger=report.startTest("Validate Login");
	logger.assignAuthor("priyanka");
	String username=xl.getCellData("Login", i, 0);	
	String password=xl.getCellData("Login", i, 1);	
	logger.log(LogStatus.INFO, username+"    "password);
    res=FunctionLibrary.adminLogin(username,password);
    if(res){
    xl.setCellData("Login", i, 2,"valid username and password",outputpath);	
    xl.setCellData("Login", i, 3, "pass", outputpath);
    logger.log(LogStatus.PASS,"valid user name and password");
    }
    else
    {
    	 xl.setCellData("Login", i, 2,"invalid username and password",outputpath);	
    	    xl.setCellData("Login", i, 3, "fail", outputpath);
    	    logger.log(LogStatus.FAIL,"Invalid user name and password");
 }
    report.endTest(logger);
    report.flush();
	}
}
}