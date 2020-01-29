# apiautomation
Its an maven project to test scanerio for stackoverflow badge ids, tags and recepient api.

To run the test cases please go through the below details-
Preqesites-

1. Java 1.8 or higher should be installed.
2. Maven Apache Maven 3.6.0 or higher should be installed.

Steps to run-

1. Checkout this repository.
2. Go to the workspace
3. Open projectConfig.properties(can be found in project root folder)
4. please enter access key generated from stackOverflow oauth. There is already default test access key provided in the config file.
5. Open myAssesmentTest.xml(can be found in project root folder).
6. Run maven command to execute the test cases->
      mvn test -DxmlPath='apiTest.xml'
