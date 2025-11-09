package services;

import models.*;
import dataStructures.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Attendance Recording Service
 * Handles attendance tracking with undo functionality using stack
 */
public class AttendanceService {
    private StudentService studentService;
    private HashTable<String, List<AttendanceRecord>> attendanceMap; // studentId -> records
    private dataStructures.Queue<AttendanceRecord> attendanceQueue; // FIFO for daily attendance
    private dataStructures.Stack<UndoAction> undoStack; // For undo functionality
    private dataStructures.LinkedList<AttendanceRecord> allRecords; // All attendance records

    public AttendanceService(StudentService studentService) {
        this.studentService = studentService;
        this.attendanceMap = new HashTable<>();
        this.attendanceQueue = new dataStructures.Queue<>();
        this.undoStack = new dataStructures.Stack<>();
        this.allRecords = new dataStructures.LinkedList<>();
    }

    public boolean markAttendance(String studentId, LocalDate date, AttendanceRecord.AttendanceStatus status) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return false;
        }

        AttendanceRecord record = new AttendanceRecord(studentId, date, status);
        
        // Check if record already exists for this date
        List<AttendanceRecord> records = attendanceMap.get(studentId);
        if (records == null) {
            records = new ArrayList<>();
            attendanceMap.put(studentId, records);
        } else {
            for (AttendanceRecord r : records) {
                if (r.getDate().equals(date)) {
                    // Update existing record
                    UndoAction undoAction = new UndoAction(
                        UndoAction.ActionType.UPDATE_ATTENDANCE,
                        record,
                        r
                    );
                    undoStack.push(undoAction);
                    
                    r.setStatus(status);
                    return true;
                }
            }
        }

        // Add new record
        records.add(record);
        attendanceQueue.enqueue(record);
        allRecords.add(record);
        
        UndoAction undoAction = new UndoAction(
            UndoAction.ActionType.ADD_ATTENDANCE,
            record
        );
        undoStack.push(undoAction);

        return true;
    }

    public boolean markDailyAttendance(LocalDate date, Map<String, AttendanceRecord.AttendanceStatus> attendanceData) {
        boolean success = true;
        for (Map.Entry<String, AttendanceRecord.AttendanceStatus> entry : attendanceData.entrySet()) {
            if (!markAttendance(entry.getKey(), date, entry.getValue())) {
                success = false;
            }
        }
        return success;
    }

    public List<AttendanceRecord> getStudentAttendance(String studentId) {
        List<AttendanceRecord> records = attendanceMap.get(studentId);
        return records != null ? new ArrayList<>(records) : new ArrayList<>();
    }

    public List<AttendanceRecord> getClassAttendance(String className, LocalDate date) {
        List<Student> students = studentService.getStudentsByClass(className);
        List<AttendanceRecord> records = new ArrayList<>();
        
        for (Student student : students) {
            List<AttendanceRecord> studentRecords = attendanceMap.get(student.getId());
            if (studentRecords != null) {
                for (AttendanceRecord record : studentRecords) {
                    if (record.getDate().equals(date)) {
                        records.add(record);
                    }
                }
            }
        }
        
        return records;
    }

    public List<AttendanceRecord> getAbsentStudents(LocalDate date) {
        List<AttendanceRecord> absent = new ArrayList<>();
        List<Student> allStudents = studentService.getAllStudents();
        
        for (Student student : allStudents) {
            List<AttendanceRecord> records = attendanceMap.get(student.getId());
            boolean found = false;
            if (records != null) {
                for (AttendanceRecord record : records) {
                    if (record.getDate().equals(date)) {
                        found = true;
                        if (record.getStatus() == AttendanceRecord.AttendanceStatus.ABSENT) {
                            absent.add(record);
                        }
                        break;
                    }
                }
            }
            if (!found) {
                // Student has no record for this date, consider absent
                absent.add(new AttendanceRecord(student.getId(), date, AttendanceRecord.AttendanceStatus.ABSENT));
            }
        }
        
        return absent;
    }

    public List<AttendanceRecord> getLateStudents(LocalDate date) {
        List<AttendanceRecord> late = new ArrayList<>();
        List<Student> allStudents = studentService.getAllStudents();
        
        for (Student student : allStudents) {
            List<AttendanceRecord> records = attendanceMap.get(student.getId());
            if (records != null) {
                for (AttendanceRecord record : records) {
                    if (record.getDate().equals(date) && 
                        record.getStatus() == AttendanceRecord.AttendanceStatus.LATE) {
                        late.add(record);
                    }
                }
            }
        }
        
        return late;
    }

    public boolean undoLastAction() {
        if (undoStack.isEmpty()) {
            return false;
        }

        UndoAction action = undoStack.pop();
        
        switch (action.getType()) {
            case ADD_ATTENDANCE:
                AttendanceRecord record = action.getRecord();
                List<AttendanceRecord> records = attendanceMap.get(record.getStudentId());
                if (records != null) {
                    records.remove(record);
                    allRecords.remove(record);
                }
                break;
                
            case UPDATE_ATTENDANCE:
                AttendanceRecord currentRecord = action.getRecord();
                AttendanceRecord previousRecord = action.getPreviousRecord();
                List<AttendanceRecord> updateRecords = attendanceMap.get(currentRecord.getStudentId());
                if (updateRecords != null) {
                    for (AttendanceRecord r : updateRecords) {
                        if (r.getDate().equals(currentRecord.getDate())) {
                            r.setStatus(previousRecord.getStatus());
                            break;
                        }
                    }
                }
                break;
        }
        
        return true;
    }

    public List<AttendanceRecord> getAllRecords() {
        List<AttendanceRecord> records = new ArrayList<>();
        for (int i = 0; i < allRecords.size(); i++) {
            records.add(allRecords.get(i));
        }
        return records;
    }

    public int getUndoStackSize() {
        return undoStack.size();
    }
}

