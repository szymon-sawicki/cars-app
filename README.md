# cars-app

Simple web app with rest api for manage collection of cars.  
Cars can be loaded from file through constructor in CarsService class. Format of input file must be JSON conform.   
REST API have only get methods to display collection of cars in json format (see javadoc)

### Installation and running 

- Project build:

mvn clean install

- Running jar archive 

java -jar --enable-preview target/app.jar

Docker container can be build with command:

docker-compose up -d --build (doesn't work at the moment - issue is on todo list, problem with pom.xml)


### Technology stack:

- Java 16  
- Spark Framework  
- JUnit 5  
- AssertJ  
- Lombok  
- Gson  

Build tool: Maven


### TODO

- test docker image
- make api documentation in Postman






