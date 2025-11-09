package models;

/**
 * Student model class
 * Represents a student with ID, name, class, and enrollment year
 */
public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private String className;
    private int enrollmentYear;

    public Student(String id, String name, String className, int enrollmentYear) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.enrollmentYear = enrollmentYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id != null ? id.equals(student.id) : student.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Class: %s, Year: %d", 
            id, name, className, enrollmentYear);
    }
}

