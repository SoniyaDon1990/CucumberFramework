package steps;

import java.io.IOException;
import helper.Data;
import helper.Driver;
import helper.Reporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Annotations {
	
	
	int stepNo = 0;
	static String FeatureName="";
	

	 @Before
	 public void initiateTest(Scenario scenario) {
		 
		 String featureName = getFeatureFileName(scenario);
		 System.out.println("feature Name is : "+featureName);
		 if(!FeatureName.equals(featureName)) {
		 FeatureName = featureName;
		 Reporter.createReportFile(FeatureName);
		 }
		
		 String scenarioName = scenario.getName();
		 System.out.println("Scenario is : "+scenarioName);
		 Reporter.addScenarioToReport(scenarioName);
		 
	 }
	 
	 @BeforeStep
	 public void beforeEachStep(Scenario scenario) {
		System.out.println("Step No: "+ (stepNo = stepNo+1)); 
	 }
	 
	 @After
	 public void afterSteps() {
		 Reporter.addStepsToReport();
		 Reporter.details.clear();
		 Driver.driver.close();
		
	 }
	 
	@After("@Last")
	 public void tearDown() {
		 Reporter.writeResults(); 
		 Driver.driver.close();
	 }
	
	private String getFeatureFileName(Scenario scenario) {
	    String featureName = "Feature ";
	    String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
	   
	    featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
	    String[] feat = featureName.split("/");
	    featureName = feat[feat.length-1];
	     String [] feat2 = featureName.split(":");
	     featureName = feat2[0];
	    return featureName.replace(".feature", "");
	}
	 
	
	 public void setData(String scenarioName) {
		 System.out.println("In given"+scenarioName);
			String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\data";
			Data data = new Data();
			try {
				data.setData(filePath,"TestData.xls","Data",scenarioName);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}

