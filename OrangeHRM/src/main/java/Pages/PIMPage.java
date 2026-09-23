package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PIMPage extends BasePage {
    private static int PIMMenuIndex = 1;
    private By Expected= By.xpath("//span[@class='oxd-text oxd-text--span']");
    private By Restbtn= By.xpath("//button[text()=' Reset ']");

    public PIMPage(WebDriver driver) {
        super(driver);
    }
    @Step("Click on PIM menu")
    public void ClickPIMMenu(){

        ClickMenuitem(PIMMenuIndex);

    }
    @Step("Search for Employee")
    public void SearchEmployee(String EmployeeID, String ExpectedResult) throws InterruptedException {
        driver.findElement(this.Restbtn).click();
        SreachRecord(EmployeeID);
        String actualResult = driver.findElement(this.Expected).getText();
        Assert.assertEquals(actualResult, ExpectedResult, "not Matching");
    }
    @Step("Verify record displayed")
    public boolean VerifyRecordDisplayed(){
        return isRecordDisplayed();
    }
}
