package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.UserInteractions;

public class HowOldAreYouHomePage extends UserInteractions{

	public HowOldAreYouHomePage(WebDriver driver) {
		super(driver);
	}

	final By howOldAreYouLabelBy = By.xpath(" //h1[contains(text(),'How old are you?')]");
	final By yourNameFieldBy = By.id("inputName");
	final By inputBirthdayFieldBy = By.id("inputBirthday");
	final By submitButtonBy = By.id("submitButton");
	final By salutationLabelBy = By.xpath("//h1[contains(text(),'Hello ')]");
	final By resultAgeBy = By.id("resultAge");
	final By dateErrorLAbelBy = By.id("help_birthday");
	final By nameErrorLabelBy=By.id("help_name");
	
	final String resultNameString = "//span[@id='resultName'][text()='%s%']";

	public void checkHowOldAreYouLabel() throws Exception {
		checkElement(howOldAreYouLabelBy,"How Old Are You Label");
	}

	public void inputYourNameField(String data) throws Exception {
		inputKeys(data, yourNameFieldBy, "Your Name Field", 10);	
	}

	public void inputBirthdayField(String data) throws Exception {
		inputKeys(data, inputBirthdayFieldBy, "Input Birthday Field", 10);	
	}
	
	public void clickSubmitButton() throws Exception {
		click(submitButtonBy,"Submit Button",10);
	}

	public void checksalutationLabel() throws Exception {
		checkElement(salutationLabelBy,"Salutation Label");
	}

	public void checkResultName(String name) throws Exception {
		By by = By.xpath(resultNameString.replace("%s%", name)) ;
		System.out.println(by);
		checkElement(by," Error Message : "+name);
	}

	public void compareResultAgeLabelText(String expectedAge) throws Exception {
		String actualAge = getElement(resultAgeBy,"Result Name Label").getText();
		CompareString(actualAge,expectedAge,"Result Age");
	}
	
	public void compareNameErrorLabelText(String expectedError) throws Exception {
		String actualError = getElement(nameErrorLabelBy,"Name Error Label").getText();
		CompareString(actualError,expectedError,"Name Error");
	}
	
	public void compareDateErrorLabelText(String expectedError) throws Exception {
		String actualError = getElement(dateErrorLAbelBy,"Date Error Label").getText();
		CompareString(actualError,expectedError,"Date Error");
	}

	public void CompareString(String actual,String expected,String comment) throws Exception {
		if(actual.equals(expected)) {
			ReportComparision("Pass",comment+" should be "+ expected,comment+" was "+ actual);
		}else {
			ReportComparision("Fail",comment+" should be "+ expected,comment+" was "+ actual);
		}	
	}

}
