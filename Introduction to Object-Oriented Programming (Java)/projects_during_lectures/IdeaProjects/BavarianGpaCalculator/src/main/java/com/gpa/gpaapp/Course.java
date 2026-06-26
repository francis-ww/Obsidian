package com.gpa.gpaapp;

import javafx.beans.property.*;

public class Course {
    private final StringProperty semester = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty score = new SimpleDoubleProperty();
    private final DoubleProperty credit = new SimpleDoubleProperty();
    private final ObjectProperty<CourseType> type = new SimpleObjectProperty<>();

    public Course(String semester, String name, double score, double credit, CourseType type) {
        setSemester(semester);
        setName(name);
        setScore(score);
        setCredit(credit);
        setType(type);
    }

    // 计算巴伐利亚绩点逻辑
    public double getGermanGPA() {
        if (getScore() < 60) return 4.0; // 德国及格线通常到4.0
        double gpa = 1.0 + 3.0 * (100.0 - getScore()) / 40.0;
        return Math.max(1.0, Math.min(4.0, gpa)); // 范围在 1.0 - 4.0 之间
    }

    // Getters and Setters (JavaFX 规范)
    public String getSemester() { return semester.get(); }
    public void setSemester(String value) { semester.set(value); }
    public StringProperty semesterProperty() { return semester; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public double getScore() { return score.get(); }
    public void setScore(double value) { score.set(value); }
    public DoubleProperty scoreProperty() { return score; }

    public double getCredit() { return credit.get(); }
    public void setCredit(double value) { credit.set(value); }
    public DoubleProperty creditProperty() { return credit; }

    public CourseType getType() { return type.get(); }
    public void setType(CourseType value) { type.set(value); }
    public ObjectProperty<CourseType> typeProperty() { return type; }
}