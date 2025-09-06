package testCases;

import org.testng.annotations.Test;
import pageObject.EditProfilePage;
import pageObject.HomePage;
import pageObject.LoginPage;

public class TC0001_LoginTest extends BaseClass {
    @Test
    public void testLogin() throws InterruptedException {
        log.info("*** TC0001_LoginTest started... *** ");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(config.getProperty("username"));
        loginPage.fillPassword(config.getProperty("password"));
        loginPage.clickLoginButton();

        HomePage homePage = loginPage.gotHomePage();
        homePage.clickProfileImg();
        homePage.clickUpdateProfileButton();
        EditProfilePage editProfilePage = homePage.gotoEditProfilePage();


//        editProfilePage.clickUpdateButton();
        editProfilePage.uploadResume();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        update heading
        editProfilePage.clickUpdateResumeHeading();
        editProfilePage.clickUpdateResumeHeadingSaveButton();

//        update Profile
        editProfilePage.updateProfile();
        editProfilePage.updateSkills();
//        editProfilePage.updateProfileSummary();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
