# Architecture of Framework 
![Untitled Diagram drawio-2](https://github.com/user-attachments/assets/7f8cbb60-e925-4140-9b62-739a2773fcc0)

# Workflow 
->Execution starts with the maven command , We check in the command which profile is requested to be executed <br/>
->The requested xml file is triggered which contains all the information of what test to execute <br/>
->When executing the test cases it takes locators from page object classes and if page object needs any utility code it takes it from the utility classes through inheritence <br/>
->The configurations required to execute the test cases are set by the base class which is also inherited by the test class <br/>
->If data is needed the data is taken from data provider ( jsonTo HashMap) the conversion is done in Base test class <br/>
->While test cases are being executed everything is being monitored by the listeners <br/>
->The listeners inturn report everything to the extent Reports. 

