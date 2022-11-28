package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPagePO {
    public WebDriver driver;

    public ContactPagePO(WebDriver driver){
        this.driver=driver;
    }

    //submit button
    By submitBtn= By.xpath("//form[@name='form']/div/a");

    //forename
    By forenameInput= By.id("forename");

    //email
    By emailInput=By.id("email");

    //message
    By messageInput=By.id("message");

    //success message of submission
    public By successSubm=By.xpath("//div[@class='ng-scope']/div/strong");

    //error message elements
    //forename error
    public By forenameErrorMsg= By.id("forename-err");

    //email error
    public By emailErrorMsg= By.id("email-err");

    //message error
    public By messageErrorMsg= By.id("message-err");

    //get submitBtn method
    public WebElement submitBtn(){
        return driver.findElement(submitBtn);
    }

    //get forenameInput method
    public WebElement forenameInput(){
        return driver.findElement(forenameInput);
    }

    //get emailInput method
    public WebElement emailInput(){
        return driver.findElement(emailInput);
    }

    //get messageInput method
    public WebElement messageInput(){
        return driver.findElement(messageInput);
    }

    //get success message of submission element
    public WebElement successSubm(){
        return driver.findElement(successSubm);
    }




    public WebElement forenameErrorMsg(){
        return driver.findElement(forenameErrorMsg);
    }


    public WebElement emailErrorMsg(){
        return driver.findElement(emailErrorMsg);
    }


    public WebElement messageErrorMsg(){
        return driver.findElement(messageErrorMsg);
    }

}
