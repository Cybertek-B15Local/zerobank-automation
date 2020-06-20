package com.zerobank.library.utilities;
import java.util.*;
import com.zerobank.library.pages.AccountActivityPage;
import com.zerobank.library.pages.AccountSummaryPage;
import com.zerobank.library.pages.DashboardMenuPagePage;
import com.zerobank.library.pages.LoginPage;

public class StepDefsUtilities {
   static AccountActivityPage accountActivity = new AccountActivityPage();
    static  LoginPage loginPage = new LoginPage();
    static AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    static DashboardMenuPagePage dashboardMenuPagePage = new DashboardMenuPagePage();

    public static List<String> getListOf(String listOf){
       List<String> columnContent;
        columnContent = BrowserUtils.getElementsText(accountActivity.allDeposit);
        switch (listOf){
            case "deposit":
                columnContent = BrowserUtils.getElementsText(accountActivity.allDeposit);
                break;
            case "withdrawal":
                columnContent = BrowserUtils.getElementsText(accountActivity.allWithdrawal);
        }
        return columnContent;

    }
}
