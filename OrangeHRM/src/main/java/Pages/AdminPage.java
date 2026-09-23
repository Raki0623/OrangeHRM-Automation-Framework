package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdminPage extends BasePage {
private static int AdminMenuIndex = 0;
private By Expected= By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span");
private By restBtn= By.xpath("//button[text()=' Reset ']");


    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver=driver;

    }
    @Step("Click on admin menu")
    public void ClickAdminMenu(){

        ClickMenuitem(AdminMenuIndex);

    }
    @Step("Search for User")
    public void SearchUser(String username,String ExpectedResult) throws InterruptedException {
        driver.findElement(this.restBtn).click();
        SreachRecord( username);
        String actualResult = driver.findElement(this.Expected).getText();
        Assert.assertEquals(actualResult, ExpectedResult, "not Matching");
    }
    @Step("Verify record displayed")
    public boolean VerifyRecordDisplayed(){
        return isRecordDisplayed();
    }


}

