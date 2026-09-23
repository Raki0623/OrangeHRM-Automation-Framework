import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class OrangeHRM {

    WebDriver driver;
    private BasePage basePage;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private PIMPage pimPage;
    private TimePage timePage;

    @BeforeMethod
    @Step("Setup browser instance and initialize framework components")
    @Description("Initialize dynamic thread-safe Web Driver session")
    public void setup() {
        String browserType = ConfigReader.getProperty("browser");

        // Add null safety checks to handle cloud environment variables safely
        if (browserType != null && browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else {
            // Fallback execution logic: If browserType is missing or null, default to Headless Chrome
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }

        BasePage.setDriver(driver);

        driver.manage().window().maximize();
        String waitTime = ConfigReader.getProperty("implicitWait");
        int implicitWait = (waitTime != null) ? Integer.parseInt(waitTime) : 10;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        basePage  = new BasePage(driver);
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        pimPage   = new PIMPage(driver);
    }
    @Test(priority = 1)
    @Description("Verify User is Admin or Not")
    @Severity(SeverityLevel.CRITICAL)
    public void VerifyAdmin() throws IOException, InterruptedException {
        Properties properties= new Properties();
        FileInputStream file= new FileInputStream("D:\\Java Programming\\TestNG\\Selenium\\Selenium\\src\\main\\resources\\config.properties");
        properties.load(file);
        String Url=properties.getProperty("url");
        String Username=properties.getProperty("username");
        String Password=properties.getProperty("password");

        loginPage.NavigateToUrl(Url);
        loginPage.Login(Username,Password);
        adminPage.ClickAdminMenu();

        String excelpath="D:\\Java Programming\\TestNG\\Selenium\\ProjectOrangeHRM\\OrangeHRM\\src\\main\\resources\\TestData.xlsx";
        FileInputStream file1=new FileInputStream(excelpath);

        XSSFWorkbook wb=new XSSFWorkbook(file1);

        XSSFSheet sheet=wb.getSheet("Sheet1");

        int RowCount= wb.getSheet("Sheet1").getLastRowNum();
        int cellcount= wb.getSheet("Sheet1").getRow(0).getLastCellNum();

        for(int i=1;i<=RowCount;i++)
        {
            String username=sheet.getRow(i).getCell(0).getStringCellValue();
            String expectedValue=sheet.getRow(i).getCell(1).getStringCellValue();
            System.out.println("Username: "+username+" Expected Value: "+expectedValue);
            adminPage.SearchUser(username,expectedValue);
        }


        adminPage.VerifyRecordDisplayed();
        basePage.Logout();
    }
    @Test(priority = 2)
    @Description("Verify Employee exists or not")
    @Severity(SeverityLevel.CRITICAL)
    public void VerifyPIM() throws IOException, InterruptedException {
        Properties properties= new Properties();
        FileInputStream file= new FileInputStream("D:\\Java Programming\\TestNG\\Selenium\\Selenium\\src\\main\\resources\\config.properties");
        properties.load(file);
        String Url=properties.getProperty("url");
        String Username=properties.getProperty("username");
        String Password=properties.getProperty("password");


        loginPage.NavigateToUrl(Url);
        loginPage.Login(Username, Password);
        pimPage.ClickPIMMenu();
        String excelpath="D:\\Java Programming\\TestNG\\Selenium\\ProjectOrangeHRM\\OrangeHRM\\src\\main\\resources\\TestData.xlsx";
        FileInputStream file1=new FileInputStream(excelpath);

        XSSFWorkbook wb=new XSSFWorkbook(file1);

        XSSFSheet sheet=wb.getSheet("Sheet2");

        int RowCount= wb.getSheet("Sheet2").getLastRowNum();
        int cellcount= wb.getSheet("Sheet2").getRow(0).getLastCellNum();

        for(int i=1;i<=RowCount;i++)
        {
            String Employeid=sheet.getRow(i).getCell(0).getStringCellValue();
            String expectedValue=sheet.getRow(i).getCell(1).getStringCellValue();

            System.out.println("EmployeeID: "+Employeid+" Expected Value: "+expectedValue);
            pimPage.SearchEmployee(Employeid,expectedValue);

        }
        pimPage.VerifyRecordDisplayed();
        basePage.Logout();
    }
    public void VerifyTime() throws IOException, InterruptedException {
        Properties properties= new Properties();
        FileInputStream file= new FileInputStream("D:\\Java Programming\\TestNG\\Selenium\\Selenium\\src\\main\\resources\\config.properties");
        properties.load(file);
        String Url=properties.getProperty("url");
        String Username=properties.getProperty("username");
        String Password=properties.getProperty("password");


        loginPage.NavigateToUrl(Url);
        loginPage.Login(Username, Password);
        timePage.ClickTimeMenu();
        String excelpath="D:\\Java Programming\\TestNG\\Selenium\\ProjectOrangeHRM\\OrangeHRM\\src\\main\\resources\\TestData.xlsx";
        FileInputStream file1=new FileInputStream(excelpath);

        XSSFWorkbook wb=new XSSFWorkbook(file1);

        XSSFSheet sheet=wb.getSheet("Sheet3");

        int RowCount= wb.getSheet("Sheet3").getLastRowNum();
        int cellcount= wb.getSheet("Sheet3").getRow(0).getLastCellNum();

        for(int i=1;i<=RowCount;i++)
        {
            String name=sheet.getRow(i).getCell(0).getStringCellValue();
            String expectedmessage=sheet.getRow(i).getCell(1).getStringCellValue();

            System.out.println("Employeename: "+name+" Expected message: "+expectedmessage);
            timePage.Searchtime(name,expectedmessage);

        }
        timePage.VerifyRecordDisplayed();
        basePage.Logout();
    }
    @AfterMethod
    public void teardown()
    {
        if(driver!= null)
        {
            driver.quit();
        }
    }
}
