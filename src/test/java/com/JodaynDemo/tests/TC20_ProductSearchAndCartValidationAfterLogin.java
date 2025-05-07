package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Cart;
import com.JodaynDemo.pages.Home;
import com.JodaynDemo.pages.Products;
import com.JodaynDemo.utils.JSONReader;
import io.qameta.allure.*;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

@Epic("Regression Tests")
@Feature("Search")
public class TC20_ProductSearchAndCartValidationAfterLogin extends TestBasic {

    @Test(description = "Test Case 20: Search Products and Verify Cart After Login")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Products and Verify Cart After Login")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify user is navigated to ALL PRODUCTS page successfully
            5. Enter product name in search input and click search button
            6. Verify 'SEARCHED PRODUCTS' is visible
            7. Verify all the products related to search are visible
            8. Add those products to cart
            9. Click 'Cart' button and verify that products are visible in cart
            10. Click 'Signup / Login' button and submit login details
            11. Again, go to Cart page
            12. Verify that those products are visible in cart after login as well
            13. Remove all products that have been added
            14. Verify 'Cart is empty! Click here to buy products.' is visible""")
    public void searchProductsAndVerifyCartAfterLogin() throws IOException, ParseException, InterruptedException {
        TC8_ProductAndProductDetailsValidation.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        TC9_ProductSearch.verifySearchedProductsIsVisible();
        List<String> productsNames = TC9_ProductSearch.verifyAllTheProductsRelatedToSearchAreVisible();
        new Products(getDriver()).addAllProducts();
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
        new Home(getDriver())
                .signupLoginClick()
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"));
        verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(productsNames);
        verifyThatCartIsEmpty();
    }

    @Step("Click 'Cart' button and verify that products are visible in cart")
    private void clickCartButtonAndVerifyThatProductsAreVisibleInCart(@NotNull List<String> productsNames) {
        List<String> productsNamesAdded = new Home(getDriver())
                .cartButtonClick()
                .getProductsNames();
            Assert.assertEquals(productsNamesAdded.getFirst(), "Blue Top", "Verify that products are visible in cart");
    }

    @Step("Verify that those products are visible in cart after login as well")
    private void verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(@NotNull List<String> productsNames) {
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
    }

    @Step("Verify 'Cart is empty! Click here to buy products.' is visible")
    private void verifyThatCartIsEmpty() throws InterruptedException {
        String emptyCartText = new Cart(getDriver())
                .deleteAllAddedProducts()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify 'Cart is empty! Click here to buy products.' is visible");
    }
}
