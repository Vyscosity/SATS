# Individual Contribution Report
## Student 2: Stack, Queue, Recursion, Sorting

**Name:** [Your Name]  
**Student ID:** [Your ID]  
**Role:** Stack, Queue, Recursion, Sorting (Lessons 5–7, 12)  
**Date:** [Date]

---

## Overview

This report details my individual contributions to the Student Attendance Tracking System (SATS) project, focusing on stack, queue, recursion, and sorting implementations.

---

## Responsibilities

### Primary Responsibilities
- Implementation of Stack data structure for undo functionality
- Implementation of Queue data structure for FIFO attendance processing
- Implementation of recursive algorithms for report generation
- Implementation of sorting algorithms for student data

### Lessons Covered
- **Lesson 5:** [Topic]
- **Lesson 6:** [Topic]
- **Lesson 7:** [Topic]
- **Lesson 12:** [Topic]

---

## Code Contributions

### Files Created/Modified

#### 1. `src/dataStructures/Stack.java`
**Purpose:** Stack implementation for undo functionality in attendance tracking

**Key Features:**
- LIFO (Last In, First Out) operations
- Push, pop, peek operations
- Size tracking

**Code Highlights:**
```java
// Key methods implemented:
- push(T item)
- pop()
- peek()
- isEmpty()
- size()
```

**Usage in Project:**
- Used in `AttendanceService` for undo functionality
- Stores `UndoAction` objects for reversing attendance changes

**Challenges Faced:**
- [Describe any challenges encountered]

---

#### 2. `src/dataStructures/Queue.java`
**Purpose:** Queue implementation for managing attendance records in FIFO order

**Key Features:**
- FIFO (First In, First Out) operations
- Enqueue, dequeue, peek operations

**Code Highlights:**
```java
// Key methods implemented:
- enqueue(T item)
- dequeue()
- peek()
- isEmpty()
- size()
```

**Usage in Project:**
- Used in `AttendanceService` for daily attendance processing
- Maintains chronological order of attendance records

---

#### 3. Recursive Algorithms in `ReportingService.java`
**Purpose:** Recursive methods for report generation and data filtering

**Key Methods:**
- `filterRecordsRecursive()` - Recursively filters attendance records by date
- `inOrderRecursive()` - Recursive tree traversal (if implemented)

**Implementation Details:**
[Describe the recursive approach used]

---

#### 4. Sorting Algorithms in `StudentService.java`
**Purpose:** Sorting students by various criteria

**Key Methods:**
- `sortByName()` - Sort students alphabetically
- `sortByClass()` - Sort students by class
- `sortByEnrollmentYear()` - Sort students by enrollment year

**Algorithm Used:**
- [Describe the sorting algorithm(s) used]

---

## Technical Implementation Details

### Stack Implementation
[Describe the stack implementation details]

### Queue Implementation
[Describe the queue implementation details]

### Recursion Strategy
[Describe the recursive algorithms and their base cases]

### Sorting Strategy
[Describe the sorting algorithms and their complexity]

### Complexity Analysis

**Stack:**
- Push: O(1)
- Pop: O(1)
- Peek: O(1)
- Space: O(n)

**Queue:**
- Enqueue: O(1)
- Dequeue: O(1)
- Peek: O(1)
- Space: O(n)

**Recursive Methods:**
- Time: [Complexity]
- Space: [Complexity]

**Sorting:**
- Time: [Complexity]
- Space: [Complexity]

---

## Integration with Other Modules

### Integration Points
1. **AttendanceService Integration**
   - Stack for undo functionality
   - Queue for attendance processing

2. **ReportingService Integration**
   - Recursive methods for report generation

3. **StudentService Integration**
   - Sorting methods for student organization

### Collaboration with Team Members
- [Describe collaboration with other students]

---

## Testing

### Test Cases Developed
1. **Stack Test Cases**
   - [Test case descriptions]

2. **Queue Test Cases**
   - [Test case descriptions]

3. **Recursion Test Cases**
   - [Test case descriptions]

4. **Sorting Test Cases**
   - [Test case descriptions]

---

## Challenges and Solutions

### Challenge 1: Implementing Undo with Stack
**Problem:** [Description]  
**Solution:** [Solution]  
**Lessons Learned:** [Lessons]

### Challenge 2: Recursive Report Generation
**Problem:** [Description]  
**Solution:** [Solution]  
**Lessons Learned:** [Lessons]

### Challenge 3: Efficient Sorting
**Problem:** [Description]  
**Solution:** [Solution]  
**Lessons Learned:** [Lessons]

---

## Learning Outcomes

### Technical Skills Gained
- Stack and Queue data structures
- Recursive algorithm design
- Sorting algorithm implementation
- Base case identification in recursion

### Concepts Mastered
- LIFO vs FIFO principles
- Recursion vs iteration
- Algorithm complexity analysis

---

## Code Statistics

- **Lines of Code Written:** [Number]
- **Files Created:** [Number]
- **Methods Implemented:** [Number]
- **Time Spent:** [Hours]

---

## Reflection

### What Went Well
- [Reflection points]

### What Could Be Improved
- [Improvement points]

---

## References

- [References used]

