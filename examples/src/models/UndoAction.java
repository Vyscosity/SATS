package models;

/**
 * Undo Action model class
 * Represents an action that can be undone using the stack
 */
public class UndoAction {
    public enum ActionType {
        ADD_ATTENDANCE, REMOVE_ATTENDANCE, UPDATE_ATTENDANCE
    }

    private ActionType type;
    private AttendanceRecord record;
    private AttendanceRecord previousRecord; // For updates

    public UndoAction(ActionType type, AttendanceRecord record) {
        this.type = type;
        this.record = record;
    }

    public UndoAction(ActionType type, AttendanceRecord record, AttendanceRecord previousRecord) {
        this.type = type;
        this.record = record;
        this.previousRecord = previousRecord;
    }

    public ActionType getType() {
        return type;
    }

    public AttendanceRecord getRecord() {
        return record;
    }

    public AttendanceRecord getPreviousRecord() {
        return previousRecord;
    }
}

