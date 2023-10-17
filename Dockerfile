FROM amazoncorretto:19.0.2-alpine3.16
RUN apk add openssl=1.1.1v-r0
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springboot-firestore-configuration.jar
COPY serviceAccountKey.json /serviceAccountKey.json
ENTRYPOINT ["java","-jar","/springboot-firestore-configuration.jar"]