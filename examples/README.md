# Student Attendance Tracking System (SATS)

A comprehensive Java console-based system for managing student records, tracking daily attendance, generating reports, and analyzing attendance patterns. This project demonstrates the implementation and use of core and advanced data structures and algorithms.

## Features

### Core Features

#### 1. Student Management
- Add, edit, and remove student records (ID, name, class, enrollment year)
- Display student lists alphabetically or by class
- Fast lookup using hash tables

#### 2. Attendance Recording
- Mark daily attendance using lists and queues
- Track absent and late students
- Undo recent attendance edits with a stack

#### 3. Searching and Sorting
- Search students by ID or name efficiently using binary search trees
- Sort students and reports by name, class, or date
- Binary search tree implementation for fast searches

#### 4. Data Storage and Access
- Hash tables/maps for fast student lookups
- Sets for unique class lists
- Self-balancing AVL trees for scalable student data management

#### 5. Reporting & Analytics
- Generate attendance reports per student or class
- Analyze trends using historical attendance data
- Recursion for report generation
- Priority queues/heaps for alerts on late or absent students

#### 6. User Interface
- Console-based menu system
- Display attendance summaries, search results, and alerts

## Project Structure

This project is organized into two main areas:

### Our Implementation Area (`src/`)
**Empty skeleton classes** that we need to implement:
```
src/
├── dataStructures/      # Core data structure implementations (TODO)
│   ├── LinkedList.java
│   ├── Stack.java
│   ├── Queue.java
│   ├── BinarySearchTree.java
│   ├── AVLTree.java
│   ├── PriorityQueue.java
│   └── HashTable.java
├── models/              # Domain models (TODO)
│   ├── Student.java
│   ├── AttendanceRecord.java
│   ├── AttendanceAlert.java
│   └── UndoAction.java
├── services/            # Business logic (TODO)
│   ├── StudentService.java
│   ├── AttendanceService.java
│   └── ReportingService.java
├── ui/                  # User interface (TODO)
│   └── ConsoleUI.java
└── main/                # Entry point (TODO)
    └── SATS.java
```

### Reference Examples (`examples/`)
**Complete working implementations** for reference:
- `examples/src/` - Full working source code
- `examples/reports/` - Example report templates with detailed placeholders

### Reports (`reports/`)
**Empty report templates** that we need to fill in:
- `TEAM_REPORT.md` - Team report template
- `STUDENT1_CONTRIBUTION.md` through `STUDENT4_CONTRIBUTION.md` - Individual contribution templates

## Data Structures Used

### Core Data Structures
- **LinkedList**: Used for managing student records and attendance lists
- **Stack**: Used for undo functionality in attendance tracking
- **Queue**: Used for managing attendance records in FIFO order

### Advanced Data Structures
- **BinarySearchTree**: Used for efficient searching by ID or name
- **AVLTree**: Self-balancing tree for scalable student data management
- **PriorityQueue (Min Heap)**: Used for alerts on late or absent students
- **HashTable**: Fast O(1) lookup for students by ID

## Algorithms Implemented

1. **Searching**: Binary search tree traversal
2. **Sorting**: Multiple sorting algorithms (by name, class, enrollment year)
3. **Recursion**: Used in report generation and tree traversals
4. **Tree Balancing**: AVL tree rotations for self-balancing

## How to Compile and Run

### Prerequisites
- Java JDK 8 or higher
- Command line terminal (bash for Linux/Mac, or WSL/Git Bash for Windows)

### Quick Start

#### 1. Run the Complete Examples (Reference Implementation)

To see the fully working system:

```bash
./examples.sh
```

This will:
- Compile the complete working implementation from `examples/src/`
- Run the Student Attendance Tracking System

#### 2. Compile Our Implementation

To compile our implementation in `src/` (currently empty skeleton classes):

```bash
./compile.sh
```

**Note:** The classes in `src/` are currently empty skeleton classes with `TODO` comments. We need to implement all the methods marked with `// TODO: Implement...`

#### 3. Run Our Implementation

After we implement the classes in `src/` and compile:

```bash
java -cp bin main.SATS
```

### Manual Compilation

If you prefer to compile manually:

**For examples (complete implementation):**
```bash
javac -d bin -encoding UTF-8 examples/src/dataStructures/*.java examples/src/models/*.java examples/src/services/*.java examples/src/ui/*.java examples/src/main/*.java
java -cp bin main.SATS
```

**For our implementation (empty skeleton classes):**
```bash
javac -d bin -encoding UTF-8 src/dataStructures/*.java src/models/*.java src/services/*.java src/ui/*.java src/main/*.java
java -cp bin main.SATS
```

## Usage Guide

### Main Menu Options

1. **Student Management**: Add, edit, remove, and display students
2. **Attendance Recording**: Mark attendance, view records, undo actions
3. **Search and Sort**: Search students and sort by various criteria
4. **Reports**: Generate student and class attendance reports
5. **Analytics**: Analyze attendance trends and generate alerts
6. **View Summary**: Display system statistics

### Example Workflow

1. Add students using Student Management
2. Mark daily attendance for a class
3. Generate reports to view attendance statistics
4. Use analytics to identify trends
5. Generate alerts for absent or late students

## Learning Objectives Demonstrated

- Implementation of fundamental Java data structures
- Application of algorithms for searching, sorting, and data analysis
- Object-oriented design principles
- Modular programming and integration of multiple modules
- Recursive algorithm implementation
- Self-balancing tree structures
- Priority queue implementation

## Team Structure (Reference)

- **Student 1**: Core Data Structures & Linked Lists
- **Student 2**: Stack, Queue, Recursion, Sorting
- **Student 3**: Trees, Heaps, Advanced Structures
- **Student 4**: Hash Tables, Sets, Maps, UI, Integration

## Project Organization

### How We're Organizing This

1. **Implementation**: We're working in the `src/` folder. Each class has empty skeleton code with `TODO` comments showing what we need to implement.

2. **Reference**: We can check `examples/src/` for complete working implementations if we need guidance.

3. **Reports**: We need to fill in the empty templates in `reports/` folder. See `examples/reports/` for detailed example templates with placeholders.

### File Structure Summary

- **`src/`** - Empty skeleton classes (we implement here)
- **`examples/src/`** - Complete working implementation (reference only)
- **`reports/`** - Empty report templates (we fill in)
- **`examples/reports/`** - Example report templates with placeholders (reference)

## Notes

- All data structures are custom implementations (except Java's built-in Set interface for class management)
- The system uses in-memory storage (data is lost when the program exits)
- Date format: yyyy-MM-dd (e.g., 2024-01-15)
- The `src/` folder contains empty skeleton classes that we need to implement
- The `examples/` folder contains complete working code for reference only

## License

This project is created for educational purposes as part of a Data Structures course.

