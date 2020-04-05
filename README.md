
# Unit Testing with JUnit 5 and Kotlin  

This project is based on [Unit Testing with JUnit 5 and Kotlin by Kevin Jones](https://app.pluralsight.com/library/courses/kotlin-junit5-unit-testing/).
  
JUnit 5 is the de-facto standard testing framework on the JVM. It is used to test application code in many areas   
including enterprise, desktop, and mobile applications.   
   
 In this course, Unit Testing with **JUnit 5** and Kotlin, you will learn how to set up JUnit to test Kotlin code,   
 including some of the issues you will encounter that are unique to Kotlin.  
  
First, you will see how to set up your environment to use JUnit 5 for Kotlin including setting up Gradle and Maven   
environments and using an IDE to run the tests. Next, you will be introduced to Kluent, a Kotlin fluent assertions library.   
  
Then, you will see how to change assertions using this fluent syntax, as well as new, cleaner ways of writing   
assertions using the **Kluent APIs**.   
  
Finally, you will explore ‘mocking’ using **MockK**, a mocking framework written in and for Kotlin. When you are finished   
with this course, you will have the skills to test Kotlin code using JUnit and how to refactor code to make it testable.

[Unit Testing with JUnit 5 and Kotlin by Kevin Jones](https://app.pluralsight.com/library/courses/kotlin-junit5-unit-testing/)

## Extra Content
On top of course material, I have added [AssertJ](https://assertj.github.io/doc/) and [AssertK](https://github.com/willowtreeapps/assertk) 
libraries and added extra tests using this two so you can see the differences.

## Stack
- [Kotlin](https://kotlinlang.org/) 1.3.70
- [JUnit 5](https://junit.org/junit5/) 5.6.1
- [MockK](https://mockk.io/) 1.9.3
- [Kluent](https://github.com/MarkusAmshove/Kluent) 1.6
- [AssertJ](https://assertj.github.io/doc/) 0.22
- [AssertK](https://github.com/willowtreeapps/assertk) 3.15.0

## Instructions
Just run `/gradlew clean build` and you will see the outcome of the build and how all tests are passing.