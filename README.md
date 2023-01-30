# Petstore API Testing Framework Example

## Framework overview:
The framework utilizes a parent pom.xml and a common 
framework which is then added as a dependency to each individual
test suite. Using a parent pom allows the infrastructure team
the ability to properly maintain all dependency versions
and therefore avoid conflicts between projects. This 
and the use of a common framework as a JAR and Maven dependency
allows separate automation testers to manage their own
repository while still being able to use common methods for test execution.

Ideally the parent pom, the test suite and the framework would all be
on separate repository, but for simplicity's sake, I have placed
them in a common root folder. Ideally all repositories will have their jars published to
a repository manager like Artifactory.

## How to build and run the project
The project contains 3 separate pom.xml files. In order
to use the necessary third party libraries, first install
the parent pom (mvn install) located in the parent folder.
Once done, install the pom from petstore-framework directory in order to 
add the common framework jar to your local Maven reponsitory.
Once done, you can run the tests in the petstore-tests project directory as usual. 