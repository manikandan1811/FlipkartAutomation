package stepDefinition;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class FlipkartStepDefinition {

	public WebDriver driver;
	private Scenario myScenario;
	private static final Logger lOGGER = LoggerFactory.getLogger(FlipkartStepDefinition.class);
	PageElements elements = new PageElements();
	String userDir = System.getProperty("user.dir");
	String screenshotPath = userDir + "\\ScreenShots";
	private String productName;

	@Given("Launch the url {string}")
	public void launchTheUrl(String url) {
		driver.get(url);
	}

	@When("Select the {string} product")
	public void selectTheProduct(String count) {
		By product = By.xpath("(//div[@class='_2kHMtA'])[" + count + "]");
		By productName = By.xpath("(//div[@class='_2kHMtA'])[" + count + "]//img");
		waitForVisibilityOfElement(product);
		String name = getAttributeValueon(productName, "alt");
		setProductName(name);
		clickOn(product);
	}
	
	@When("Check deliver pincode as {string}")
	public void checkDeliverPincode(String pincode) throws Exception {
		switchToNewWindow();
		enterValueOn(By.id("pincodeInputId"), pincode);
		clickOn(elements.checkBtn);
	}

	@When("Check the item is added to the cart")
	public void checkTheItemIsAddedToTheCart() throws Exception {
		switchToNewWindow();
		waitForVisibilityOfElement(elements.cartProduct);
		String cartValue = driver.findElement(elements.cartProduct).getText();
		System.out.println(cartValue);
		assert cartValue.contains(getProductName().substring(0, getProductName().length() - 3))
				: "Expected string: '" + getProductName() + "' not found in actual string: '" + cartValue + "'";
		captureScreenShot();
	}

	@When("click on Buy now button")
	public void clickOnBuyNowButton() throws Exception {
		clickOn(elements.buyNowBtn);
	}

	@When("Enter the login credentials {string}")
	public void enterTheLoginCredentials(String mobileNo) throws Exception {
		enterValueOn(elements.mobileNoTxt, mobileNo);
		clickOn(elements.continueBtn);
		clickOn(elements.loginBtn);
	}

	@When("Enter shipping details")
	public void enterShippingDetails(DataTable dataTable) throws Exception {
		Map<String, String> inputData;
		inputData = dataTable.asMap(String.class, String.class);
		waitForVisibilityOfElement(elements.addAddressBtn);
	}

	@When("Enter {string} in a search text")
	public void EnterInSearchText(String text) throws Exception {
		waitForVisibilityOfElement(elements.searctTxt);
		driver.findElement(elements.searctTxt).sendKeys(text);
		driver.findElement(elements.searctTxt).sendKeys(Keys.ENTER);
	}
	
	@When("Verify Order Summary")
	public void verify_order_summary() {
		
	}
	@When("click on continue button")
	public void click_on_continue_button() {
		
	}
	@When("Select payment options")
	public void select_payment_options() {
		
	}

	private void waitForVisibilityOfElement(By visbleElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visbleElement));
	}

	private void waitForVisibilityOfElement(By visbleElement, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visbleElement));
	}

	private void clickOn(By element) {
		waitForVisibilityOfElement(element);
		driver.findElement(element).click();
	}

	private void clickJSEon(By element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(element));

	}

	private void scrollToElement(By element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
	}

	private void switchToNewWindow() {
		String currentWindow = driver.getWindowHandle();
		Iterator<String> windows = driver.getWindowHandles().iterator();
		while (windows.hasNext()) {
			String window = windows.next();
			if (!currentWindow.equalsIgnoreCase(window)) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				break;
			}
		}
	}

	@Before
	public void launchBrowser(Scenario scenario) throws Exception {
		myScenario = scenario;
		myScenario.getId();

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			try {
				captureScreenShot();
			} catch (Exception e) {
			}
		}
		driver.close();
		driver.quit();
	}

	public void captureScreenShot() {
		try {
			Robot robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenCapture = robot.createScreenCapture(screenRect);
			String filePath = "" + screenshotPath + "/screenshot.png";
			File screenshotFile = new File(filePath);
			ImageIO.write(screenCapture, "png", screenshotFile);

			System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAttributeValueon(By element, String attribute) {
		String atrVal = null;
		waitForVisibilityOfElement(element, 10);
		atrVal = driver.findElement(element).getAttribute(attribute);
		return atrVal;
	}

	public void enterValueOn(By element,String value) {
		waitForVisibilityOfElement(element);
		driver.findElement(element).sendKeys(value);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
