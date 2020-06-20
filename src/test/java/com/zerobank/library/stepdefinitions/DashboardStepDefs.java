package com.zerobank.library.stepdefinitions;

import com.zerobank.library.pages.AccountActivityPage;
import com.zerobank.library.pages.DashboardMenuPagePage;
import com.zerobank.library.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardStepDefs {
    DashboardMenuPagePage dashboardMenuPagePage = new DashboardMenuPagePage();
    AccountActivityPage accountActivity = new AccountActivityPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);

    @Given("{} page should have the title {string}")
    public void account_summary_page_should_have_the_title(String page, String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
