#!/bin/bash

echo "Compiling Student Attendance Tracking System..."
mkdir -p bin
javac -d bin -encoding UTF-8 src/dataStructures/*.java src/models/*.java src/services/*.java src/ui/*.java src/main/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "To run the application, use:"
    echo "java -cp bin main.SATS"
else
    echo "Compilation failed!"
fi

