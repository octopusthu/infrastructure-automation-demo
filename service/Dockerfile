FROM maven:3.6-jdk-8-alpine AS MAVEN
COPY pom.xml /tmp/
WORKDIR /tmp/
# Use separate layers so as to cache downloaded maven repo artifacts
RUN mvn verify clean --fail-never

COPY src /tmp/src/
RUN mvn clean package

FROM tomcat:9-jre8-alpine
COPY --from=MAVEN /tmp/target/infrastructure-automation-demo-service.war /usr/local/tomcat/webapps/infrastructure-automation-demo.war
