FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/cocoman-*.jar
COPY ${JAR_FILE} cocoman-application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cocoman-application.jar"]