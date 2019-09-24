USSD BANKING APPLICATION

Technology Stack:
- Java
- Spring boot
- MySql
- Apache Kafka / Apache Zookeeper
- Redis Cache
- Docker
- Maven

Functionality:
- This application enable bank customer to open account
- Deposit money into account
- Do withdrawal
- Check account balance

Deployment:
This application can be deployed on docker container or on the machine itself.

- Deployment via docker:
 - Build the application > mvn clean install
 - run > docker build -t ussd-application .
 - run > docker-compose up -d
 - Then go to browser and access the application on port 2222
 
- Deployment of machine:
 - Build the application > mvn clean install
 - run > java -jar ussd-application.jar
 - Then go to browser and access the application on port 2222

Note: The doc  

    
