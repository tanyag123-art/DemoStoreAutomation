package pageObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationAssignmentDemo.demoStore.PropertiesFIleReader;




public class DemoBlazePageObj {
	WebDriver driver;
	WebElement element;
	WebDriverWait wait;
	PropertiesFIleReader propertiesfileReader = new PropertiesFIleReader();
	JavascriptExecutor js = (JavascriptExecutor) driver;


	private String laptopOption = "//a[text()='Laptops']";
	private String laptopOption1 = "//a[text()='?']";
	private String addToCart = "//a[text()='Add to cart']";
	private String productHomePage = "//a[@id='nava']/img";
	private String cartButton = "//a[text()='Cart']";
	private String deleteBtn ="//td[text()='?']/following-sibling::td/a[text()='Delete']";
	private String placeOrderBtn= "//button[text()='Place Order']";
	private String placeOrderLabel = "//h5[text()='Place order']";

	private String nameofUser ="//label[text()='Name:']/following-sibling::input";
	private String countryofUser= "//label[text()='Country:']/following-sibling::input";
	private String cityofUser ="//label[text()='City:']/following-sibling::input";
	private String creditCardofUser ="//label[text()='Credit card:']/following-sibling::input";
	private String monthofUser = "//label[text()='Month:']/following-sibling::input";
	private String yearofUser ="//label[text()='Year:']/following-sibling::input";
	private String purchaseBtn  ="//button[text()='Purchase']";
	private String purchaseConfirm = "//h2[text()='Thank you for your purchase!']";
	private String purchaseSummary ="//p[@class='lead text-muted ']";


	public void navigates_to_demoBlazeWebsite() {
		initializeDriver();
		driver.get(propertiesfileReader.getApplicationUrl());
	}


	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver", propertiesfileReader.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(propertiesfileReader.getApplicationUrl());
	}


	public void selectACategory(String category) throws InterruptedException {
		try{
			Thread.sleep(5000);
			//			driver.switchTo().defaultContent();
			if(category.equals("Laptop")) {
				element = driver.findElement(By.xpath(laptopOption));
				//				js.executeScript("arguments[0].scrollIntoView();", element);
				element.click();
			}
		}
		catch(NoSuchElementException| NullPointerException e) {
			e.printStackTrace();
			System.out.println("Element is Null");
		}
	}


	public void addLaptopToCart(List<String> laptopforCart, String category) throws InterruptedException {
		Thread.sleep(6000);
		for (String laptopName : laptopforCart) {
			selectACategory(category);
			element = driver.findElement(By.xpath(laptopOption1.replace("?", laptopName)));
			element.click();
			wait = new WebDriverWait(driver, 3000);
			driver.findElement(By.xpath(addToCart)).click();
			//				wait = new WebDriverWait(driver, 8000);
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			driver.findElement(By.xpath(productHomePage)).click();
		}

	}


	public void deleteAProductfromCart(String laptopToDelete) throws InterruptedException {
		driver.findElement(By.xpath(cartButton)).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath(deleteBtn.replace("?", laptopToDelete))).click();
		Thread.sleep(4000);

	}

	public void enterText(String pathofElement, String textToEnter) {
		element = driver.findElement(By.xpath(pathofElement));
		element.clear();
		element.sendKeys(textToEnter);
	}


	public void placingOrder(List<String> personalDetails) {
		try {
			driver.findElement(By.xpath(placeOrderBtn)).click();

			Thread.sleep(4000);
			if(driver.findElement(By.xpath(placeOrderLabel)).isDisplayed()) {
				enterText(nameofUser, personalDetails.get(0));
				enterText(countryofUser, personalDetails.get(1));
				enterText(cityofUser, personalDetails.get(2));
				enterText(creditCardofUser, personalDetails.get(3));
				enterText(monthofUser, personalDetails.get(4));
				enterText(yearofUser, personalDetails.get(5));

				driver.findElement(By.xpath(purchaseBtn)).click();
				Thread.sleep(3000);
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}


	public void verifyThePurchase(String amount) {
		element= driver.findElement(By.xpath(purchaseConfirm));
		Assert.assertTrue("Purchase of Sonu Viao Laptop was not successful", element.isDisplayed());
		System.out.println("Purchase was successful");

		element= driver.findElement(By.xpath(purchaseSummary));
		String purchaseSummaryText = element.getText();

		System.out.println(purchaseSummaryText);
		Assert.assertTrue("Purchase amount displayed in the summary is as expected", purchaseSummaryText.contains(amount));
	}


	public void closeBrowser() {
		driver.close();
		driver.quit();
		
	}

}








