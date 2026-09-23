package Pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    public int index;
    private By Userdropdown = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
    private By Lougutbtn = By.xpath("//a[text()='Logout']");
    private By SearchBox= By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By Searchbtn= By.xpath("//button[text()=' Search ']");
    private By MenuItems= By.xpath("//a[@class='oxd-main-menu-item']");


    public BasePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public static void setDriver(WebDriver driver) {
    }

    @Step("Logout from application")
    public void Logout()
    {
        driver.findElement(this.Userdropdown).click();
        driver.findElement(this.Lougutbtn).click();
        attachScreenshot("Record logout");
    }
    @Step("Click on menu item")
    public void ClickMenuitem(int index)
    {
        driver.findElements(this.MenuItems).get(index).click();
        attachScreenshot("Menu item click");
    }
    @Step("Search a record")
    public void SreachRecord(String SearchText) throws InterruptedException {
        WebElement search= driver.findElement(this.SearchBox);
        search.clear();
        search.sendKeys(SearchText);
        driver.findElement(Searchbtn).click();
        System.out.println("Entered: " + search.getAttribute("value"));
        Thread.sleep(5000);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span']")));
        attachScreenshot("Search results");
    }
    @Step ("Verify record is Displayed")
    public boolean isRecordDisplayed()
    {
        Boolean displayed =driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).isDisplayed();
        attachScreenshot("Record Verification");
        return displayed;

    }


    public By Lougutbtn() {
        return Lougutbtn;
    }

    @Attachment(value = "Screenshot : {name}",type = "image/png")
    public byte[] attachScreenshot(String Name)
    {
        return((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

    }
}
