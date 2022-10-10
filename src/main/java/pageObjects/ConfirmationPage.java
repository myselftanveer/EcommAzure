package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponentPage;

public class ConfirmationPage extends AbstractComponentPage {

	public WebDriver driver;

	public ConfirmationPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	private By thankyouMessage = By.cssSelector(".hero-primary");

	public String getConfirmationMessage() {
		CheckOutPage cp = new CheckOutPage(driver);
		waitForElementToAppear(thankyouMessage);
		return confirmationMessage.getText();
	}

}
