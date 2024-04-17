import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class KurtosysFunctionalTestCaseOne {
@Test
    public void logIn(){
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        WebDriver driver=new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.manage().window().maximize();
        driver.get("https://www.kurtosys.com");

        //locating the RESOURCES element at the bottom of the page and making sure it is visible on the screen
        WebElement resources = driver.findElement(By.xpath("/html/body/div[2]/div/section/div/div/div[2]/div/div/section[2]/div/div/div[3]/div/div/div[1]/div/span"));
        if (!resources.isDisplayed()){
            js.scrollToElement(driver,resources);
        }

        //Below is the list of the elements the script will interact with
        WebElement whitePapers =driver.findElement(By.xpath("/html/body/div[2]/div/section/div/div/div[2]/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/ul/li[4]/a"));
        WebElement selectedPaper=driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div/div/div/div/div[1]/article[7]/div/div[1]/p/a"));
        WebElement firstName=driver.findElement(By.xpath("//*[@id=\"18882_231721pi_18882_231721\"]"));
        WebElement lastName=driver.findElement(By.xpath("//*[@id=\"18882_231723pi_18882_231723\"]"));
        WebElement company=driver.findElement(By.xpath("//*[@id=\"18882_231727pi_18882_231727\"]"));
        WebElement industry=driver.findElement(By.xpath("//*[@id=\"18882_231729pi_18882_231729\"]"));
        WebElement sendCopy=driver.findElement(By.xpath("//*[@id=\'pardot-form\']/p[3]/input'[1]"));

        String actualDownloadErrorOne = driver.findElement(By.xpath("//*[@id=\"pardot-form\"]/p[1]")).getText();
        String expectedDownloadErrorOne = "Please correct the errors below:";
        String actualDownloadErrorTwo = driver.findElement(By.xpath("//*[@id=\"pardot-form\"]/div[3]/p")).getText();
        String expectedDownloadErrorTwo = "This field is required.";
        
        whitePapers.click();
        selectedPaper.click();
        firstName.sendKeys("Vuyelwa");
        lastName.sendKeys("Musinahama");
        company.sendKeys("Self");
        industry.sendKeys("Software");
        sendCopy.click();

        try{
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyfile(screenshotFile, new File("Download Error Evidence.png"));
            System.out.println("Screenshot for download error taken successfully");

        }
        catch (Exception e){
            System.out.println("Screenshot for download error taken FAILED" + e.getMessage());

        }

        Assert.assertEquals(actualDownloadErrorOne,expectedDownloadErrorOne,"This is a different error message");
        Assert.assertEquals(actualDownloadErrorTwo,expectedDownloadErrorTwo,"This is a different error message");

    }
}
