package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

            public WebDriver driver ;

            public void startBrowser() throws IOException {
                //get GlobalData.properties path
                String path = new File("").getAbsolutePath() + File.separator + "configs\\GlobalData.properties";
                FileInputStream file = new FileInputStream(path);
                Properties prop=new Properties();
                prop.load(file);

                //get config data from GlobalData.properties
                String browser=prop.getProperty("browser");
                String url=prop.getProperty("url");
                //String driverPath="C:\\Develop\\driver";
                String driverPath=new File("").getAbsolutePath() + File.separator + "drivers\\";

                //set up webdriver instance
                if(browser.equals("firefox")){
                    System.setProperty("webdriver.gecko.driver",driverPath+"\\geckodriver.exe");
                    driver=new FirefoxDriver();
                }else if(browser.equals("chrome")){
                    System.setProperty("webdriver.chrome.driver",driverPath+"\\chromedriver.exe");
                    driver=new ChromeDriver();
                }else if(browser.equals("ie")){
                    System.setProperty("webdriver.ie.driver",driverPath+"\\IEDriverServer.exe");
                    driver=new ChromeDriver();
                }

                driver.get(url);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



            }

    public void takeScreeshot(String screenshotname,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File srcfile=ts.getScreenshotAs(OutputType.FILE);

        File desfile=new File(System.getProperty("user.dir")+"//Screenshots//"+screenshotname+".png");

        FileHandler.copy(srcfile,desfile);


    }






}
