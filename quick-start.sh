#!/bin/bash

echo "⚡ Quick Start - Fully Automatic"

# Always use dynamic port (never conflicts)
export SPRING_PROFILES_ACTIVE=dev

# Start Elasticsearch if needed
if ! curl -s -k https://localhost:9200 > /dev/null 2>&1; then
    echo "🔍 Starting Elasticsearch..."
    elasticsearch &
    sleep 15
fi

# Check Kafka
if ! nc -z localhost 9092 2>/dev/null; then
    echo "⚠️  Please start Kafka: brew services start kafka"
    exit 1
fi

echo "🚀 Starting app on random port..."
SPRING_PROFILES_ACTIVE=dev ./mvn17.sh spring-boot:run 