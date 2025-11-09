package services;

import models.Student;
import dataStructures.*;
import java.util.*;

/**
 * Student Management Service
 * Handles all student-related operations using various data structures
 */
public class StudentService {
    private HashTable<String, Student> studentMap; // Fast lookup by ID
    private dataStructures.LinkedList<Student> studentList; // For sequential access
    private AVLTree<Student> studentTree; // Self-balancing tree for scalable management
    private Set<String> classSet; // Unique class names
    private BinarySearchTree<String> nameBST; // For name-based search

    public StudentService() {
        this.studentMap = new HashTable<>();
        this.studentList = new dataStructures.LinkedList<>();
        this.studentTree = new AVLTree<>();
        this.classSet = new HashSet<>();
        this.nameBST = new BinarySearchTree<>();
    }

    public boolean addStudent(String id, String name, String className, int enrollmentYear) {
        if (studentMap.containsKey(id)) {
            return false; // Student already exists
        }

        Student student = new Student(id, name, className, enrollmentYear);
        studentMap.put(id, student);
        studentList.add(student);
        studentTree.insert(student);
        classSet.add(className);
        nameBST.insert(name.toLowerCase());

        return true;
    }

    public boolean editStudent(String id, String newName, String newClassName, int newEnrollmentYear) {
        Student student = studentMap.get(id);
        if (student == null) {
            return false;
        }

        // Update name BST
        nameBST.delete(student.getName().toLowerCase());
        nameBST.insert(newName.toLowerCase());

        // Update class set
        classSet.remove(student.getClassName());
        classSet.add(newClassName);

        // Update student data
        student.setName(newName);
        student.setClassName(newClassName);
        student.setEnrollmentYear(newEnrollmentYear);

        // Update tree (remove and reinsert)
        studentTree.delete(student);
        studentTree.insert(student);

        return true;
    }

    public boolean removeStudent(String id) {
        Student student = studentMap.get(id);
        if (student == null) {
            return false;
        }

        studentMap.remove(id);
        studentList.remove(student);
        studentTree.delete(student);
        nameBST.delete(student.getName().toLowerCase());

        // Check if class is still used
        boolean classStillUsed = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getClassName().equals(student.getClassName())) {
                classStillUsed = true;
                break;
            }
        }
        if (!classStillUsed) {
            classSet.remove(student.getClassName());
        }

        return true;
    }

    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    public Student searchStudentByName(String name) {
        // Search in list
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            students.add(studentList.get(i));
        }
        return students;
    }

    public List<Student> getStudentsByClass(String className) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getClassName().equals(className)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> getStudentsAlphabetically() {
        List<Student> students = getAllStudents();
        sortByName(students);
        return students;
    }

    public List<Student> getStudentsByClassSorted(String className) {
        List<Student> students = getStudentsByClass(className);
        sortByName(students);
        return students;
    }

    public Set<String> getAllClasses() {
        return new HashSet<>(classSet);
    }

    public int getStudentCount() {
        return studentList.size();
    }

    public void sortByName(List<Student> students) {
        Collections.sort(students);
    }

    public void sortByClass(List<Student> students) {
        students.sort((s1, s2) -> {
            int classCompare = s1.getClassName().compareTo(s2.getClassName());
            if (classCompare != 0) {
                return classCompare;
            }
            return s1.getName().compareToIgnoreCase(s2.getName());
        });
    }

    public void sortByEnrollmentYear(List<Student> students) {
        students.sort((s1, s2) -> {
            int yearCompare = Integer.compare(s1.getEnrollmentYear(), s2.getEnrollmentYear());
            if (yearCompare != 0) {
                return yearCompare;
            }
            return s1.getName().compareToIgnoreCase(s2.getName());
        });
    }
}

