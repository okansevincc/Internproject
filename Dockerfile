FROM openjdk:17
WORKDIR /app
COPY target/canteenApp-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","canteenApp-0.0.1-SNAPSHOT.jar"]