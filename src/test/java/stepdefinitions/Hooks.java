package stepdefinitions;

import base.BaseUtil;
import base.ScreenRecorderUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import getProperties.HooksGetProperty;
import getProperties.ScriptGetProperty;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


import java.io.File;
import java.io.IOException;

/** This hook class the related to cucumber framework that contains two important methods
 * Before tag - Make the method to execute before the test starts
 * After tag - Make the method to execute after the completion of the test **/

public class Hooks extends BaseUtil {

    private String scenarioName;
    private BaseUtil base;
    HooksGetProperty hooksGetProperty = new HooksGetProperty();
    ScriptGetProperty scriptGetProperty = new ScriptGetProperty();


    public Hooks(BaseUtil base) {
        this.base = base;
    }

    /** This method executes the webDriver to open the required browser for testing and also start the video recording of browser **/
    @Before
    public void InitializeTest(Scenario scenario) throws Exception {

        try {
            if (System.getProperty("env").equalsIgnoreCase("edge"))
                startEdgeDriver();  /** Start the test on the Edge browser if specified in the command line**/
           else
                startChromeDriver(); /** Start the test on the Chrome browser as a default Browser**/
        } catch (Exception e) {
            startChromeDriver(); /** Start the test on the Chrome browser if any exception occurred **/
        }
//        driver.manage().window().maximize();
//        driver.manage().window().fullscreen();
        scenarioName = scenario.getName();
        ScreenRecorderUtil.startRecord(scenarioName); /** Start the video recoding **/

    }

    /** This method will quit the driver, stop the video recording of the test attach the following file to the allure report
     * Boundary_price.txt - That contains the lowest and highest price value available on the final screen
     * screenshot.png - Screenshot of the final screen
     * Video.mp4 - Recorded video of the test **/

    @After
    public void TearDownTest(Scenario scenario) throws Exception {
        driver.quit();
        try {
            ScreenRecorderUtil.stopRecord();
            driver.quit();
//        if (scenario.isFailed()) {

//        }
            Allure.addAttachment(hooksGetProperty.boundryPrice(),FileUtils.openInputStream(new File(scriptGetProperty.textFilePath())));
            Allure.addAttachment(hooksGetProperty.screenshot(),FileUtils.openInputStream(new File(scriptGetProperty.screenShotPath())));
            Allure.addAttachment(hooksGetProperty.scenarioVideo(),FileUtils.openInputStream(new File(hooksGetProperty.videoPath())));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void startChromeDriver() throws IOException {
        if (System.getProperty(hooksGetProperty.osName()).contains(hooksGetProperty.windows()))
            System.setProperty(hooksGetProperty.chromeDriver(), hooksGetProperty.chromeDriverWindowsPath());
        else
            System.setProperty(hooksGetProperty.chromeDriver(), hooksGetProperty.chromeDriverLinuxPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments(hooksGetProperty.remoteAllowOrigin());
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    private void startEdgeDriver() throws IOException {
        System.setProperty(hooksGetProperty.edgeDriver(), hooksGetProperty.edgeDriverPath());
        EdgeOptions options = new EdgeOptions();
        options.addArguments(hooksGetProperty.edgeDriverOptions());
        driver = new EdgeDriver(options);
    }
}
