#!/bin/bash

echo "🛑 Stopping all services..."

# Stop Spring Boot application
echo "🌱 Stopping Spring Boot application..."
pkill -f "spring-boot:run"

# Stop Elasticsearch
echo "📦 Stopping Elasticsearch..."
pkill -f elasticsearch

# Note: Kafka is managed by brew services, so we leave it running
echo "ℹ️  Kafka is managed by brew services and left running"
echo "   To stop Kafka: brew services stop kafka"

echo "✅ Services stopped!" 