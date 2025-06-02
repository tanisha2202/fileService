#!/bin/bash

host="$1"
port="$2"

echo "Waiting for $host:$port..."

while ! nc -z "$host" "$port"; do
  echo "Waiting..."
  sleep 1
done

# Wait additional time for Cassandra to finish initializing
echo "Port is open, waiting 20 more seconds for Cassandra to finish startup..."
sleep 20

echo "$host:$port is available!"
exec "${@:3}"
