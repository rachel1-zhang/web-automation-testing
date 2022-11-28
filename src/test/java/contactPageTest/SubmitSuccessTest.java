package contactPageTest;

import base.Base;
import lib.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.ContactPagePO;
import pageobjects.HomePagePO;

import java.io.IOException;

public class SubmitSuccessTest extends Base {
    private static final Logger log = LogManager.getLogger();

    @BeforeClass
    public void beforeClass() throws IOException {
        startBrowser();
        log.info("start Browser successfully");
    }

    @Test
    public void submitSuccessTest() throws InterruptedException {
        //create HomePagePO instance
        HomePagePO hp=new HomePagePO(driver);
        log.info("go to home page successfully");

        //click contactIcon
        hp.contactPageIcon().click();
        log.info("go to contact page successfully");

        //create ContactPagePO instance
        ContactPagePO cp=new ContactPagePO(driver);

        //get input data from data file
        //file name:contactDataFile.xlsx
        Utils util=new Utils();
        String forename=util.getData("contactDataFile.xlsx","contactInputData",1,0);
        String email=util.getData("contactDataFile.xlsx","contactInputData",1,1);
        String message=util.getData("contactDataFile.xlsx","contactInputData",1,2);

        //Populate all mandatory fields
        cp.forenameInput().sendKeys(forename);
        cp.emailInput().sendKeys(email);
        cp.messageInput().sendKeys(message);

        //click submit button
        cp.submitBtn().click();
        log.info("contact messages are submitted successfully");


        Thread.sleep(10000);


        WebDriverWait wait=new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cp.successSubm));

        String expectedMsg=util.getData("contactDataFile.xlsx","contactExpectedData",0,0);
        Assert.assertEquals(cp.successSubm().getText().toLowerCase(),expectedMsg);
        log.info("Submit contact message are verified successfully");


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
