package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatloguePage;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	/***************************************************************************
	 * Using DataProvider
	 *******************************************************/
//	@Test(groups = {"Purchase"}, dataProvider = "loginData")
//	public void submitOrder(String email,String password,String productName) throws IOException {
//		
//		ProductCatloguePage pc = lp.loginApplication(email,password);
//		// ProductCatloguePage pc = new ProductCatloguePage(driver);
//		List<WebElement> prodlist = pc.getProductList();
//		pc.addToCart(productName);
//		CartPage cartPage = pc.goToCart();
//
//		boolean match = cartPage.VerifyProductDisplay(productName);
//		Assert.assertTrue(match);
//		CheckOutPage checkOut = cartPage.goToCheckout();
//		checkOut.selectCountry("india");
//		ConfirmationPage confirm = checkOut.submitOrder();
//		String confirmMessage = confirm.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.contains("THANKYOU"));
//	}
	/***************************************************************************
	 * Using Hashmap
	 *******************************************************/

//	@Test(groups = {"Purchase"}, dataProvider = "loginData")
//	public void submitOrder(HashMap<String,String> input) throws IOException {
//		
//		ProductCatloguePage pc = lp.loginApplication(input.get("email") ,input.get("password"));
//		// ProductCatloguePage pc = new ProductCatloguePage(driver);
//		List<WebElement> prodlist = pc.getProductList();
//		pc.addToCart(input.get("product"));
//		CartPage cartPage = pc.goToCart();
//
//		boolean match = cartPage.VerifyProductDisplay(input.get("product"));
//		Assert.assertTrue(match);
//		CheckOutPage checkOut = cartPage.goToCheckout();
//		checkOut.selectCountry("india");
//		ConfirmationPage confirm = checkOut.submitOrder();
//		String confirmMessage = confirm.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.contains("THANKYOU"));
//	}
	/***************************************************************************
	 * Using JSON
	 *******************************************************/
	@Test(groups = { "Purchase" }, dataProvider = "loginData")
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatloguePage pc = lp.loginApplication(input.get("email"), input.get("password"));
		// ProductCatloguePage pc = new ProductCatloguePage(driver);
		List<WebElement> prodlist = pc.getProductList();
		pc.addToCart(input.get("product"));
		CartPage cartPage = pc.goToCart();

		boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckout();
		checkOut.selectCountry("india");
		ConfirmationPage confirm = checkOut.submitOrder();
		String confirmMessage = confirm.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.contains("THANKYOU"));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() {
		ProductCatloguePage pc = lp.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = pc.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrdertDisplay(productName));
	}

//	@DataProvider(name="loginData")
//	public Object[][] getData() {
//		return new Object[][] {{"anshika@gmail.com", "Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" }};
//	}

//	@DataProvider(name="loginData")
//	public Object[][] getData(){
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
//		return new Object[][]{ {map},{map1} };
//	}

	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap("./src/test/java/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
