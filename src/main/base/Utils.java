package base;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
    public WebDriver driver;

    //get data from excel
    //file path
    String path = new File("").getAbsolutePath() + File.separator + "dataFiles\\";

    public String getData(String fileName, String sheetName,int rowNum,int cellNum)  {
        //use apache poi
        FileInputStream file= null;
        try {
            file = new FileInputStream(path+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //get excel file
        XSSFWorkbook wb= null;
        try {
            wb = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //get specific sheet
        XSSFSheet sh=wb.getSheet(sheetName);
        //get specific row
        XSSFRow row=sh.getRow(rowNum);
        //System.out.println(row.toString());

        //get specific cell
        XSSFCell cell=row.getCell(cellNum);
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    //Check if this element is present on the page
    public boolean isElementExit(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            //System.out.println(by+" doesn't exit");
            return false;
        }
    }

    //add product and quantity
    public void addProduct(WebElement element, int num){
        for (int i = 0; i <num ; i++) {
            element.click();
        }
    }
}
