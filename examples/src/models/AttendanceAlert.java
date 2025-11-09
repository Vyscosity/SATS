package models;

/**
 * Attendance Alert model class
 * Used in priority queue for alerts on late or absent students
 */
public class AttendanceAlert implements Comparable<AttendanceAlert> {
    private String studentId;
    private String studentName;
    private String message;
    private int priority; // Higher priority = more urgent

    public AttendanceAlert(String studentId, String studentName, String message, int priority) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.message = message;
        this.priority = priority;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getMessage() {
        return message;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(AttendanceAlert other) {
        // Higher priority comes first (max heap behavior)
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public String toString() {
        return String.format("[Priority: %d] %s (%s): %s", 
            priority, studentName, studentId, message);
    }
}

