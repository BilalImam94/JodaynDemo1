package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Home;
import com.JodaynDemo.pages.Products;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Products")
public class TC18_ProductViewCategory extends TestBasic {

    @Test(description = "Test Case 18: View Category Products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View Category Products")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that categories are visible on left side bar
            4. Click on 'Women' category
            5. Click on any category link under 'Women' category, for example: Dress
            6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
            7. On left side bar, click on any sub-category link of 'Men' category
            8. Verify that user is navigated to that category page""")
    public void viewCategoryProducts() {
        TC1_UserRegistration.verifyThatHomePageIsVisibleSuccessfully();
        verifyThatCategoriesAreVisibleOnLeftSideBar();
        verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts();
        verifyThatUserIsNavigatedToThatCategoryPage();
    }

    @Step("Verify that categories are visible on left side bar")
    private void verifyThatCategoriesAreVisibleOnLeftSideBar() {
        boolean categoriesAreVisible = new Home(getDriver())
                .getCategories()
                .isDisplayed();
        Assert.assertTrue(categoriesAreVisible, "Verify that categories are visible on left side bar");
    }

    @Step("Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'")
    private void verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts() {
        String titleTextCenter = new Home(getDriver())
                .womenCategoryClick()
                .dressCategoryClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "WOMEN - DRESS PRODUCTS", "Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'");
    }

    @Step("Verify that user is navigated to that category page")
    private void verifyThatUserIsNavigatedToThatCategoryPage() {
        String titleTextCenter = new Products(getDriver())
                .menCategoryClick()
                .tShirtsCategoryClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "MEN - TSHIRTS PRODUCTS", "Verify that user is navigated to that category page");
    }
}
