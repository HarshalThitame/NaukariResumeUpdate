package testCases;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public FileInputStream fis;
    public Logger log;
    public Properties config;

    @BeforeClass
    public void setUp() throws Exception {
        log = LogManager.getLogger(this.getClass());
        PropertyConfigurator.configure("./src/test/resources/log4j2.properties");
        log.info("**** Test execution started **** ");

        try {
            fis = new FileInputStream("./src/test/resources/config.properties");
            config = new Properties();
            config.load(fis);
            log.info(("Configuration file loaded successfully"));
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

        switch (config.getProperty("browser")) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                driver = new ChromeDriver();
                log.info("Chrome driver loaded successfully");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                log.info("Firefox driver loaded successfully");
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                log.info("Internet explorer driver loaded successfully");
                break;
            case "safari":
                driver = new SafariDriver();
                log.info("Safari driver loaded successfully");
                break;
            case "edge":
                driver = new EdgeDriver();
                log.info("Edge driver loaded successfully");
                break;
            default:
                System.out.println("Invalid browser selected");
                log.error("Invalid browser selected");
                break;
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(config.getProperty("url"));
    }

    @AfterClass
    public void tearDown() {
        log.info("**** Test execution finished **** ");
        if (driver != null) {
            driver.quit();
        }
    }
}
