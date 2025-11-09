# Quick Start Guide

## Compilation and Execution

### Windows
```bash
# Compile
compile.bat

# Run
run.bat
```

### Linux/Mac
```bash
# Make scripts executable (first time only)
chmod +x compile.sh run.sh

# Compile
./compile.sh

# Run
./run.sh
```

### Manual Compilation
```bash
# Create bin directory
mkdir bin

# Compile all Java files
javac -d bin -encoding UTF-8 src/dataStructures/*.java src/models/*.java src/services/*.java src/ui/*.java src/main/*.java

# Run the application
java -cp bin main.SATS
```

## Quick Test Workflow

1. **Add Students**
   - Main Menu → 1 (Student Management) → 1 (Add Student)
   - Example: ID: "S001", Name: "John Doe", Class: "CS101", Year: 2024

2. **Mark Attendance**
   - Main Menu → 2 (Attendance Recording) → 1 (Mark Attendance)
   - Enter student ID, date (yyyy-MM-dd), and status (1=Present, 2=Absent, 3=Late)

3. **Generate Report**
   - Main Menu → 4 (Reports) → 1 (Student Attendance Report)
   - Enter student ID and date range

4. **View Alerts**
   - Main Menu → 5 (Analytics) → 2 (Generate Alerts for Today)
   - Main Menu → 4 (Reports) → 3 (View Alerts)

## Features Overview

### Student Management
- Add, edit, remove students
- Display students alphabetically or by class
- Fast lookup using hash tables

### Attendance Recording
- Mark daily attendance
- Track absent and late students
- Undo functionality using stack

### Search and Sort
- Search by ID (hash table) or name (BST)
- Sort by name, class, or enrollment year

### Reports and Analytics
- Student attendance reports
- Class attendance reports
- Trend analysis
- Priority queue alerts

## Data Structures Demonstrated

- **LinkedList**: Student lists, attendance records
- **Stack**: Undo functionality
- **Queue**: FIFO attendance processing
- **BinarySearchTree**: Name-based search
- **AVLTree**: Self-balancing student management
- **PriorityQueue**: Alert system
- **HashTable**: Fast ID lookups

