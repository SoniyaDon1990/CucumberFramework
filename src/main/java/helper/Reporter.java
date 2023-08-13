package helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reporter {
	public static List<Result> details = new ArrayList<Result>();
	public static String templatePath = System.getProperty("user.dir")+"\\src\\main\\resources\\Report\\template.html";
	private static String resultPlaceholder = "<!-- INSERT_RESULTS -->"	;
	public static String currentDate = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
	public static String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\report_" + currentDate;
	public static int counter = 0;
	public static File filePath;
	public static String reportPath="";
	public static File file ;
	public static String reportIn="";
	
	public static String tableTemp = "<tr>\r\n"
			+ "<th colspan=\"4\"> Scenario Name  : Scenario_Name_Holder </th>\r\n"
			+ "</tr>\r\n"
			+ "<tr>\r\n"
			+ "<th width=\"10%\">Step</th>\r\n"
			+ "<th width=\"40%\">Expected Result</th>\r\n"
			+ "<th width=\"40%\">Actual Result</th>\r\n"
			+ "<th width=\"10%\">Result</th>\r\n"
			+ "</tr>";

	public static void report(String status, String expectedValue,String actualValue, String screenPath) {

			Result r = new Result(status,expectedValue,actualValue,screenPath);
			details.add(r);
	
	}
	
	public static void createReportFile (String featurName) {
		try {
		reportIn = new String(Files.readAllBytes(Paths.get(templatePath)));
		reportIn = reportIn.replace("FeatureName_Holder", featurName);
		
		filePath = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\report_" + currentDate);
		if (!filePath.mkdirs())
		{
		   System.err.println("Could not create parent directories ");
		}
		file = new File(filePath, "DetailedReport_"+featurName+".html");
		file.createNewFile();
		reportPath=filePath+"\\DetailedReport_"+featurName+".html";
		}catch(Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
		
	}
	
	public static void addScenarioToReport(String scenarioName) {
		try {
			String addition = tableTemp.replace("Scenario_Name_Holder", scenarioName);
			reportIn = reportIn.replace(resultPlaceholder, addition+resultPlaceholder);
		}catch(Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}
	
	public static void addStepsToReport() {
		try {
			for (int i = 0; i < details.size();i++) {
				String scrPath = details.get(i).getScreenPath();
				scrPath=scrPath.replace("\\", "\\\\");
				reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + Integer.toString(i+1)  + "</td><td>" + details.get(i).getExpectedResult() + "</td><td><a href=\""+scrPath+"\"  target=\"_blank\">"+ details.get(i).getActualResult() + "</a></td>"+ "</td><td>" + details.get(i).getResult()+"</tr>" + resultPlaceholder);
			}
			
			
		} catch (Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}
	

	public static void writeResults() {
		try {

			for (int i = 0; i < details.size();i++) {
				String scrPath = details.get(i).getScreenPath();
				scrPath=scrPath.replace("\\", "\\\\");
				reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + Integer.toString(i+1)  + "</td><td>" + details.get(i).getExpectedResult() + "</td><td><a href=\""+scrPath+"\"  target=\"_blank\">"+ details.get(i).getActualResult() + "</a></td>"+ "</td><td>" + details.get(i).getResult()+"</tr>" + resultPlaceholder);
			}
			
			reportIn=reportIn.replace("<!-- INSERT_RESULTS -->", "");

			System.out.println("writing to"+ file.getAbsolutePath());
			Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.CREATE);
			reportIn="";

		} catch (Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}
	
	public static String  screenPath() {
		counter = counter+1;
		String reportPath = path + "\\screens\\"+counter+".png";
		return reportPath;
		
	}

}
