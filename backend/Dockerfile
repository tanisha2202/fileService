FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

# Install netcat and cqlsh
RUN apt-get update && \
    apt-get install -y netcat python3 python3-pip && \
    pip3 install cqlsh && \
    rm -rf /var/lib/apt/lists/*

# Copy built JAR from previous stage
COPY --from=build /app/target/fileservice-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for.sh"]
