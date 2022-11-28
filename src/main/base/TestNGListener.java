package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGListener implements ITestListener {
    public WebDriver driver;
    Base base=new Base();


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        String testname = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testname);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        this.driver=((Base)result.getInstance()).driver;

        String testcasename=result.getName().toString().trim();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotname = testcasename+"_"+timeStamp;

        try {
            base.takeScreeshot(screenshotname,driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //extent report
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

        // Attach failure screenshots to Extent Report
        try {
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"\\ScreenShots\\"+screenshotname+".png").build());
        } catch (IOException e) {
            System.out.println(e.getCause());
        }


    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
    }
}
