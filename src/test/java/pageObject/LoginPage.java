package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='usernameField']")
    private WebElement username;
    @FindBy(xpath = "//input[@id='passwordField']")
    private WebElement password;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsername(String u) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='usernameField']")));
        username.sendKeys(u);
    }

    public void fillPassword(String password) {
        this.password.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginButton);
    }

    public HomePage gotHomePage() {
        return new HomePage(driver);
    }
}
