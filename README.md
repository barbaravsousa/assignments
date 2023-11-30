# Java Application 

### Context 
I was asked to create a Java application capable of ingesting data from a CSV file into a database using a REST API.
- The CSV file expected headers are:
        - Customer Ref 
        - Customer Name 
        - Address Line 1 
        - Address Line 2 
        - Town 
        - County 
        - Country 
        - Postcode
- The REST API should have both a GET and a POST endpoints. The first to retrieve the customer information, passing in a customer ref;
The second to insert a new Customer entity. The REST API should use JSON format.
- The database can have a single table that matches the CSV headers.

### About the project
This repository contains the codebase for a java application that serves the REST API required.
Even though the application complexity does not require a use of such versatile framework, I still opted to use *Spring Boot* because
it makes it easier for me to showcase qualities that I believe are relevant for the vacancy I'm applying to.
After deciding to use *Spring Boot*, the *H2* database was a logic choice, since it requires minimal configuration, can run as
an in-memory database, taking away the need to install or setup something on my local machine, and it integrates very easily with
Spring Data JPA, reducing the boilerplate code.

 #### Layered Architecture
I followed a layered architecture style, because in addition to being the most common architecture pattern for Java applications, it's also the one 
I'm most comfortable with. 
This strategy consists in having different layers with different responsibilities (to promote the separation of concerns)
I followed a standard layered architecture format with the following closed (layers can only interact with their immediate neighbours)
layers: 
- Presentation Layer (CustomerController) - Responsible to 'present' the REST API.
- Business Layer (CustomerService) - Responsible to apply the 'business' logic related with the creation and retrieval of Customers
- Persistence Layer (Repositories) - Responsible to interact with the database
- Database Layer - Stores all the information

 #### Database 
Given the information about the customer, I decided to create two entities, Customer and Location.
Assuming one customer only has one address, so one postcode, but one postcode can be the same for multiple customers, creating a Many-to-One relationship between the entities.
Although there are no validations to support this idea, I created the Location entity based on the fact that the postcode is a unique identifier and through it, 
it's possible to obtain the town, the county and the country, avoiding the repetition of information in the Location table, this is an example of database normalization.  

Since there was a need to search the database for a costumer based on the customerRef, I decided to mark this property as a unique identifier and also create an index, in order 
to make the search quicker. I applied the same logic to the postcode since before saving a new customer I first check if the postcode already exists. 

#### How to run it locally 
1) Start by running the following commands: 
```
mvn clean compile
mvn spring-boot:run
```
2) You can check the [H2 console](http://localhost:8081/h2-console), make sure the URl is "jdbc:h2:file:/data/demo" and the password is the same as the one in the application.properties file. 
You can check both tables, Customer and Location, to confirm they are populated. 
3) To test the GET request, you can use the following Curl command: 
```
curl -X GET "http://localhost:8081/customers/findCustomer?customerRef=XXX" -i
```
- There are no validations in place for the customerRef property, so the curl command can be used as it is, 
since the 'XXX' does not exist in the database, the request will return '404'. If the customerRef is replaced by an existent 
value from the database then the request will return '200' and the customer information. 

***Running the tests***

To run the test classes, use the following command: 
```
mvn test
```

