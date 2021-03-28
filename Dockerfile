FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/publicis-sapient-spring-h2-demo.jar publicis-sapient-spring-h2-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "publicis-sapient-spring-h2-demo.jar"]
