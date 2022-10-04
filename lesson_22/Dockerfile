FROM maven:3.5.2-jdk-8 AS builder

ADD ./pom.xml pom.xml
RUN mvn dependency:resolve

ADD ./src src/
RUN mvn clean package

FROM tomcat:9.0.53-jdk8
COPY --from=builder target/Aliaksei.Fezhanka-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/servlet.war

EXPOSE 8080
