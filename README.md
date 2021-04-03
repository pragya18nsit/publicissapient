# Publicis-Sapient-Creditcard-Spring-boot-data-H2-embedded

In this app, I used H2 in-memory database for demo purpose

**Application.properties**

```
spring.datasource.url=jdbc:h2:mem:TEST;DB_CLOSE_DELAY=-1;
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.platform=h2
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
```


**To Run without Docker**

```
> mvn clean install
> java -jar target/publicis-sapient-spring-h2-demo.jar
```

**To Run with Docker**
```
> mvn clean install
> docker build -t publicis-sapient-spring-h2-demo
> docker run -d -p 8080:8080 publicis-sapient-spring-h2-demo

> docker stop <image-name>
```

**Project Structure**

```
PublicisSapient
├── src
|   └── main
|		└── java
|		│	└── com.publicissapient.springboot.creditcard
|       │   │    └── controller
|       │   │    │      └── CreditCardController.java
| 		│	│	 └── exception
|		│	│	 │		└── CreditCardFailedLuhnException.java
|		│	│	 │		└── CreditCardNotFoundException.java
|		│	│	 │		└── CustomizedResponseEntityExceptionHandler.java
|		│	│	 │		└── ExceptionResponse.java
|       │   │    └── model
|       │   │    │    	└── CreditCard.java
|		│	│	 └── security
|		│	│	 │		└── ApplicationSecurityConfig.java
|		│	│	 └── service
|		│	│	 │		└── CreditCardService.java
|		│	│	 └── utils
|		│	│	 │		└── LuhnValidation.java
|		│	│	 └── CreditCardApp.java
|       │   │       
|		│   └── resources
|       └── test	
|			└── java
|				  └── com.publicissapient.springboot.creditcard
|					└── controller
|							└── CreditCardGetControllerTest.java
|							└── CreditCardPostControllerTest.java
|					└── integration
|							└── CreditCardGetControllerIntegrationTest.java
|							└── CreditCardPostControllerIntegrationTest.java
├── Dockerfile
│   
├── pom.xml


```
**Curl API Requests to add or get a credit card**
```

Add a credit card
==================

curl --location --request POST 'localhost:8080/creditcard' \
--header 'Authorization: Basic YWRtaW46bmltZGE=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=203A6545B1AFF97EFEFE5B7F3DB0BAB6' \
--data-raw '{
    "name": "Bib",
    "id": "1267136226300779925",
    "cvv": 123,
    "bankName": "axis",
    "balance": 123,
    "currency": "$",
    "cardType": "gold"
}'

Get a credit card
==================
curl --location --request GET 'localhost:8080/creditcard/1441424869721971993' \
--header 'Authorization: Basic YWRtaW46bmltZGE=' \
--header 'Cookie: JSESSIONID=203A6545B1AFF97EFEFE5B7F3DB0BAB6' \
--data-raw ''

```
**Model/Schema**
```

 TABLE credit_card
      
 1.  id BIGINT(19) NOT NULL,
 2.  name VARCHAR2(50) NOT NULL,
 3.  cvv NUMBER(5) NOT NULL,
 4.  issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 5.  expiry_date DATE,
 6.  bank_name VARCHAR(40)  NOT NULL,
 7.  card_type VARCHAR(20),
 8.  balance NUMBER(10) NOT NULL,
 9.  currency VARCHAR(1) NOT NULL,
