package com.gpa.gpaapp;

public enum CourseType {
    COMPULSORY("必修"),
    ELECTIVE("选修"),
    OPTIONAL("任选"),
    MINOR_COMPULSORY("辅修必修"),
    MINOR_ELECTIVE("辅修选修");

    private final String label;
    CourseType(String label) { this.label = label; }
    public String getLabel() { return label; }
    @Override public String toString() { return label; }
}
