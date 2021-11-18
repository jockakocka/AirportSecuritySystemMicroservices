# README file for Assignment1 Cloud Computing

This README file contains information about how to build the services and run them in Docker. The project includes eight different microservices, from which three of them are built in JavaSpringBoot. 

## JAVA .jar building 

In order to execute the docker-compose.yml file, the first step would be to create the executable .jar files for the services written in JavaSpriingBoot. The first step would be to clone the Git repository to your machine. In order to that a step by step guide is explained for the three services:

### Human-Detection Service .jar

- After cloning of the Git repository, navigate to a11946909->Services->human-detection folder. 
- When reaching this destination, two Maven commands need to be executed in the terminal: 
    - mvn clean - To clean the project
    - mvn package - To create the .jar file.
- After the commands are executed and finished, navigate to human-detection->target folder. The executable .jar should be there under the following name: human-detection-0.0.1-SNAPSHOT.jar

### Collector Service .jar

- After cloning of the Git repository, navigate to a11946909->Services->collector-microservice folder. 
- When reaching this destination, two Maven commands need to be executed in the terminal: 
    - mvn clean - To clean the project
    - mvn package - To create the .jar file.
- After the commands are executed and finished, navigate to collector-microservice->target folder. The executable .jar should be there under the following name: collector-microservice-0.0.1-SNAPSHOT.jar

### CPanel Service .jar

- After cloning of the Git repository, navigate to a11946909->Services->cpanel-service folder. 
- When reaching this destination, two Maven commands need to be executed in the terminal: 
    - mvn clean - To clean the project
    - mvn package - To create the .jar file.
- After the commands are executed and finished, navigate to cpanel-service->target folder. The executable .jar should be there under the following name: cpanel-service-0.0.1-SNAPSHOT.jar

## Docker images and containers

After the executable .jar files are created, the images and the containers in Docker can be built and then started. In order to do this, follow the following steps:
- Navigate to a11946909->Services destination. 
- docker-compose build --no-cache - To build the images
- docker-compose up - To run the containers
- Check in terminal whether the containers for the services work



