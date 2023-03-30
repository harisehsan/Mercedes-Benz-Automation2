package stepdefinitions;

import getProperties.StepDefinationGetProperty;
import scripts.MercedesTestScript;
import base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class MercedezTaskSteps {

    /** This class is used to the bind to the Gherkin steps of Cucumber with the methods defined in scripts
     * This class also contains the validation checks that required to make the tests pass/fail
     * Total 8 validations are used in the test **/

    private BaseUtil base;
    MercedesTestScript mercedesTestScript;
    StepDefinationGetProperty stepDefinationGetProperty = new StepDefinationGetProperty();

    public MercedezTaskSteps(BaseUtil base) throws IOException {
        this.base = base;
        mercedesTestScript = new MercedesTestScript(base.driver);
    }

    @Given("Open Mercedes-benz United Kingdom market")
    public void iNavigateToTheMercedezBenzUKSite() throws InterruptedException, IOException {
        mercedesTestScript.navigateToMercedesBenzPageUK();
    }

    @When("Under Our Models - Select Model: {string}")
    public void iSelectHatchBackUnderOurModelSectionOnMainPage(String hatchBacks) throws InterruptedException, IOException {
      /** Validation 1: To verify that Hatchback should exist in the model list **/
      Assert.assertTrue(mercedesTestScript.selectHatchbacks(hatchBacks),stepDefinationGetProperty.validation1());
    }

    @Then("Mouse over the {string} model available and proceed to {string}")
    public void iVerifyThatAClassHatchbackShouldExistsInTheLists(String aClass,String buildYourCar) throws InterruptedException, IOException {
        /** Validation 2 & 3: To verify that Class A cars should exist in the Hatchback.
         * Build your car should be available for that  **/
        Assert.assertTrue(mercedesTestScript.mouseOverClassACar(aClass,buildYourCar), stepDefinationGetProperty.validation2());
    }

    @When("Filter by Fuel type {string}")
    public void iCheckThatFuelTypeShouldBeAvailable(String diesel) throws InterruptedException, IOException {
      mercedesTestScript.filterByDieselFuelType(diesel);
        /** Validation 4: To verify that atleast one item should exist in the catelog **/
      Assert.assertTrue(mercedesTestScript.oneOrMoreItemsShouldBePresentInCatelog(),stepDefinationGetProperty.validation3());
    }

    @Then("Should see only {string} fuel type items")
    public void iShouldSeeThatAllTheAvailableItemsFuelTypeShouldBeDiesal(String diesel) throws IOException {
        /** Validation 5: To verify that all the available items must have the fuel type diesel **/
    Assert.assertTrue(mercedesTestScript.allItemsMustHaveDiesalFuelType(diesel),stepDefinationGetProperty.validation4());
    }

    @Then("Verify that the catalog should be sorted according the default sorting method")
    public void iVerifyThatTheCatelogShouldBeSortedAccordigTheDefualtSortingMethod() throws IOException {
        /** Validation 6: To verify that all that the catalog must be sorted in the default sorting method (Ascending price order) **/
        Assert.assertTrue(mercedesTestScript.verifyTheItemCatelogIsSortedInTheDefaultOrder(),stepDefinationGetProperty.validation5());
    }

    @When("Save the screenshot of the resultant screen")
    public void iSaveTheScreenshotOfTheResultantScreen() throws IOException {
       mercedesTestScript.getScreenShotOfWebPage();
    }

    @Then("The screenshot should be present in the directory")
    public void theScreenshotShouldBePresentInTheDirectory() throws IOException {
        /** Validation 7: To verify that screenshot must be taken and saved in the specified directory **/
        Assert.assertTrue(mercedesTestScript.verifyTheScreenshotExists(),stepDefinationGetProperty.validation6());
    }

    @And("Save Highest and lowest price in the text file")
    public void iSaveHighestAndLowestPriceInTheTextFile() throws IOException {
        /** Save the lowest and highest price in the text file **/
        mercedesTestScript.saveTheLowestAndHighestPriceInTextFile();
    }

    @Then("Verify that the text file should exist and should contain price values")
    public void iVerifyThatLowestAndHighestPriceShouldBePresentInTheTextFile() throws IOException {
        /** Validation 8: To validate that text file must contain the values **/
    Assert.assertTrue(mercedesTestScript.verifyTheTextFileShouldExistAndNotEmpty(), stepDefinationGetProperty.validation7());
    }

    @Then("Verify that the price range should be between {string} and {string}")
    public void finallyIVerifyThatThePriceRangeShouldBeBetweenAnd(String arg0, String arg1) throws IOException {
        /** Validation 9: To validate all the prices must be between £15,000 and £60,000 **/
        Assert.assertTrue(mercedesTestScript.verifyThatAllItemsShouldBeBetweenSpecifiedRange(arg0,arg1),stepDefinationGetProperty.validation8());
    }
}
