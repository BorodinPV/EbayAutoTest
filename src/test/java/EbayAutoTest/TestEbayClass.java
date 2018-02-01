package EbayAutoTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class TestEbayClass {

    private static WebDriver driver;
    private WebDriverWait wait = (new WebDriverWait(driver, 10));

    @BeforeClass
    public static void proDefault() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/pborodin/Desktop/DriverSelenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");

    }

    @Test
    public void userToRegister() {

        driver.findElement(By.xpath("/*//*[@id=\"gh-ug-flex\"]/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Page title is: " + driver.getTitle());

        WebElement firstname = driver.findElement(By.id("firstname"));
        firstname.sendKeys("Павел");

        WebElement lastname = driver.findElement(By.id("lastname"));
        lastname.sendKeys("Бородин");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("ebayautotest2@rambler.ru");

        WebElement PASSWORD = driver.findElement(By.id("PASSWORD"));
        PASSWORD.sendKeys("rwerer5w45GHSG");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("ppaFormSbtBtn")));
        WebElement ppaFormSbtBtn = driver.findElement(By.id("ppaFormSbtBtn"));
        ppaFormSbtBtn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("gh-ac")).sendKeys("blackberry");
        driver.findElement(By.id("gh-btn")).click();

        scrollDown("//*[@id=\"h2_rtm_csa_391\"]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"ListViewInner\"]/li[@class=\"sresult lvresult clearfix li shic\"]")));
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"ListViewInner\"]/li[contains(text(), sresult)]"));
        System.out.println("Размер Массива " + list.size());
    }

    public void scrollDown(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @AfterClass
    public static void testDown() {
        driver.quit();
    }
}