package base;

import java.io.File;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {
    private static ExtentReports extent;
    
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "ExtentReport";
    private static String reportFileName = "Automation-ExtentReport"+".html";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
  
    
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //创建extent report实例
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
       
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("UTF-8");
        //htmlReporter.config().setEncoding("gbk");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS); 
        htmlReporter.config().setTimeStampFormat("yyyy-MM-dd, hh:mm a '('zzz')'");
        //htmlReporter.config().enableTimeline(false);

 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
       
        //设置测试环境信息
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("TestEnvironment", "SIT");
		extent.setSystemInfo("Selenium version", "3.141.59");
		
		System.out.println(fileSeperator);
		System.out.println(reportFilepath);
 
        return extent;
    }
     
    //创建测试报告路径
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return reportFileLocation;
    }
 
}
