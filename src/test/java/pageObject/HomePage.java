package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[normalize-space()='View profile']")
    private WebElement profile;
    @FindBy(xpath = "//img[@alt='naukri user profile img']")
    private WebElement profileImg;
    @FindBy(xpath = "//a[@class='nI-gNb-info__sub-link']")
    private WebElement updateProfileButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", profile);
    }

    public void clickProfileImg() {
        wait.until(ExpectedConditions.elementToBeClickable(profileImg));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", profileImg);
    }

    public void clickUpdateProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(updateProfileButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", updateProfileButton);
    }

    public EditProfilePage gotoEditProfilePage() {
        return new EditProfilePage(driver);
    }
}
