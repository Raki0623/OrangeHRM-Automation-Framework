package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TimePage extends BasePage{
    private static int TimeIndex = 2;
    private By Expected= By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    private By EnterEmployeefield= By.xpath("//input[@placeholder='Type for hints...']");
    private By Viewbtn= By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    public TimePage(WebDriver driver) {
        super(driver);
    }
    @Step("Click on Time menu")
    public void ClickTimeMenu(){

        ClickMenuitem(TimeIndex);

    }
    public void Searchtime(String name, String expectedmessage)
    {
        driver.findElement(EnterEmployeefield).sendKeys(name);
        driver.findElement(Viewbtn).click();
        String actualResult = driver.findElement(this.Expected).getText();
        Assert.assertEquals(actualResult, expectedmessage, "not Matching");

    }
    @Step("Verify record displayed")
    public boolean VerifyRecordDisplayed(){
        return isRecordDisplayed();
    }
}
