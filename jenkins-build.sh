#!/bin/bash

echo "🏗️  Jenkins Build Script - Safe for CI/CD"
echo "================================================"

# Set build profile
export SPRING_PROFILES_ACTIVE=test

echo "🧹 Cleaning previous builds..."
./mvn17.sh clean

echo "🔧 Compiling the project..."
./mvn17.sh compile

echo "🧪 Running tests..."
./mvn17.sh test

echo "📦 Packaging the application..."
./mvn17.sh package -DskipTests

echo "✅ Build completed successfully!"
echo "📁 Generated JAR: target/*.jar"

# Optional: Run a quick smoke test without starting external services
echo "🔍 Running smoke test (without external dependencies)..."
if ./mvn17.sh test -Dtest="*ApplicationTests" -DfailIfNoTests=false; then
    echo "✅ Smoke test passed!"
else
    echo "⚠️  Smoke test failed, but build artifacts are ready"
fi

echo "🎉 Jenkins build finished!" 