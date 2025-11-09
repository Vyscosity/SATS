package ui;

import services.*;
import models.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Console-based User Interface for Student Attendance Tracking System
 */
public class ConsoleUI {
    private StudentService studentService;
    private AttendanceService attendanceService;
    private ReportingService reportingService;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;

    public ConsoleUI() {
        this.studentService = new StudentService();
        this.attendanceService = new AttendanceService(studentService);
        this.reportingService = new ReportingService(studentService, attendanceService);
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void start() {
        System.out.println("========================================");
        System.out.println("Student Attendance Tracking System");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    studentManagementMenu();
                    break;
                case 2:
                    attendanceMenu();
                    break;
                case 3:
                    searchAndSortMenu();
                    break;
                case 4:
                    reportingMenu();
                    break;
                case 5:
                    analyticsMenu();
                    break;
                case 6:
                    displaySummary();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using SATS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Student Management");
        System.out.println("2. Attendance Recording");
        System.out.println("3. Search and Sort");
        System.out.println("4. Reports");
        System.out.println("5. Analytics");
        System.out.println("6. View Summary");
        System.out.println("0. Exit");
    }

    private void studentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Display Students by Class");
            System.out.println("6. Display Students Alphabetically");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    displayStudentsByClass();
                    break;
                case 6:
                    displayStudentsAlphabetically();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void attendanceMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Attendance Recording ===");
            System.out.println("1. Mark Attendance for Student");
            System.out.println("2. Mark Daily Attendance");
            System.out.println("3. View Student Attendance");
            System.out.println("4. View Class Attendance");
            System.out.println("5. View Absent Students");
            System.out.println("6. View Late Students");
            System.out.println("7. Undo Last Action");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    markAttendance();
                    break;
                case 2:
                    markDailyAttendance();
                    break;
                case 3:
                    viewStudentAttendance();
                    break;
                case 4:
                    viewClassAttendance();
                    break;
                case 5:
                    viewAbsentStudents();
                    break;
                case 6:
                    viewLateStudents();
                    break;
                case 7:
                    undoLastAction();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchAndSortMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Search and Sort ===");
            System.out.println("1. Search Student by ID");
            System.out.println("2. Search Student by Name");
            System.out.println("3. Sort Students by Name");
            System.out.println("4. Sort Students by Class");
            System.out.println("5. Sort Students by Enrollment Year");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    searchByID();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    sortByName();
                    break;
                case 4:
                    sortByClass();
                    break;
                case 5:
                    sortByEnrollmentYear();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void reportingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Reports ===");
            System.out.println("1. Student Attendance Report");
            System.out.println("2. Class Attendance Report");
            System.out.println("3. View Alerts");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    generateStudentReport();
                    break;
                case 2:
                    generateClassReport();
                    break;
                case 3:
                    viewAlerts();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void analyticsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Analytics ===");
            System.out.println("1. Analyze Student Trends");
            System.out.println("2. Generate Alerts for Today");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    analyzeTrends();
                    break;
                case 2:
                    generateAlerts();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Student Management Methods
    private void addStudent() {
        System.out.println("\n--- Add Student ---");
        String id = getStringInput("Enter Student ID: ");
        String name = getStringInput("Enter Student Name: ");
        String className = getStringInput("Enter Class: ");
        int year = getIntInput("Enter Enrollment Year: ");

        if (studentService.addStudent(id, name, className, year)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Error: Student with ID " + id + " already exists.");
        }
    }

    private void editStudent() {
        System.out.println("\n--- Edit Student ---");
        String id = getStringInput("Enter Student ID: ");
        
        Student student = studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current student: " + student);
        String name = getStringInput("Enter new name (or press Enter to keep current): ");
        String className = getStringInput("Enter new class (or press Enter to keep current): ");
        String yearStr = getStringInput("Enter new enrollment year (or press Enter to keep current): ");

        if (name.isEmpty()) name = student.getName();
        if (className.isEmpty()) className = student.getClassName();
        int year = yearStr.isEmpty() ? student.getEnrollmentYear() : Integer.parseInt(yearStr);

        if (studentService.editStudent(id, name, className, year)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Error updating student.");
        }
    }

    private void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        String id = getStringInput("Enter Student ID: ");

        if (studentService.removeStudent(id)) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private void displayStudentsByClass() {
        System.out.println("\n--- Students by Class ---");
        Set<String> classes = studentService.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes found.");
            return;
        }

        System.out.println("Available classes: " + classes);
        String className = getStringInput("Enter class name: ");
        
        List<Student> students = studentService.getStudentsByClassSorted(className);
        if (students.isEmpty()) {
            System.out.println("No students found in class " + className);
        } else {
            System.out.println("\nStudents in " + className + ":");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private void displayStudentsAlphabetically() {
        System.out.println("\n--- Students (Alphabetical) ---");
        List<Student> students = studentService.getStudentsAlphabetically();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Attendance Methods
    private void markAttendance() {
        System.out.println("\n--- Mark Attendance ---");
        String id = getStringInput("Enter Student ID: ");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");
        
        System.out.println("1. Present");
        System.out.println("2. Absent");
        System.out.println("3. Late");
        int statusChoice = getIntInput("Select status: ");

        AttendanceRecord.AttendanceStatus status;
        switch (statusChoice) {
            case 1:
                status = AttendanceRecord.AttendanceStatus.PRESENT;
                break;
            case 2:
                status = AttendanceRecord.AttendanceStatus.ABSENT;
                break;
            case 3:
                status = AttendanceRecord.AttendanceStatus.LATE;
                break;
            default:
                System.out.println("Invalid status.");
                return;
        }

        if (attendanceService.markAttendance(id, date, status)) {
            System.out.println("Attendance marked successfully!");
        } else {
            System.out.println("Error marking attendance. Student may not exist.");
        }
    }

    private void markDailyAttendance() {
        System.out.println("\n--- Mark Daily Attendance ---");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");
        String className = getStringInput("Enter class name: ");

        List<Student> students = studentService.getStudentsByClass(className);
        if (students.isEmpty()) {
            System.out.println("No students found in class " + className);
            return;
        }

        Map<String, AttendanceRecord.AttendanceStatus> attendanceData = new HashMap<>();
        
        for (Student student : students) {
            System.out.println("\nStudent: " + student.getName() + " (" + student.getId() + ")");
            System.out.println("1. Present");
            System.out.println("2. Absent");
            System.out.println("3. Late");
            int statusChoice = getIntInput("Select status: ");

            AttendanceRecord.AttendanceStatus status;
            switch (statusChoice) {
                case 1:
                    status = AttendanceRecord.AttendanceStatus.PRESENT;
                    break;
                case 2:
                    status = AttendanceRecord.AttendanceStatus.ABSENT;
                    break;
                case 3:
                    status = AttendanceRecord.AttendanceStatus.LATE;
                    break;
                default:
                    status = AttendanceRecord.AttendanceStatus.ABSENT;
                    break;
            }
            attendanceData.put(student.getId(), status);
        }

        if (attendanceService.markDailyAttendance(date, attendanceData)) {
            System.out.println("Daily attendance marked successfully!");
        } else {
            System.out.println("Error marking daily attendance.");
        }
    }

    private void viewStudentAttendance() {
        System.out.println("\n--- Student Attendance ---");
        String id = getStringInput("Enter Student ID: ");
        
        List<AttendanceRecord> records = attendanceService.getStudentAttendance(id);
        if (records.isEmpty()) {
            System.out.println("No attendance records found for this student.");
        } else {
            System.out.println("Attendance Records:");
            for (AttendanceRecord record : records) {
                System.out.println(record);
            }
        }
    }

    private void viewClassAttendance() {
        System.out.println("\n--- Class Attendance ---");
        String className = getStringInput("Enter class name: ");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");

        List<AttendanceRecord> records = attendanceService.getClassAttendance(className, date);
        if (records.isEmpty()) {
            System.out.println("No attendance records found for this class on " + date);
        } else {
            System.out.println("Attendance Records for " + className + " on " + date + ":");
            for (AttendanceRecord record : records) {
                System.out.println(record);
            }
        }
    }

    private void viewAbsentStudents() {
        System.out.println("\n--- Absent Students ---");
        LocalDate date = getDateInput("Enter date: ");

        List<AttendanceRecord> records = attendanceService.getAbsentStudents(date);
        if (records.isEmpty()) {
            System.out.println("No absent students found for " + date);
        } else {
            System.out.println("Absent Students on " + date + ":");
            for (AttendanceRecord record : records) {
                Student student = studentService.getStudentById(record.getStudentId());
                System.out.println(student != null ? student.getName() + " (" + record.getStudentId() + ")" : record.getStudentId());
            }
        }
    }

    private void viewLateStudents() {
        System.out.println("\n--- Late Students ---");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");

        List<AttendanceRecord> records = attendanceService.getLateStudents(date);
        if (records.isEmpty()) {
            System.out.println("No late students found for " + date);
        } else {
            System.out.println("Late Students on " + date + ":");
            for (AttendanceRecord record : records) {
                Student student = studentService.getStudentById(record.getStudentId());
                System.out.println(student != null ? student.getName() + " (" + record.getStudentId() + ")" : record.getStudentId());
            }
        }
    }

    private void undoLastAction() {
        if (attendanceService.undoLastAction()) {
            System.out.println("Last action undone successfully!");
        } else {
            System.out.println("No actions to undo.");
        }
    }

    // Search and Sort Methods
    private void searchByID() {
        System.out.println("\n--- Search by ID ---");
        String id = getStringInput("Enter Student ID: ");
        
        Student student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void searchByName() {
        System.out.println("\n--- Search by Name ---");
        String name = getStringInput("Enter Student Name: ");
        
        Student student = studentService.searchStudentByName(name);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void sortByName() {
        System.out.println("\n--- Students Sorted by Name ---");
        List<Student> students = studentService.getAllStudents();
        studentService.sortByName(students);
        
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void sortByClass() {
        System.out.println("\n--- Students Sorted by Class ---");
        List<Student> students = studentService.getAllStudents();
        studentService.sortByClass(students);
        
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void sortByEnrollmentYear() {
        System.out.println("\n--- Students Sorted by Enrollment Year ---");
        List<Student> students = studentService.getAllStudents();
        studentService.sortByEnrollmentYear(students);
        
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Reporting Methods
    private void generateStudentReport() {
        System.out.println("\n--- Student Attendance Report ---");
        String id = getStringInput("Enter Student ID: ");
        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate = getDateInput("Enter end date (yyyy-MM-dd): ");

        String report = reportingService.generateStudentReport(id, startDate, endDate);
        System.out.println(report);
    }

    private void generateClassReport() {
        System.out.println("\n--- Class Attendance Report ---");
        String className = getStringInput("Enter class name: ");
        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate = getDateInput("Enter end date (yyyy-MM-dd): ");

        String report = reportingService.generateClassReport(className, startDate, endDate);
        System.out.println(report);
    }

    private void viewAlerts() {
        System.out.println("\n--- Attendance Alerts ---");
        List<AttendanceAlert> alerts = reportingService.getAlerts();
        
        if (alerts.isEmpty()) {
            System.out.println("No alerts available. Generate alerts first.");
        } else {
            for (AttendanceAlert alert : alerts) {
                System.out.println(alert);
            }
        }
    }

    // Analytics Methods
    private void analyzeTrends() {
        System.out.println("\n--- Analyze Trends ---");
        String id = getStringInput("Enter Student ID: ");
        int days = getIntInput("Enter number of days to analyze: ");

        String analysis = reportingService.analyzeTrends(id, days);
        System.out.println(analysis);
    }

    private void generateAlerts() {
        System.out.println("\n--- Generate Alerts ---");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");
        
        reportingService.generateAlerts(date);
        List<AttendanceAlert> alerts = reportingService.getAlerts();
        
        if (alerts.isEmpty()) {
            System.out.println("No alerts generated for " + date);
        } else {
            System.out.println("Generated " + alerts.size() + " alerts:");
            for (AttendanceAlert alert : alerts) {
                System.out.println(alert);
            }
        }
    }

    // Utility Methods
    private void displaySummary() {
        System.out.println("\n=== System Summary ===");
        System.out.println("Total Students: " + studentService.getStudentCount());
        System.out.println("Total Classes: " + studentService.getAllClasses().size());
        System.out.println("Undo Stack Size: " + attendanceService.getUndoStackSize());
        
        List<AttendanceAlert> alerts = reportingService.getAlerts();
        System.out.println("Active Alerts: " + alerts.size());
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getIntInput(prompt);
        }
    }

    private LocalDate getDateInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return LocalDate.parse(input, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return getDateInput(prompt);
        }
    }
}

