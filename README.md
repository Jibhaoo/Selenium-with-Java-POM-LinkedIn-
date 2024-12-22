# Instructions for building and running tests on linux

1. Install java 21 and maven with the following commands

`sudo apt install openjdk-21-jdk`

`sudo apt install maven`

2. In the current directory, run `mvn clean install` to install dependencies

3. In the current directory, run `mvn clean test` to run UI tests

    * Note: which test(s) are run is determined by the testng.xml file in this directory

4. When tests finish, open `Reports/ExtentReport.html` in a browser to view results  