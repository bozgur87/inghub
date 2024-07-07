# Ing Hub Stock 

This repository contains a simple example of a code challenge with Java.

### Table of Contents  
[Technologies](#technologies)<br>
[Dependencies](#dependencies)<br>
[Pre requirements](#requirements)<br>
[Download](#download)<br>
[Run Project](#installing)<br>
[Usage](#usage)

<a name="technologies"/></a>
## Technologies
  * Java 21
  * Maven
  * Docker
  * H2 Database
  
<a name="dependencies"/></a>
## Dependencies
  * Lombok
  * Slf4j
  * Mapstruct

<a name="requirements"/></a>
## Pre requirements
  * OpenJDK 21.0.2 version
  * Docker
  * Maven
  * git (optional)

<a name="download"/></a>
## Download
  * First of all you need to clone this project. For this, if you have installed git then you can run below code snippet in bash, powershall or terminal.<br>
      `git clone https://github.com/bozgur87/inghub.git`<br>
    or you can download from github.com project page as a `.zip` file directly and extract.


<a name="installing"/></a>
## Installing
  * To build project, before go into project folder (stock-module/stock-management-contract) via bash, powershall or terminal and run above code snippet.<br>
    `mvn clean install`<br>
    If everything goes well you will see the last `BUILD SUCCESS` message.

<a name="run"/></a>
## Run Project
  * To run project run above code snippet. After this process, Producer and consumer microservices will start to accept requests over ports 8181 and 8282 with the embedded tomcat server.<br>
    `java -jar ./target/stock-management-service-0.0.1-SNAPSHOT.jar.jar`<br>
    <br>
  * You can also use Docker. <br>
    go into stock management service ms project folder and run: `docker build --tag=stock-management-service:latest .`<br>
    						`docker run -p 8181:8181 stock-management-service:latest`<br>


<a name="using"/></a>
## Using
  * To make a request for this project, you can use the Postman collection in the project