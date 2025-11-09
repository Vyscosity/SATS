#!/bin/bash

echo "Compiling Student Attendance Tracking System..."
echo "Note: This compiles the empty skeleton classes in src/"
echo ""

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files from src/
javac -d bin -encoding UTF-8 src/dataStructures/*.java src/models/*.java src/services/*.java src/ui/*.java src/main/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "Compilation successful!"
    echo ""
    echo "Note: The classes in src/ are currently empty skeleton classes."
    echo "We need to implement the TODO items in each class."
    echo ""
    echo "To run the examples (complete implementation), use:"
    echo "  ./examples.sh"
    echo ""
    echo "To run our implementation after we finish it, use:"
    echo "  java -cp bin main.SATS"
else
    echo ""
    echo "Compilation failed!"
    echo ""
    echo "This is expected if the classes aren't implemented yet."
    echo "We should implement the TODO items in each class."
    exit 1
fi
