#!/bin/bash

echo "🚀 Starting all services..."

# Check if port 8080 is in use and kill it
echo "🔍 Checking if port 8080 is in use..."
PID=$(lsof -ti:8080)
if [ ! -z "$PID" ]; then
    echo "🛑 Killing existing process on port 8080 (PID: $PID)"
    kill $PID
    sleep 2
fi

# Start Elasticsearch if not running
echo "📦 Starting Elasticsearch..."
if ! curl -s http://localhost:9200 > /dev/null 2>&1; then
    echo "🔍 Elasticsearch not running, starting it..."
    elasticsearch &
    ELASTICSEARCH_PID=$!
    echo "⏳ Waiting for Elasticsearch to start..."
    sleep 15
else
    echo "✅ Elasticsearch already running!"
fi

# Check if Kafka is running
echo "📦 Checking Kafka..."
if ! nc -z localhost 9092 2>/dev/null; then
    echo "🔍 Kafka not running, please start it manually:"
    echo "   brew services start kafka"
    exit 1
else
    echo "✅ Kafka is running!"
fi

# Verify services are ready
echo "🔍 Checking service health..."
curl -f http://localhost:9200/_cluster/health?wait_for_status=yellow&timeout=60s > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "✅ Elasticsearch is ready!"
else
    echo "❌ Elasticsearch failed to start"
    exit 1
fi

# Start Spring Boot application
echo "🌱 Starting Spring Boot application..."
./mvn17.sh spring-boot:run 