package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPagePO {
    public WebDriver driver;

    public ShopPagePO(WebDriver driver){
        this.driver=driver;
    }

    //buy button of Stuffed Frog
    By stuffedFrogBuyBtn= By.xpath("//li[@id='product-2']/div/p/a");

    //buy button of Fluffy Bunny
    By fluffyBunnyBuyBtn= By.xpath("//li[@id='product-4']/div/p/a");

    //buy button of Valentine Bear
    By valentineBearBuyBtn= By.xpath("//li[@id='product-7']/div/p/a");

    // cart button
    By cartBtn= By.id("nav-cart");


    //get stuffedFrogBuyBtn method
    public WebElement stuffedFrogBuyBtn(){
        return driver.findElement(stuffedFrogBuyBtn);
    }

    //get fluffyBunnyBuyBtn method
    public WebElement fluffyBunnyBuyBtn(){
        return driver.findElement(fluffyBunnyBuyBtn);
    }

    //get valentineBearBuyBtn method
    public WebElement valentineBearBuyBtn() {
        return driver.findElement(valentineBearBuyBtn);
    }

    //get cartBtn method
    public WebElement cartBtn() {
        return driver.findElement(cartBtn);
    }







}

