package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Cart;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Cart")
public class TC17_ProductRemovalCart extends TestBasic {

    @Test(description = "Test Case 17: Remove Products From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove Products From Cart")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click 'X' button corresponding to particular product
            8. Verify that product is removed from the cart""")
    public void removeProductsFromCart() {
        TC1_UserRegistration.verifyThatHomePageIsVisibleSuccessfully();
        TC14_OrderPlaceRegisterDuringCheckout.verifyThatCartPageIsDisplayed();
        verifyThatProductIsRemovedFromTheCart();
    }

    @Step("Verify that product is removed from the cart")
    private void verifyThatProductIsRemovedFromTheCart() {
        String emptyCartText = new Cart(getDriver())
                .xButtonClick()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify that product is removed from the cart");
    }
}
