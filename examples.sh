#!/bin/bash

echo "Building and running examples from examples/src/..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile examples
echo "Compiling examples..."
javac -d bin -encoding UTF-8 examples/src/dataStructures/*.java examples/src/models/*.java examples/src/services/*.java examples/src/ui/*.java examples/src/main/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Running Student Attendance Tracking System (SATS)..."
    echo ""
    java -cp bin main.SATS
else
    echo "Compilation failed!"
    exit 1
fi

