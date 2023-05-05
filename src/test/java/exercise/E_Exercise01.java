package exercise;

import com.github.javafaker.Faker;
import com.google.common.util.concurrent.FakeTimeLimiter;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class E_Exercise01 extends TestBase {
     /*
       1. Launch browser
       2. Navigate to url 'http://automationexercise.com'
       3. Verify that home page is visible successfully
       4. Click on 'Signup / Login' button
       5. Verify 'New User Signup!' is visible
       6. Enter name and email address
       7. Click 'Signup' button
       8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
       9. Fill details: Title, Name, Email, Password, Date of birth
       10. Select checkbox 'Sign up for our newsletter!'
       11. Select checkbox 'Receive special offers from our partners!'
       12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
       13. Click 'Create Account button'
       14. Verify that 'ACCOUNT CREATED!' is visible
       15. Click 'Continue' button
       16. Verify that 'Logged in as username' is visible
       17. Click 'Delete Account' button
       18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
     */

    @Test
    public void test1() throws IOException {

       //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

       // 3. Verify that home page is visible successfully
            String expectedText="Automation Exercise";
            String actualText=driver.getTitle();
            Assert.assertEquals(expectedText,actualText);
        WebElement sayfa= driver.findElement(By.xpath("//*[@src='/static/images/home/logo.png']"));
        Assert.assertTrue(sayfa.isDisplayed());

        String date=new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu="depo/screenshot"+date+".png";
        TakesScreenshot screenshot= (TakesScreenshot) driver;
        FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE),new File(dosyaYolu));

       // 4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[@href='/login']")).click();
       // 5. Verify 'New User Signup!' is visible
       WebElement signup=driver.findElement(By.xpath("//*[text()='New User Signup!']"));
       Assert.assertTrue(signup.isDisplayed());

       // 6. Enter name and email address
       Faker fake=new Faker();
     WebElement name=driver.findElement(By.xpath("//*[@type='text']"));
              name.sendKeys(fake.name().firstName());
       WebElement email= driver.findElement(By.xpath("(//input[@type='email'])[2]"));
              email.sendKeys(fake.internet().emailAddress());

     //7. Click 'Signup' button
     driver.findElement(By.xpath("(//*[@type='submit'])[2]")).click();

     // 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        WebElement enterAccount= driver.findElement(By.xpath("//h2[@class='title text-center']"));
        String expectedBaslık="ENTER ACCOUNT INFORMATION";
        String actualBaslık= enterAccount.getText();
        Assert.assertEquals(expectedBaslık,actualBaslık);
        // 9. Fill details: Title, Name, Email, Password, Date of birth
        WebElement radio1Title= driver.findElement(By.xpath("//*[@class='radio'][1]"));
        radio1Title.click();

       WebElement name1= driver.findElement(By.xpath("(//*[@type='text'])"));
               name1.sendKeys(fake.name().firstName(), Keys.TAB,fake.internet().emailAddress(),Keys.TAB,
                     fake.idNumber().valid(),Keys.TAB );

        WebElement days= driver.findElement(By.xpath("//*[@id='days']"));
        WebElement months=driver.findElement(By.xpath("//*[@id='months']"));
        WebElement years=driver.findElement(By.xpath("//*[@id='years']"));

        Select select=new Select(days);
        select.selectByVisibleText(String.valueOf(fake.number().numberBetween(1,31)));
        select.selectByVisibleText(String.valueOf(fake.number().numberBetween(1,12)));
        select.selectByVisibleText(String.valueOf(fake.number().numberBetween(1,2025)));

        //10. Select checkbox 'Sign up for our newsletter!'
        driver.findElement(By.xpath("//*[@id='newsletter']")).click();
       // 11. Select checkbox 'Receive special offers from our partners!'
        driver.findElement(By.xpath("//*[@name='optin']")).click();

        // 12. Fill details: First name, Last name, Company, Address,
        // Address2, Country, State, City, Zipcode, Mobile Number

    }

}
