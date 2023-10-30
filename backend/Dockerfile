FROM maven:3-amazoncorretto-17
WORKDIR /pizzanu
EXPOSE 8080
COPY . .
HEALTHCHECK --interval=10s --start-period=60s CMD curl --fail http://localhost:8080/actuator/health || exit 1
CMD mvn spring-boot:run -B
