package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		else if (browserName.equalsIgnoreCase("edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
// String to HashMap- Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir"+"//reports//" + testCaseName +".png"));
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
