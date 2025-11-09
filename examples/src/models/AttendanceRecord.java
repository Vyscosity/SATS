package models;

import java.time.LocalDate;

/**
 * Attendance Record model class
 * Represents a single attendance record for a student on a specific date
 */
public class AttendanceRecord implements Comparable<AttendanceRecord> {
    private String studentId;
    private LocalDate date;
    private AttendanceStatus status;

    public enum AttendanceStatus {
        PRESENT, ABSENT, LATE
    }

    public AttendanceRecord(String studentId, LocalDate date, AttendanceStatus status) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    @Override
    public int compareTo(AttendanceRecord other) {
        int dateCompare = this.date.compareTo(other.date);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AttendanceRecord record = (AttendanceRecord) obj;
        return studentId.equals(record.studentId) && date.equals(record.date);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode() * 31 + date.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Student ID: %s, Date: %s, Status: %s", 
            studentId, date, status);
    }
}

