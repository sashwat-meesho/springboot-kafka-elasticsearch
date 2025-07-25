#!/bin/bash

# Exit on any error
set -e

echo "🏗️  Jenkins Build Script - Safe for CI/CD"
echo "================================================"

# Set build profile
export SPRING_PROFILES_ACTIVE=test

echo "🧹 Cleaning previous builds..."
./mvn17.sh clean

echo "🔧 Compiling the project..."
./mvn17.sh compile

echo "🧪 Running tests..."
if ./mvn17.sh test; then
    echo "✅ All tests passed!"
else
    echo "❌ Tests failed! Build cannot continue."
    exit 1
fi

echo "📦 Packaging the application..."
./mvn17.sh package -DskipTests

echo "✅ Build completed successfully!"
echo "📁 Generated JAR: target/*.jar"

echo "🎉 Jenkins build finished successfully!" 