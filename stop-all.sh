#!/bin/bash

echo "🧹 Stopping ALL services and freeing ALL ports..."

# Stop Spring Boot application
echo "🌱 Stopping Spring Boot application..."
pkill -f "spring-boot:run"

# Stop Elasticsearch
echo "📦 Stopping Elasticsearch..."
pkill -f elasticsearch

# Stop Kafka and Zookeeper (Homebrew services)
echo "🍺 Stopping Kafka services..."
brew services stop kafka
brew services stop zookeeper

# Show freed ports
echo "🔍 Checking freed ports..."
echo "Port 8080 (Spring Boot): $(lsof -ti:8080 || echo 'FREE ✅')"
echo "Port 9092 (Kafka): $(lsof -ti:9092 || echo 'FREE ✅')"
echo "Port 9200 (Elasticsearch): $(lsof -ti:9200 || echo 'FREE ✅')"
echo "Port 2181 (Zookeeper): $(lsof -ti:2181 || echo 'FREE ✅')"

echo "✅ All services stopped - All ports freed!" 