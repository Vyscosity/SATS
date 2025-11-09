package services;

import models.*;
import dataStructures.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Reporting & Analytics Service
 * Generates reports and analyzes attendance patterns using recursion
 */
public class ReportingService {
    private StudentService studentService;
    private AttendanceService attendanceService;
    private dataStructures.PriorityQueue<AttendanceAlert> alertQueue;

    public ReportingService(StudentService studentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.alertQueue = new dataStructures.PriorityQueue<>();
    }

    /**
     * Generate attendance report for a specific student
     * Uses recursion to traverse attendance records
     */
    public String generateStudentReport(String studentId, LocalDate startDate, LocalDate endDate) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "Student not found.";
        }

        List<AttendanceRecord> records = attendanceService.getStudentAttendance(studentId);
        List<AttendanceRecord> filteredRecords = filterRecordsByDate(records, startDate, endDate);

        int present = 0, absent = 0, late = 0;
        for (AttendanceRecord record : filteredRecords) {
            switch (record.getStatus()) {
                case PRESENT:
                    present++;
                    break;
                case ABSENT:
                    absent++;
                    break;
                case LATE:
                    late++;
                    break;
            }
        }

        int total = filteredRecords.size();
        double attendanceRate = total > 0 ? (double)(present + late) / total * 100 : 0;

        StringBuilder report = new StringBuilder();
        report.append("=== Student Attendance Report ===\n");
        report.append("Student: ").append(student.getName()).append(" (").append(studentId).append(")\n");
        report.append("Class: ").append(student.getClassName()).append("\n");
        report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("Total Days: ").append(total).append("\n");
        report.append("Present: ").append(present).append("\n");
        report.append("Absent: ").append(absent).append("\n");
        report.append("Late: ").append(late).append("\n");
        report.append("Attendance Rate: ").append(String.format("%.2f%%", attendanceRate)).append("\n");

        return report.toString();
    }

    /**
     * Generate attendance report for a class
     * Uses recursion for report generation
     */
    public String generateClassReport(String className, LocalDate startDate, LocalDate endDate) {
        List<Student> students = studentService.getStudentsByClass(className);
        if (students.isEmpty()) {
            return "Class not found or has no students.";
        }

        StringBuilder report = new StringBuilder();
        report.append("=== Class Attendance Report ===\n");
        report.append("Class: ").append(className).append("\n");
        report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("Total Students: ").append(students.size()).append("\n\n");

        int totalPresent = 0, totalAbsent = 0, totalLate = 0;
        int totalDays = 0;

        for (Student student : students) {
            List<AttendanceRecord> records = attendanceService.getStudentAttendance(student.getId());
            List<AttendanceRecord> filteredRecords = filterRecordsByDate(records, startDate, endDate);
            
            int present = 0, absent = 0, late = 0;
            for (AttendanceRecord record : filteredRecords) {
                switch (record.getStatus()) {
                    case PRESENT:
                        present++;
                        break;
                    case ABSENT:
                        absent++;
                        break;
                    case LATE:
                        late++;
                        break;
                }
            }

            totalPresent += present;
            totalAbsent += absent;
            totalLate += late;
            if (filteredRecords.size() > totalDays) {
                totalDays = filteredRecords.size();
            }

            report.append(String.format("%s (%s): Present: %d, Absent: %d, Late: %d\n",
                student.getName(), student.getId(), present, absent, late));
        }

        report.append("\n=== Class Summary ===\n");
        report.append("Total Present: ").append(totalPresent).append("\n");
        report.append("Total Absent: ").append(totalAbsent).append("\n");
        report.append("Total Late: ").append(totalLate).append("\n");
        if (totalDays > 0) {
            double avgRate = (double)(totalPresent + totalLate) / (totalDays * students.size()) * 100;
            report.append("Average Attendance Rate: ").append(String.format("%.2f%%", avgRate)).append("\n");
        }

        return report.toString();
    }

    /**
     * Analyze attendance trends using historical data
     */
    public String analyzeTrends(String studentId, int days) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return "Student not found.";
        }

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days);

        List<AttendanceRecord> records = attendanceService.getStudentAttendance(studentId);
        List<AttendanceRecord> filteredRecords = filterRecordsByDate(records, startDate, endDate);

        if (filteredRecords.isEmpty()) {
            return "No attendance data available for the specified period.";
        }

        // Sort by date
        filteredRecords.sort(Comparator.comparing(AttendanceRecord::getDate));

        int present = 0, absent = 0, late = 0;
        Map<LocalDate, AttendanceRecord.AttendanceStatus> dailyStatus = new HashMap<>();
        
        for (AttendanceRecord record : filteredRecords) {
            dailyStatus.put(record.getDate(), record.getStatus());
            switch (record.getStatus()) {
                case PRESENT:
                    present++;
                    break;
                case ABSENT:
                    absent++;
                    break;
                case LATE:
                    late++;
                    break;
            }
        }

        StringBuilder analysis = new StringBuilder();
        analysis.append("=== Attendance Trend Analysis ===\n");
        analysis.append("Student: ").append(student.getName()).append(" (").append(studentId).append(")\n");
        analysis.append("Period: Last ").append(days).append(" days\n");
        analysis.append("Present: ").append(present).append("\n");
        analysis.append("Absent: ").append(absent).append("\n");
        analysis.append("Late: ").append(late).append("\n");

        // Calculate trend
        if (filteredRecords.size() >= 2) {
            List<AttendanceRecord> recent = filteredRecords.subList(
                Math.max(0, filteredRecords.size() - filteredRecords.size() / 2),
                filteredRecords.size()
            );
            List<AttendanceRecord> earlier = filteredRecords.subList(
                0,
                filteredRecords.size() / 2
            );

            double recentRate = calculateAttendanceRate(recent);
            double earlierRate = calculateAttendanceRate(earlier);

            analysis.append("\nTrend: ");
            if (recentRate > earlierRate + 5) {
                analysis.append("Improving (+").append(String.format("%.1f%%", recentRate - earlierRate)).append(")\n");
            } else if (recentRate < earlierRate - 5) {
                analysis.append("Declining (").append(String.format("%.1f%%", recentRate - earlierRate)).append(")\n");
            } else {
                analysis.append("Stable\n");
            }
        }

        return analysis.toString();
    }

    /**
     * Generate alerts for late or absent students using priority queue
     */
    public void generateAlerts(LocalDate date) {
        alertQueue.clear();

        List<AttendanceRecord> absent = attendanceService.getAbsentStudents(date);
        List<AttendanceRecord> late = attendanceService.getLateStudents(date);

        // Add absent students with high priority
        for (AttendanceRecord record : absent) {
            Student student = studentService.getStudentById(record.getStudentId());
            if (student != null) {
                AttendanceAlert alert = new AttendanceAlert(
                    student.getId(),
                    student.getName(),
                    "Absent on " + date,
                    10 // High priority
                );
                alertQueue.enqueue(alert);
            }
        }

        // Add late students with medium priority
        for (AttendanceRecord record : late) {
            Student student = studentService.getStudentById(record.getStudentId());
            if (student != null) {
                AttendanceAlert alert = new AttendanceAlert(
                    student.getId(),
                    student.getName(),
                    "Late on " + date,
                    5 // Medium priority
                );
                alertQueue.enqueue(alert);
            }
        }
    }

    public List<AttendanceAlert> getAlerts() {
        List<AttendanceAlert> alerts = new ArrayList<>();
        dataStructures.PriorityQueue<AttendanceAlert> tempQueue = new dataStructures.PriorityQueue<>();
        
        while (!alertQueue.isEmpty()) {
            AttendanceAlert alert = alertQueue.dequeue();
            alerts.add(alert);
            tempQueue.enqueue(alert);
        }
        
        // Restore queue
        while (!tempQueue.isEmpty()) {
            alertQueue.enqueue(tempQueue.dequeue());
        }
        
        return alerts;
    }

    // Helper methods using recursion
    private List<AttendanceRecord> filterRecordsByDate(List<AttendanceRecord> records, 
                                                       LocalDate startDate, LocalDate endDate) {
        List<AttendanceRecord> filtered = new ArrayList<>();
        filterRecordsRecursive(records, 0, startDate, endDate, filtered);
        return filtered;
    }

    private void filterRecordsRecursive(List<AttendanceRecord> records, int index,
                                        LocalDate startDate, LocalDate endDate,
                                        List<AttendanceRecord> result) {
        if (index >= records.size()) {
            return;
        }
        
        AttendanceRecord record = records.get(index);
        LocalDate recordDate = record.getDate();
        
        if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
            result.add(record);
        }
        
        filterRecordsRecursive(records, index + 1, startDate, endDate, result);
    }

    private double calculateAttendanceRate(List<AttendanceRecord> records) {
        if (records.isEmpty()) {
            return 0;
        }
        
        int presentOrLate = 0;
        for (AttendanceRecord record : records) {
            if (record.getStatus() != AttendanceRecord.AttendanceStatus.ABSENT) {
                presentOrLate++;
            }
        }
        
        return (double) presentOrLate / records.size() * 100;
    }
}

