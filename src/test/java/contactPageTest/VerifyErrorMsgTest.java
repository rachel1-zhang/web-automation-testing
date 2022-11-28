package contactPageTest;

import base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.ContactPagePO;
import pageobjects.HomePagePO;
import lib.Utils;
import java.io.IOException;

public  class VerifyErrorMsgTest extends Base {
    private static final Logger log = LogManager.getLogger();

    @BeforeClass
    public void beforeClass() throws IOException {
        startBrowser();
        log.info("start Browser successfully");
    }

    @DataProvider(name="provideContactMsg")
    public Object[][] provideData(){
        return new Object[][]{
                {"John","John.example@planittesting.com.au","Tell us about it ......"}
        };
    }

    @Test(dataProvider ="provideContactMsg")
    public void verifyErrorMsgTest(String forename,String email,String message){
        //create HomePagePO instance
        HomePagePO hp=new HomePagePO(driver);
        log.info("go to home page successfully");

        //click contactIcon
        hp.contactPageIcon().click();
        log.info("go to contact page successfully");

        //create ContactPagePO instance
        ContactPagePO cp=new ContactPagePO(driver);
        //click submit button
        cp.submitBtn().click();
        log.info("click submit button successfully");


        //verify errors exit
        try{
            Assert.assertEquals(cp.forenameErrorMsg().getText(),"Forename is required");
        }catch (AssertionError e){
            Assert.fail("Forename is required assertion failed"+e);
        }

        try{
            Assert.assertEquals(cp.emailErrorMsg().getText(),"Email is required");
        }catch (AssertionError e){
            Assert.fail("Email is required assertion failed"+e);
        }

        try{
            Assert.assertEquals(cp.messageErrorMsg().getText(),"Message is required");
        }catch (AssertionError e){
            Assert.fail("Message is required assertion failed"+e);
        }
        log.info("required assertions are verified successfully");

        //get input data from data provider
        //Populate all mandatory fields
        cp.forenameInput().sendKeys(forename);
        cp.emailInput().sendKeys(email);
        cp.messageInput().sendKeys(message);

        Utils util=new Utils();
        //verify errors are gone
        try{
            Assert.assertFalse(false, String.valueOf(util.isElementExit(driver,cp.forenameErrorMsg)));
        }catch (AssertionError e){
            Assert.fail("forename error message assertion failed"+e);
        }

        try{
            Assert.assertFalse(false, String.valueOf(util.isElementExit(driver,cp.emailErrorMsg)));
        }catch (AssertionError e){
            Assert.fail("email error message assertion failed"+e);
        }

        try{
            Assert.assertFalse(false, String.valueOf(util.isElementExit(driver,cp.messageErrorMsg)));
        }catch (AssertionError e){
            Assert.fail("message error assertion failed"+e);
        }
        log.info("Validate errors are gone successfully");


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
