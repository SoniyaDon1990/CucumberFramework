package steps;


import helper.Data;
import helper.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import pages.HowOldAreYouHomePage;


public class HowOldAreYouChecksStepDefenitions extends Driver {

	Annotations ano = new Annotations();
	Driver driverHelp = new Driver();
	Data data = new Data();

	@Given ("User navigates to site HowOldAreYou")
	public void navigateToSite() throws Exception {
		driver = driverHelp.openWebPage("https://howoldru.m-messiah.cc/");
	}
	
	@When ("user is in How old are you homepage")
	public void checkLoginPage() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.checkHowOldAreYouLabel();
	}
	
	@And ("user fetch testData for {string}")
	public void setUpData(String scenarioName) {
		ano.setData(scenarioName);	
		System.out.println(data.getData("YourAge"));
	}
	
	@And ("user fill user details")
	public void fillUserDetails() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.inputYourNameField(data.getData("YourName"));
		howOldPage.inputBirthdayField(data.getData("Birthdate"));
	}
	
	@And ("user fill date")
	
	public void fillDate() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.inputBirthdayField(data.getData("Birthdate"));
	}
	
	@And ("user fill name")
	
	public void fillName() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.inputYourNameField(data.getData("YourName"));
	}

	
	@And ("user Submits form")
	public void submitForm() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.clickSubmitButton();
	}
	
	@Then ("user should be directed to Results Page")
	public void checkResultPage() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.checksalutationLabel();
	}
	
	@And ("users name should be dislayed correctly")
	public void checkName() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.checkResultName(data.getData("YourName"));
	}
	
	@And ("users age should be calculated correctly")
	public void checkAge() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.compareResultAgeLabelText(data.getData("YourAge"));
	}
	
	@Then ("user should be getting name error")
	public void getNameError() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.compareNameErrorLabelText(data.getData("ErrorMessage"));
	}
	
	@Then ("user should be getting date error")
	public void getDateError() throws Exception {
		HowOldAreYouHomePage howOldPage = new HowOldAreYouHomePage(driver);
		howOldPage.compareDateErrorLabelText(data.getData("ErrorMessage"));
	}

} 

