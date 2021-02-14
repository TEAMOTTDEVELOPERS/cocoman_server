FROM openjdk:11-jre-slim
COPY build/libs/cocoman-*.jar cocoman-application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cocoman-application.jar"]