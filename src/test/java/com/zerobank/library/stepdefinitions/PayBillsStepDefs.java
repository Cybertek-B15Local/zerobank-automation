package com.zerobank.library.stepdefinitions;

import com.zerobank.library.pages.PayBillsPage;
import com.zerobank.library.utilities.BrowserUtils;
import com.zerobank.library.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PayBillsStepDefs {


    Actions actions = new Actions(Driver.getDriver());
    PayBillsPage payBillsPage = new PayBillsPage();
    Random random = new Random();
    @Then("I complete a successful Pay operation")
    public void i_complete_a_successful_Pay_operation() {
        Select payeeOptions = new Select(payBillsPage.payeeOptions);
        int rand = random.nextInt(payeeOptions.getOptions().size());
        payeeOptions.selectByIndex(rand);

        Select accountOptions = new Select(payBillsPage.accountOptions);
        int rand2 = random.nextInt(accountOptions.getOptions().size());
        accountOptions.selectByIndex(rand2);

        int amountRandom = random.nextInt(100000);
        payBillsPage.amountInput.sendKeys(amountRandom+"");

        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        Date dateobj = new Date();
        payBillsPage.dateInput.sendKeys(df.format(dateobj));

        payBillsPage.descriptionInput.sendKeys("Sending money");

        payBillsPage.payButton.click();
    }

    @Then("(message ){} alert should be displayed")
    public void message_should_be_displayed(String expectedMessage) {
        if (expectedMessage.contains("success")) {
            Assert.assertTrue("Alert Box is not displayed", payBillsPage.alertMessageBox.isDisplayed());
            String actualMessage = payBillsPage.alertMessageBox.getText();
            Assert.assertEquals("Alert Box Message does not match: " + actualMessage, expectedMessage, actualMessage);
        } else {
           //TODO ... HTML5 FILL OUT THE FIELD IS HERE !!!
            if (payBillsPage.amountInput.getAttribute("value").isEmpty()) {
                String actualValidationMessage = payBillsPage.amountInput.getAttribute("validationMessage");
                Assert.assertEquals("Alert Box Message does not match",expectedMessage,actualValidationMessage);
            } else {
                String actualValidationMessage = payBillsPage.dateInput.getAttribute("validationMessage");
                Assert.assertEquals("Alert Box Message does not match",expectedMessage,actualValidationMessage);
            }
        }
    }

    @When("I try to make a payment without entering the amount or date")
    public void i_try_to_make_a_payment_without_entering_the_amount_or_date() {
            int dateOrAmount = random.nextInt(2)+1;

        Select payeeOptions = new Select(payBillsPage.payeeOptions);
        int rand = random.nextInt(payeeOptions.getOptions().size());
        payeeOptions.selectByIndex(rand);

        Select accountOptions = new Select(payBillsPage.accountOptions);
        int rand2 = random.nextInt(accountOptions.getOptions().size());
        accountOptions.selectByIndex(rand2);
        if (dateOrAmount==1){
            int amountRandom = random.nextInt(100000);
            payBillsPage.amountInput.sendKeys(amountRandom+"");
        } else {
            DateFormat df = new SimpleDateFormat("yy-MM-dd");
            Date dateobj = new Date();
            payBillsPage.dateInput.sendKeys(df.format(dateobj));
        }

        payBillsPage.descriptionInput.sendKeys("Sending money");

        payBillsPage.payButton.click();
    }

    @When("I enter date as an alphabetical value")
    public void i_enter_date_as_an_alphabetical_value() {
        payBillsPage.dateInput.sendKeys("~!@#$%^&*()_+asf!2e324fsd@//.,ad@345d1");
        payBillsPage.amountInput.sendKeys("1231241231233112");
    }

    @Then("The date input should contain only numeric value")
    public void the_date_input_should_contain_only_numeric_value() {
      boolean numbersOnly = false;
        String valueOfDate = payBillsPage.dateInput.getAttribute("value");
        numbersOnly =  valueOfDate.chars().allMatch(Character::isDigit );
        Assert.assertTrue(numbersOnly);
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> expectedCurrencyList) {
       WebElement element;
       Select listSelect = new Select(payBillsPage.currencyList);
        List<String> actualCurrencyList = BrowserUtils.getElementsText(listSelect.getOptions());
        actualCurrencyList.remove(0);
        for (String s : expectedCurrencyList) {
            Assert.assertTrue(actualCurrencyList.contains(s));
        }
    }


    @When("user tries to calculate cost without selecting/entering a {}")
    public void user_tries_to_calculate_cost_without_selecting(String enterValueTo) {
        Random rand = new Random();
        if (enterValueTo.equalsIgnoreCase("currency")){
            int i = random.nextInt();
            payBillsPage.amountInputForeignCash.sendKeys(i+"");
        } else if (enterValueTo.equalsIgnoreCase("value")){
            Select allCurrency = new Select(payBillsPage.currencyList);
            int randomNr = rand.nextInt(allCurrency.getOptions().size());
            allCurrency.selectByIndex(randomNr);
        }
        payBillsPage.calculateCostsButton.click();
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        Alert alert = Driver.getDriver().switchTo().alert();
        Assert.assertFalse(alert.getText().isEmpty());
        alert.accept();


    }

}
