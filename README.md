## Selenium Automation Project 
This repository contains the source code for automation framework designed using Selenium following the Page Object Model and using TestNG . 
Below is the overall architecture of the framework.

![image](https://github.com/user-attachments/assets/4325fbe3-d388-4ec7-a9fc-7adb32781807)

# Working Flow of the framework 
<ul>
<li>First if we use maven command to execute the TestSuite, then maven command will check the profile selected and search for the profile in POM.xml file</li>
<li>Then on finding the profile the xml for the profile will be triggered , the xml file has all the information on which test to execute ,so it will go to the test cases. </li>
<li>Before the Test start executing the method with @BeforeMehod annotation is executed in which all the browser configuration is done and is present in BaseTest class which is inherited by all the test Classes and the first test object required for test execution ic created and returned.</li>
<li>After this the control goes to the listeners for the onTestStart() where we create the report.</li>
<li>Then the test case will need Some actions to be performed so the control goes to the Page classes when all the logic for actions is present and PageFactory is used, the Page classes interit the utility class which contains all the reusable code.</li>
<li>If our test case requires data for its execution then data is provided in form of JSON file . The JSON data is passed to the Base test class when we have the logic to covert the JSON data to HashMap. The test case will use TestNG data provider to fetch this data and use it in form of HashMap.</li>
<li>There is also a retry mechanism in the framework to rerun the flaky failed test cases which is implemented using the IRetryAnalyzer.</li>
<li>Then based on the output of the test, the listeners listen to the events and write appropriate data to the extent reports </li>
<li>Finally the method with @AfterMethod annotation is executed to gracefully shutdown the browser.</li>
</ul>
