# Student Attendance Tracking System (SATS)

I set this up with a working example so we can see how everything might work together.

## Setup

### Option 1: Eclipse/IntelliJ
- Just import the project as a Java project
- Make sure you have Java JDK 8+ installed
- The examples are in `examples/src/` if you want to reference them

### Option 2: Command Line
- Make sure you have Java JDK installed (download from Oracle/OpenJDK if needed)
- On Linux/Mac: `chmod +x examples.sh compile.sh` (first time only)
- Run `./examples.sh` to see the complete working version
- Run `./compile.sh` to compile our implementation (currently empty skeleton classes)

## What We Need to Do

1. **Decide who's doing what** - Check out the team structure below and let's divide up the work
2. **Implement our parts** - Work in the `src/` folder (empty skeleton classes with TODO comments)
3. **Fill in reports** - Use the templates in `reports/` folder

## Team Structure

- **Student 1**: Core Data Structures & Linked Lists (Lessons 3–4)
  - LinkedList implementation
  
- **Student 2**: Stack, Queue, Recursion, Sorting (Lessons 5–7, 12)
  - Stack, Queue, Recursion, Sorting algorithms
  
- **Student 3**: Trees, Heaps, Advanced Structures (Lessons 8–9, 13)
  - BST, AVL Tree, Priority Queue
  
- **Student 4**: Hash Tables, Sets, Maps, UI, Integration (Lessons 10–11)
  - HashTable, UI, System Integration

## Project Structure

- **`src/`** - Empty skeleton classes we need to implement (this is where we work)
- **`examples/src/`** - Complete working implementation (reference only - don't copy!)
- **`reports/`** - Empty report templates we need to fill in
- **`examples/reports/`** - Example report templates with placeholders (reference)

## Running the Project

### See the Working Example
```bash
./examples.sh
```
This compiles and runs the complete working implementation from `examples/src/`

### Compile Our Implementation
```bash
./compile.sh
```
This compiles our skeleton classes in `src/` (they're empty right now, so it'll just print a message)

### Run Our Implementation (after we finish it)
```bash
java -cp bin main.SATS
```

## Git Workflow

### First Time Setup
```bash
cd ~/projects  # or wherever you keep projects
git clone <repo-url>
cd ds
```

### Before You Start Working
```bash
cd ~/projects/ds  # or wherever you cloned it
git pull
```

### When You're Done With Your Changes
```bash
git add src/your-file.java
git commit -m "Added LinkedList implementation"
git pull
git push
```

### If There's a Conflict
Run `git pull` first, then push again. If git says there's a conflict, it'll tell you which files - open them and look for `<<<<<<<` markers. Ask for help if stuck.

## Notes

- All the skeleton classes in `src/` have `TODO` comments showing what we need to implement
- We can check `examples/src/` for reference, but we should write our own code
- The reports in `reports/` are empty - we need to fill them in as we go
- See `examples/` folder for detailed README and quick start guide

Go team!