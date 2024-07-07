FROM openjdk:21
ADD ./target/stock-management-service-0.0.1-SNAPSHOT.jar stock-management-service.jar
EXPOSE 8181
ENTRYPOINT ["java","-Xmx2048m", "-jar","stock-management-service.jar"]
