package cartPageTest;

import base.Base;
import lib.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.CartPagePO;
import pageobjects.HomePagePO;
import pageobjects.ShopPagePO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartPageTest extends Base {
    private static final Logger log = LogManager.getLogger();

    @BeforeClass
    public void beforeClass() throws IOException {
        startBrowser();
        log.info("start Browser successfully");
    }

    @Test
    public void cartPageTest() throws IOException {
        //create HomePagePO instance
        HomePagePO hp=new HomePagePO(driver);
        log.info("go to home page successfully");

        //click shopPageIcon
        hp.shopPageIcon().click();
        log.info("go to shop page successfully");

        //create ShopPagePO instance
        ShopPagePO sp=new ShopPagePO(driver);

        Utils util=new Utils();
        //add products to cart
        //add 2 stuffed frog
        util.addProduct(sp.stuffedFrogBuyBtn(),2);

        //add 5 fluffy bunny
        util.addProduct(sp.fluffyBunnyBuyBtn(),5);

        //add 3 valentine bear
        util.addProduct(sp.valentineBearBuyBtn(),3);

        //go to cart
        sp.cartBtn().click();
        log.info("go to cart page successfully");

        //create CartPagePO instance
        CartPagePO cp=new CartPagePO(driver);

        //get actual total from cart page
        double actualTotal=cp.total();
        //declare variable expectedTotal and get it from data file
        double expectedTotal=0;

        //get all items in the cart
        List<ArrayList<String>> cartProductList= cp.getCartProduct();

        //loop every product
        for (int i=0;i<cartProductList.size();i++) {
            //get current product
            ArrayList<String> currActualProduct=cartProductList.get(i);
            //declare expected subtotal
            double expectedSubTotal=0;

            for (int j = 0; j < currActualProduct.size(); j++) {

                if(j==1){
                    //get actual price from page
                    String actualPrice=currActualProduct.get(j).replace("$","");
                    //get expected price from data file
                    String expectedPrice=
                            util.getData("cartProduct.xlsx","cartExpectedData",i+1,j);
                    //verify actualPrice and expectedPrice
                    Assert.assertEquals(actualPrice,expectedPrice);
                    log.info("The actual price and the expected price are verified successfully");
                }

                if(j==3){
                    //get actual subtotal from page
                    String actualSubTotal=currActualProduct.get(j).replace("$","");
                    //get expected subtotal for each product from data file
                    expectedSubTotal=
                    Double.parseDouble(util.getData("cartProduct.xlsx","cartExpectedData",i+1,j));
                    //verify actualSubTotal and expectedSubTotal
                    Assert.assertEquals(Double.parseDouble(actualSubTotal),expectedSubTotal);
                    log.info("The actual subtotal and the expected subtotal are verified successfully");
                }
            }

            expectedTotal+=expectedSubTotal;
        }

        //verify actualTotal and expectedTotal
        Assert.assertEquals(actualTotal,expectedTotal);
        log.info("The actual total and the expected total are verified successfully");

    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
}
