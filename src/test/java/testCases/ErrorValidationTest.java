package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatloguePage;
import testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws IOException {
		lp.loginApplication("anshika@gmail.com", "Iaasamking@000");
        Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatloguePage pc = lp.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> prodlist = pc.getProductList();
		pc.addToCart(productName);
		CartPage cartPage = pc.goToCart();
		boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
 	}

}
