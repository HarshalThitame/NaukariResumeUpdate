package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class EditProfilePage extends BasePage {
    @FindBy(xpath = "//input[@value='Update resume']")
    private WebElement updateButton;

    @FindBy(xpath = "//div[@class='card mt15']//div//span[@class='edit icon'][normalize-space()='editOneTheme']")
    private WebElement updateResumeHeading;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement updateResumeHeadingSaveButton;

    @FindBy(xpath = "//em[contains(@class,'icon edit')]")
    private WebElement updateProfileButton;
    @FindBy(xpath = "//button[@id='saveBasicDetailsBtn']")
    private WebElement saveProfileButton;
    @FindBy(xpath = "//div[@class='widgetHead typ-16Bold']//span[@class='edit icon'][normalize-space()='editOneTheme']")
    private WebElement updateSkillsButton;
    @FindBy(xpath = "//button[@id='saveKeySkills']")
    private WebElement saveSkillsButton;
    @FindBy(xpath = "//div[@class='profileSummary']//div[@class='card']//div//span[@class='edit icon'][normalize-space()='editOneTheme']")
    private WebElement updateProfileSummaryButton;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveProfileSummaryButton;

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickUpdateButton() {
        updateButton.click();
    }


    public void uploadResume() throws InterruptedException {

        Thread.sleep(2000);
        StringSelection filePathSelection = new StringSelection("C:\\Users\\hthit\\IdeaProjects\\Naukari\\testData\\Harshal_Resume.pdf");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(filePathSelection, null);
        Thread.sleep(2000);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

//    public void uploadResume() {
//        // Example XPath or ID — update this based on actual hidden file input
//        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
//        fileInput.sendKeys("/home/ubuntu/resume/Harshal_Resume.pdf");
//
//    }

    public void clickUpdateResumeHeading() {
        wait.until(ExpectedConditions.elementToBeClickable(updateResumeHeading));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", updateResumeHeading);
    }

    public void clickUpdateResumeHeadingSaveButton() {
        wait.until(ExpectedConditions.visibilityOf(updateResumeHeadingSaveButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", updateResumeHeadingSaveButton);
    }

    public void updateProfile() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(updateProfileButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(saveProfileButton)).click();
        Thread.sleep(2000);
    }

    public void updateSkills() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(updateSkillsButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(saveSkillsButton)).click();
        Thread.sleep(2000);
    }

    public void updateProfileSummary() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(updateProfileSummaryButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(saveProfileSummaryButton)).click();
        Thread.sleep(2000);
    }
}
