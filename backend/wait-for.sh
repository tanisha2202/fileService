#!/bin/bash

host="cassandra"
port="9042"
datacentre="datacenter1"

echo "Waiting for $host:$port to be ready..."

# Wait for TCP port to open
while ! nc -z "$host" "$port"; do
  echo "Waiting for $host:$port..."
  sleep 2
done

echo "TCP port $port is open. Checking Cassandra readiness..."

# Wait for Cassandra to accept CQL commands
until cqlsh "$host" "$port" -e "describe keyspaces"; do
  echo "Cassandra not ready yet. Retrying..."
  sleep 3
done

echo "Cassandra is ready. Starting Spring Boot app..."

exec java -jar app.jar --spring.data.cassandra.contact-points=$host ----spring.data.cassandra.local-datacenter=$datacentre --spring.data.cassandra.schema-action=create_if_not_exists

