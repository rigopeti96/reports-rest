FROM amazoncorretto:11-alpine-jdk
COPY target/report_rest_springdata.jar report_rest_springdata.jar
ENTRYPOINT ["java","-jar","/report_rest_springdata.jar"]