FROM eclipse-temurin:17-jdk-alpine
WORKDIR /home/app
EXPOSE 8080

COPY build/libs/*.jar ./spring-boot-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/spring-boot-application.jar"]