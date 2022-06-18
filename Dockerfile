FROM maven:3.8.3-jdk-11 as mvn-dependency-resolver
WORKDIR app
COPY pom.xml ./
RUN mvn package -DskipTests

FROM mvn-dependency-resolver as mvn-builder
COPY src src/
RUN mvn package -DskipTests

FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR app
COPY --from=mvn-builder app/target target
ARG JAR_FILE=target/*.jar
RUN java -Djarmode=layertools -jar ${JAR_FILE} extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
