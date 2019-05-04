# AppiumSampleSauceTest
This is a sample test project

# Prerequisites
- Java v1.8
- Maven v3.5.3

# Structure

- Folder config contains base configuration files
    1. File- 'config.properties' contains cloud provider and web driver config details
    2. File- 'sauce_environment.json' contains execution environment and desired capabilities
- Folder 'testng' contains testng files
- Folder 'log' contains custom driver execution log
- Folder 'report' contains simple testng report
- Folder 'target' contains simple maven surefire reports

- Package 'com.sl.util' contains Utility classes
- Package 'com.sl.test' contains the test scripts
- Package 'com.sl.structure' contains the base driver class
- Package 'com.sl.listner' contains testng listner class
- Package 'com.sl.global' contains global classes

# Configuration

1. Add 'SAUCE_USER' and 'SAUCE_KEY' system environment
2. If step 1 cannot be completed update Line 3, 4 in the 'config.properties' file
