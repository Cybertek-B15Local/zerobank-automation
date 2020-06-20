package com.zerobank.library.stepdefinitions;

import com.zerobank.library.pages.DashboardMenuPagePage;
import com.zerobank.library.pages.PayBillsPage;
import com.zerobank.library.utilities.BrowserUtils;
import com.zerobank.library.utilities.Driver;
import com.zerobank.library.utilities.FileUtility;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PayeeStepDefs {
    PayBillsPage payBillsPage = new PayBillsPage();
    DashboardMenuPagePage dashboardMenuPagePage = new DashboardMenuPagePage();
    private File mostRecentFile;
    boolean fileFound;

    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> table) {
        payBillsPage.newPayeeNameInput.sendKeys(table.get("Payee Name"));
        payBillsPage.newPayeeAddressInput.sendKeys(table.get("Payee Address"));
        payBillsPage.newPayeeAccountInput.sendKeys(table.get("Account"));
        payBillsPage.newPayeeDetailsInput.sendKeys(table.get("Payee details"));
        payBillsPage.addNewPayeeButton.click();
    }

    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_Recent_Statements_Year(int year) {
        WebElement yearStatement = payBillsPage.getYearStatement(year + "");
        yearStatement.click();
    }

    @Then("{int} statements should be displayed for that {int}")
    public void statements_should_be_displayed_for_that_year(int numberOfStatements, int year) {
        List<WebElement> allStatements = payBillsPage.getAllStatements(year + "");
        Assert.assertEquals(numberOfStatements, allStatements.size());
    }


    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String string) {
        BrowserUtils.wait(1);
        Driver.getDriver().findElement(By.linkText(string)).click();
    }

    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String fileName) {
        BrowserUtils.wait(1);
        String downloadsFolder = System.getProperty("user.home") + "/Downloads/";
        mostRecentFile = FileUtility.getMostRecentFile(downloadsFolder);
        String name = mostRecentFile.getName();
        assertTrue(name.contains(fileName));
        fileFound=true;

    }

    @Then("the file type should be pdf")
    public void the_file_type_should_be_pdf() {
        assertTrue(mostRecentFile.getName().endsWith(".pdf"));
        if (fileFound) {
            mostRecentFile.delete();
        }
    }


}
