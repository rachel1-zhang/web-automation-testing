package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPagePO {
    public WebDriver driver;
    public CartPagePO (WebDriver driver){
        this.driver=driver;
    }

    //cart total quantity element
    By totalQuantity=By.xpath("//li[@id='nav-cart']/a/span");

    //price in total element
    By total=By.xpath("//strong[@class=\"total ng-binding\"]");


    //get cart total quantity element
    public int totalQuantity(){
        String value=driver.findElement(totalQuantity).getText();
        return Integer.parseInt(value);
    }

    //get price in total element
    public double total(){
        String value=driver.findElement(total).getText();
        String subStr=value.replace("Total: ","");
        System.out.println(subStr);
        return Double.parseDouble(subStr);
    }




    //table element
    By tableEle=By.xpath("//form/table/tbody/tr");

    public List<ArrayList<String>> getCartProduct() {
        //initial Two dimensional array list
        List<ArrayList<String>> cartProductList = new ArrayList<>();

        //get tr list of table
        List<WebElement> trList = driver.findElements(tableEle);

        //loop trList to get product name, product price, product quantity, product subtotal of all products
        for (int i = 0; i < trList.size(); i++) {
            // get currTdList td list of every tr
            List<WebElement> currTdList = trList.get(i).findElements(By.tagName("td"));

            //creat array list instance for td then add to cartProductList
            cartProductList.add(new ArrayList<String>());
            //loop current td list
            for (int j = 0; j < currTdList.size() - 1; j++) {

                if (j == 2) {

                    cartProductList.get(i).add(currTdList.get(j).findElement(By.tagName("input")).getAttribute("value"));

                } else {
                    cartProductList.get(i).add(currTdList.get(j).getText());

                }
            }
        }
        //return cartProductList
        return cartProductList;

}

}
