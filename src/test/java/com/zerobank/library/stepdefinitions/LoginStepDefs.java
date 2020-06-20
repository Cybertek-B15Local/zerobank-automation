package com.zerobank.library.stepdefinitions;

import com.zerobank.library.pages.DashboardMenuPagePage;
import com.zerobank.library.pages.LoginPage;
import com.zerobank.library.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();
    DashboardMenuPagePage dashboardMenuPagePage = new DashboardMenuPagePage();

    @Then("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String login, String password) {
        loginPage.login(login,password);
    }

    @Then("the {} page should be displayed")
    public void the_Account_summary_page_should_be_displayed(String pageName) {
        String currentTitle = Driver.getDriver().getTitle();
        String selectedMenu = dashboardMenuPagePage.selectedMenu.getText();

        Assert.assertEquals("Page is not matching as expected", pageName,selectedMenu);
        Assert.assertTrue("Title is not matching as expected", currentTitle.toLowerCase().contains(pageName.toLowerCase()));
    }

    @Then("Error message {} should be displayed.")
    public void error_message_should_be_displayed(String errorMessage) {
        Assert.assertEquals("Error message does NOT match",errorMessage,loginPage.errorMessage.getText());
    }

    @Then("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        loginPage.login("username","password");
    }






}
