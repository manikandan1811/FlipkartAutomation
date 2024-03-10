package stepDefinition;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;

public class PageElements {
	
	public By searctTxt=By.xpath("//input[@name='q']");
	
	public By cartProduct=By.xpath("//span[@class='B_NuCI']");
	public By buyNowBtn=By.xpath("//button[@class='_2KpZ6l _2U9uOA ihZ75k _3AWRsL']");
	public By mobileNoTxt=By.xpath("//input[@class='_2IX_2- _17N0em']");
	public By continueBtn=By.xpath("//button[@class='_2KpZ6l _20xBvF _3AWRsL']");
	public By loginBtn=By.xpath("//button[@class='_2KpZ6l _20xBvF _3AWRsL']");
	public By addToCartBtn=By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']");
	public By pincodeSearch=By.id("pincodeInputId");
	public By checkBtn=By.xpath("//span[text()='Check']");
	public By enterOTPbtn=By.xpath("//input[@class='_2IX_2- _3umUoc _3mctLh _17N0em']");
	public By addAddressBtn=By.xpath("//div[@class='_1P2-0f']");
	public By nameTxt=By.xpath("//input[@name='name']");
	public By phoneTxt=By.xpath("//input[@name='phone']");
	public By pincodeTxt=By.xpath("//input[@name='pincode']");
	public By localityTxt=By.xpath("//input[@name='addressLine2']");
	public By addressTxt=By.xpath("//input[@name='addressLine1']");
	public By deliverHereBtn=By.xpath("//button[@class='_2KpZ6l _1JDhFS _3AWRsL']");
	public By modeCheck=By.xpath("//label[@for='WORK']//input");
	public By contin=By.xpath("//button[text()=\"CONTINUE\"]");
	public By acceptContinueBtn=By.xpath("//button[text()='Accept & Continue']");
	public By cashOnDelivery=By.xpath("//div[text()='Cash on Delivery']");
	public By orderChangeBtn=By.xpath("//div[text()='Order Summary']//parent::div//parent::div//button");
	public By orderTxt=By.xpath("//div[@class='_2Kn22P']");
}

