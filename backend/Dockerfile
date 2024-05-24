FROM openjdk:17-ea-oracle

WORKDIR /data/www

ARG JAR_FILE=build/libs/*.jar
ARG APP_VERSION
ENV APP_VERSION=${APP_VERSION}

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]