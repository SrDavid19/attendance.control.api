FROM alpine/java:17-jdk

WORKDIR /attendance

COPY target/project.attendance.control.api-0.0.1-SNAPSHOT.jar attendance.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "attendance.jar"]