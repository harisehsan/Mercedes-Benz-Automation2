How to execution the Automation Test:

There are total 3 different ways to execute the project you can choose any one of them to run the test but first 2 are recommended: 

Method1: Execution of the “runTest.bat” file 

- In Windows, double click on the “runTestChrome.bat” or “runTestEdge.bat”

Method 2: Execution by running the maven command directly in the command prompt

•	Open Windows Command prompt or terminal:

•	Go to directory where project is cloned or downloaded.

•	Type the command: mvn clean test -Dcucumber=" --tags @mercedesAutomation" allure:serve (for chrome browser execution)

•	mvn clean test -Denv="edge"  -Dcucumber=" --tags @mercedesAutomation" allure:serve (For edge browser execution)


Method 3: Execution by Running the RunTest.java file 

•	Install IntelliJ or any other java IDE Open project in IDE

•	Go to the file in the directory

<project repository>\src\test\java\runConfig

•	Right click on the “RunTest.java” file select run 

•	In that case no report will be generated.
