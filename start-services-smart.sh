#!/bin/bash

echo "🚀 Starting all services..."

# Check if port 8080 is in use
echo "🔍 Checking if port 8080 is in use..."
PID=$(lsof -ti:8080)
if [ ! -z "$PID" ]; then
    echo "⚠️  Port 8080 is busy (PID: $PID)"
    echo "🛑 Killing process $PID automatically"
    kill $PID
    sleep 2
else
    echo "✅ Port 8080 is available!"
fi

# Start Elasticsearch if not running
echo "📦 Starting Elasticsearch..."
if ! curl -s -k https://localhost:9200 > /dev/null 2>&1; then
    echo "🔍 Elasticsearch not running, starting it..."
    elasticsearch &
    echo "⏳ Waiting for Elasticsearch to start..."
    sleep 15
else
    echo "✅ Elasticsearch already running!"
fi

# Start Kafka if not running
echo "📦 Starting Kafka..."
if ! nc -z localhost 9092 2>/dev/null; then
    echo "🔍 Kafka not running, starting it..."
    brew services start kafka
    echo "⏳ Waiting for Kafka to start..."
    
    # Wait up to 30 seconds for Kafka to be ready
    for i in {1..30}; do
        if nc -z localhost 9092 2>/dev/null; then
            echo "✅ Kafka started successfully!"
            break
        fi
        echo "⏳ Kafka starting... ($i/30)"
        sleep 1
    done
    
    # Final verification
    if ! nc -z localhost 9092 2>/dev/null; then
        echo "❌ Kafka failed to start within 30 seconds. Please check your setup."
        exit 1
    fi
else
    echo "✅ Kafka already running!"
fi

# Start Spring Boot application
echo "🌱 Starting Spring Boot application..."
if [ "$SPRING_PROFILES_ACTIVE" = "dev" ]; then
    echo "🔀 Using development profile with dynamic port"
    SPRING_PROFILES_ACTIVE=dev ./mvn17.sh spring-boot:run
else
    ./mvn17.sh spring-boot:run
fi 