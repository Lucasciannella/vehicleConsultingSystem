FROM openjdk:17-slim

RUN mkdir /opt/vehicle

WORKDIR /opt/vehicle

COPY target/vehicle-system-0.0.1-SNAPSHOT.jar  /opt/vehicle/vehicle-system-0.0.1-SNAPSHOT.jar

RUN chmod +x vehicle-system-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "vehicle-system-0.0.1-SNAPSHOT.jar"]