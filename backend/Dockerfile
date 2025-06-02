FROM openjdk:17-jdk-slim
WORKDIR /app

# Install netcat and Cassandra cqlsh
RUN apt-get update && \
    apt-get install -y netcat python3 python3-pip && \
    pip3 install cqlsh && \
    rm -rf /var/lib/apt/lists/*

COPY target/fileservice-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for.sh wait-for.sh

RUN chmod +x wait-for.sh

EXPOSE 8080

ENTRYPOINT ["bash", "./wait-for.sh", "cassandra", "9042", "java", "-jar", "app.jar"]
