The program should now be object oriented in order to comply with the completion notice.
As far as i've tested, everything should be working and comply with the passing grade requirements.

The test cases are testable through Gradle with ./gradlew test
(I have tested cloning the repo and ran the tests and the program).

I'm aware that the association relationships from the main method isn't really correct usage.
I particularly wanted to have a relation to the ConsoleUI class as it's only used in the main method.





--Old Readme Information--
Some information regarding my assignment.

I basically rewrote the entire assignment, the Scanner object is passed as an argument to the methods and is closed properly.
Made the program more object oriented.
I made the code base less messy.
Added some simple error handling and printouts.
Main method is less cluttered.
Updated the class diagram(design).
Added a more relevant test case.

The methods implementing the interface could possibly be separated into own classes.

The program is runable from the recipeApp path.

--Old Readme Information--
Using multiple Scanner objects wasnt a good idea because closing one closes the inputstream. I realized this way to far in the assignment hence why the scanners are not closed. The singleton pattern could probably be a good use case here.

There's alot of string splitting and string concatenation in my application which i realized way too late aswell. A solution could be to parse the file into private fields early on regardless of method call. Would make it easier for the methods to handle the data and the application less error prone.
Also using JSON as file format would make things easier.

Source path:
recipeApp/app/src/main/java/recipeApp

Gradle test path:
recipeApp/app/src/test/java/recipeApp/AppTest.java

