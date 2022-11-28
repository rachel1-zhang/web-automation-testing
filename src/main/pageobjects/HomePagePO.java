package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePO {
    public WebDriver driver;

    public HomePagePO(WebDriver driver){
        this.driver=driver;
    }

    //contact icon
    By contactPageIcon= By.id("nav-contact");
    
    //shop icon
    By shopPageIcon= By.id("nav-shop");

    //get ccontactIcon method
    public WebElement contactPageIcon(){
        return driver.findElement(contactPageIcon);
    }

    //get shopPageIcon method
    public WebElement shopPageIcon(){
        return driver.findElement(shopPageIcon);
    }

}
