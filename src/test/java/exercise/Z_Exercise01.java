package exercise;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Z_Exercise01 extends TestBase {

    //https://demoqa.com/ url'ine gidin.
//Alerts, Frame & Windows Butonuna click yap
//""Please select an item from left to start practice."" yazısının görünür olduğunu doğrula
//Sol'da açılan Menu den ""Browser Windows"" butonuna click yap
//New Tab butonunun görünür olduğunu doğrula
//New Tab butonuna click yap
//Açılan yeni Tab da ""This is a sample page"" yazısının görünür olduğunu doğrula
//İlk Tab'a geri dön
//New Tab butonunun görünür olduğunu doğrula

    @Test
    public void test1() throws InterruptedException, IOException {

    driver.get("https://demoqa.com/");

        //Alerts, Frame & Windows Butonuna click yap
        WebElement alertFrameWindow=driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",alertFrameWindow);
        Thread.sleep(3);
        js.executeScript("arguments[0].click();",alertFrameWindow);

        //""Please select an item from left to start practice."" yazısının görünür olduğunu doğrula

        WebElement yazı= driver.findElement(By.xpath("//*[text()='Please select an item from left to start practice.']"));
        Assert.assertTrue(yazı.isDisplayed());
        String windowHandle1= driver.getWindowHandle();

        //resim alalım burda
        String date=new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu="depo/screenshot"+date+".png";
        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));

        Thread.sleep(3000);

        //Sol'da açılan Menu den ""Browser Windows"" butonuna click yap
        WebElement browserWindows= driver.findElement(By.xpath("//span[text()='Browser Windows']"));
        browserWindows.click();

        //New Tab butonunun görünür olduğunu doğrula
        WebElement newTabText= driver.findElement(By.xpath("//*[@id='tabButton']"));
        Assert.assertTrue(newTabText.isDisplayed());

        //New Tab butonuna click yap
        newTabText.click();
        Thread.sleep(3000);
        //Açılan yeni Tab da ""This is a sample page"" yazısının görünür olduğunu doğrula
        Set<String> allWindowHandle=driver.getWindowHandles();
        for (String w:allWindowHandle) {
            if (!w.equals(windowHandle1)){
                driver.switchTo().window(w);
            }
        }
        Thread.sleep(3000);
        WebElement samplePageText= driver.findElement(By.xpath("//h1"));
        Assert.assertTrue(samplePageText.isDisplayed());

        Thread.sleep(3000);
        //İlk Tab'a geri dön

        driver.switchTo().window(windowHandle1);

        //New Tab butonunun görünür olduğunu doğrula
        Assert.assertTrue(newTabText.isDisplayed());


    }



}
