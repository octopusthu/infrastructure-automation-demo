FROM maven:3.6-jdk-8-alpine AS MAVEN
# Use mirror to accelerate artifacts downloading
COPY maven-settings.xml /root/.m2/settings.xml
COPY pom.xml /tmp/
WORKDIR /tmp/
# Use separate layers to cache downloaded artifacts
RUN mvn verify clean --fail-never

COPY src /tmp/src/
RUN mvn clean package

FROM tomcat:9-jre8-alpine
COPY --from=MAVEN /tmp/target/infrastructure-automation-demo-service.war /usr/local/tomcat/webapps/infrastructure-automation-demo.war
