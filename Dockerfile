FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} report_rest_springdata.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/report_rest_springdata.jar"]