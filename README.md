Task:
Write an acceptance test for http://www.xe.com/currencyconverter/
	The Acceptance criteria is to test multiple conversion rates
	The test should iterate over 5 sets of sample data.
	Example iteration :
	o The test should input from Euro to pounds.
	o Verify the result on the proceeding page.

	
Tools:
Eclipse
Maven
TestNG
Junit
Apache POI
log4j


Execution:
1. Just click on run.bat file to kick start the test execution of TC_Currency_Converter_001
2. Wait for few minutes till the browser gets closed to confirm the end of test execution
3. Open test-output folder and check for html test report with timestamp for execution details.

To run other tests, change the test case name in line 11 in testNG.xml
To run tests on other brosers like firefox and ie, just change the value to "firefox" or "ie" in line 9.

Thank You!






