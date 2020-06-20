package com.zerobank.library.stepdefinitions;

import com.zerobank.library.pages.DashboardMenuPagePage;
import com.zerobank.library.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class AccountSummaryStepDefs {
    DashboardMenuPagePage dashboardMenuPagePage = new DashboardMenuPagePage();

    @Then("Account summary page should have the following account types:{}")
    public void page_should_have_to_following_account_types(String types) {
        List<String> items = Arrays.asList(types.split("\\s*,\\s*"));
        for (int i = 0; i < items.size(); i++) {
            items.set(i,items.get(i).trim());
        }
        String boardText = null;
        WebElement boardElement;
        for (int i = 0; i < items.size(); i++) {
            boardText = dashboardMenuPagePage.getBoardHeadByIndex(i+1).getText();
            Assert.assertEquals(boardText,items.get(i));
            boardElement = dashboardMenuPagePage.getBoardHeadByName(items.get(i));
            Assert.assertTrue(boardElement.isDisplayed());
        }
    }

    @Then("{} table must have columns {}, {} and {}")
    public void table_must_have_columns(String tableName, String column1,String column2, String column3) {
        List<String> tableColumns = BrowserUtils.getElementsText(dashboardMenuPagePage.getTableColumnsByTableName(tableName));
        Assert.assertEquals(tableColumns.get(0),column1);
        Assert.assertEquals(tableColumns.get(1),column2);
        Assert.assertEquals(tableColumns.get(2),column3);
    }

}
