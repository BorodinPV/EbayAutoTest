package EbayAutoTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestEbayClass {

    private static WebDriver driver;

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

        driverXpathElement("//*[@id=\"gh-ug-flex\"]/a").click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Page title is: " + driver.getTitle());

        driverIdElement("firstname").sendKeys("Test");

        driverIdElement("lastname").sendKeys("Test");

        driverIdElement("email").sendKeys("ebayautotest19@rambler.ru");

        driverIdElement("PASSWORD").sendKeys("rwerer5w45GHSG");


        driverWait().until(ExpectedConditions.elementToBeClickable(By.id("ppaFormSbtBtn")));
        driverIdElement("ppaFormSbtBtn").click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverIdElement("gh-ac").sendKeys("blackberry");
        driverIdElement("gh-btn").click();

        scroll("/*//*[@id=\"h2_rtm_csa_391\"]");
        driverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/*//*[@id=\"ListViewInner\"]/li[@class=\"sresult lvresult clearfix li shic\"]")));
        List<WebElement> list = driver.findElements(By.xpath("/*//*[@id=\"ListViewInner\"]/li[contains(text(), sresult)]"));
        System.out.println("Размер Массива " + list.size());
        scroll("/*//*[@id=\"gh-ac\"]");

        driverWait().until(ExpectedConditions.elementToBeClickable(By.id("gh-ug")));
        Actions actions = new Actions(driver);
        actions.moveToElement(driverIdElement("gh-ug")).click().build().perform();
        actions.moveToElement(driverXpathElement("//div[@id=\"gh-eb-u-o\"]/ul/li[@id=\"gh-uo\"]/a")).click().build().perform();

        String exitText = driverXpathElement("/*//*[@id=\"AreaTitle\"]/div/table/tbody/tr/td/div/div[2]/div/h1/span").getText();
        if (exitText.equals("Выход успешно выполнен. До скорой встречи.")) {
            System.out.println(exitText);
        } else {
            System.out.println("Ошибка при выходе из профиля");
        }
    }

    //Поиск элемента по Xpath
    public WebElement driverXpathElement(String xpath) {

        return driver.findElement(By.xpath(xpath));
    }

    //Поиск элемента по Id
    public WebElement driverIdElement(String id) {

        return driver.findElement(By.id(id));
    }

    //Скролл для прогрузки всех элементов
    public void scroll(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Явное ожидание
    public WebDriverWait driverWait() {
        WebDriverWait wait = (new WebDriverWait(driver, 10));
        return wait;
    }

    @AfterClass
    public static void testDown() {
        driver.quit();
    }

}