FROM openjdk:17-alpine
VOLUME /tmp
COPY target/*.jar findfood-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/findfood-0.0.1-SNAPSHOT.jar"]
