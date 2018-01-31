package EbayAutoTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestEbayClass {

    private static WebDriver driver;
    private  WebDriverWait wait = (new WebDriverWait(driver, 5));

    @BeforeClass
    public static void proDefault(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/pborodin/Desktop/DriverSelenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");

    }

    @Test
    public void userToRegister(){
        driver.findElement(By.xpath("//*[@id=\"gh-ug-flex\"]/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Page title is: " + driver.getTitle());

        WebElement firstname = driver.findElement(By.id("firstname"));
        firstname.sendKeys("Павел");
        WebElement lastname = driver.findElement(By.id("lastname"));
        lastname.sendKeys("Бородин");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("inadequte@gmail.ru");

        WebElement PASSWORD = driver.findElement(By.id("PASSWORD"));
        PASSWORD.sendKeys("rwerer5w45GHSG");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("ppaFormSbtBtn")));
        WebElement ppaFormSbtBtn = driver.findElement(By.id("ppaFormSbtBtn"));
        ppaFormSbtBtn.click();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void testDown() {
        driver.quit();
    }

}
