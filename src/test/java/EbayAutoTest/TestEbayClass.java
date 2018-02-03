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
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Title("Автотест на Ebay")
@Description("Тестовый сценарий")
public class TestEbayClass {

    private static WebDriver driver;

    @BeforeClass
    public static void proDefault() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Pavel/Desktop/DriverSelenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com/");

    }

    @Test
    public void runningTheScript(){
        userToRegister();
        searchElement();
        scroll("/*//*[@id=\"h2_rtm_csa_391\"]");
        amountOfElements();
        scroll("/*//*[@id=\"gh-ac\"]");
        exitAccounting();
        ExitCheck();
    }

    @Step("Регистрация нового пользователя")
    public void userToRegister() {
        driverXpathElement("//*[@id=\"gh-ug-flex\"]/a").click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Page title is: " + driver.getTitle());
        driverIdElement("firstname").sendKeys("Test");
        driverIdElement("lastname").sendKeys("Test");
        driverIdElement("email").sendKeys(getGenerate() + "@rambler.ru");
        driverIdElement("PASSWORD").sendKeys("rwerer5w45GHSG");
        driverWait().until(ExpectedConditions.elementToBeClickable(By.id("ppaFormSbtBtn")));
        driverIdElement("ppaFormSbtBtn").click();
    }

    @Step("Поиск элементов на Ebay")
    public void searchElement(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverIdElement("gh-ac").sendKeys("blackberry");
        driverIdElement("gh-btn").click();
    }

    @Step("Проверяем колличестов отображаемых элементов")
    public void amountOfElements(){
        driverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/*//*[@id=\"ListViewInner\"]/li[@class=\"sresult lvresult clearfix li shic\"]")));
        System.out.println("Размер Массива " + listElement("/*//*[@id=\"ListViewInner\"]/li[contains(text(), sresult)]").size());
    }

    @Step("Выход из учетной записи")
    public void exitAccounting(){
        driverWait().until(ExpectedConditions.elementToBeClickable(By.id("gh-ug")));
        Actions actions = new Actions(driver);
        actions.moveToElement(driverIdElement("gh-ug")).click().build().perform();
        actions.moveToElement(driverXpathElement("//div[@id=\"gh-eb-u-o\"]/ul/li[@id=\"gh-uo\"]/a")).click().build().perform();
    }

    @Step("Проверка успешности выхода из учетной записи")
    public void ExitCheck(){
        String exitText = driverXpathElement("/*//*[@id=\"AreaTitle\"]/div/table/tbody/tr/td/div/div[2]/div/h1/span").getText();
        if (exitText.equals("Выход успешно выполнен. До скорой встречи.")) {
            System.out.println(exitText);
            log("Выход успешно выполнен");
        } else {
            System.out.println("Ошибка при выходе из профиля");
            log("Ошибка при выходе из профиля");
        }
    }

    @Step("{0}")
    private void log(String value) {

    }


    public List listElement(String xpath){
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        log("Колличество эллементов в списке: " + list.size());
        return list;
    }

    public WebElement driverXpathElement(String xpath) {
        log("Переход по Xpath");
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement driverIdElement(String id) {
        log("Переход по Id " + id);
        return driver.findElement(By.id(id));
    }

    public void scroll(String xpath) {
        log("Скролл cтранички");
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public WebDriverWait driverWait() {
        log("Ожидание элемента");
        WebDriverWait wait = (new WebDriverWait(driver, 10));
        return wait;
    }

    public String getGenerate() {
        UUID email = UUID.randomUUID();
        log("Сгенерирован новый email");
        return email.toString().substring(0,6);
    }

    @AfterClass
    public static void testDown() {
        driver.quit();
    }

}