package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static String LoginURl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private By Username = By.xpath("//input[@placeholder='Username']");
    private By Password = By.xpath("//input[@placeholder='Password']");
    private By Loginbtn = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate to Url")
    public void NavigateToUrl(String url) {
        driver.get(LoginURl);
    }

    @Step("Login with Credentials")
    public void Login(String username, String password) {
        driver.findElement(this.Username).sendKeys(username);
        driver.findElement(this.Password).sendKeys(password);
        driver.findElement(this.Loginbtn).click();

        attachScreenshot("After Login");
    }
}

