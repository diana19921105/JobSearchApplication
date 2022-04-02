Spring Boot application for searching jobs

##Requirements
- JDK 11.0.14
- Maven 3.8.5
- JUnit4

##Build and run the application locally
- build the project using mvn clean install command
- execute the main method in the com.dianaszanto.jobsearchapi.JobsearchApiApplication class
- alternatively you can run the project using mvn spring-boot:run command
- the application is accessible via localhost:8080
- Authorization header : x-api-key

##Run tests
- to run all tests in the project run JobSearchApiTests

##Environment variables
|Name                | Value                        |
|--------------------|------------------------------|
|DATASOURCE_URL	     |{your database name}          |
|DATASOURCE_USERNAME |{your local mysql username}   |
|DATASOURCE_PASSWORD |{your local mysql password}   |
|HIBERNATE_DIALECT   |org.hibernate.dialect.MySQL8Dialect |
|API_KEY             |e7e237d7cc7c88f16f42d5c8e1e1d936|
|API_ID              |8f762315|

        






