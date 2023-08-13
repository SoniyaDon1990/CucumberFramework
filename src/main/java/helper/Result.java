package helper;

public class Result {

	public Result(String result, String expectedResult, String actualResult, String screenPath) {
		this.result = result;
		this.expectedResult = expectedResult;
		this.actualResult = actualResult;
		this.screenPath = screenPath;
		// TODO Auto-generated constructor stub
	}
	
	String result;
	String description;
	String actualResult;
	String expectedResult;
	String screenPath;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	
	public String getActualResult() {
		return actualResult;
	}
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	
	public String getScreenPath() {
		return screenPath;
	}
	public void setScreenPath(String screenPath) {
		this.screenPath = screenPath;
	}
}
