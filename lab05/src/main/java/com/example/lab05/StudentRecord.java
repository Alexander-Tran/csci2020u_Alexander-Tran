package com.example.lab05;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private char letterGrade;

    //Constructor
    public StudentRecord(String studentID, float assignments, float midterm, float finalExam) {
        this.studentID = studentID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.finalMark = (float) ((0.2 * this.assignments) + (0.3 * this.midterm) + (0.5 * this.finalExam));

        if(this.finalMark >= 80 && this.finalMark <= 100) {
            this.letterGrade = 'A';
        }
        else if(this.finalMark >= 70 && this.finalMark <= 79) {
            this.letterGrade = 'B';
        }
        else if(this.finalMark >= 60 && this.finalMark <= 69) {
            this.letterGrade = 'C';
        }
        else if(this.finalMark >= 50 && this.finalMark <= 59) {
            this.letterGrade = 'D';
        }
        else if(this.finalMark >= 0 && this.finalMark <= 49) {
            this.letterGrade = 'F';
        }
    }

    //Accessors
    public String getStudentID() {
        return studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public char getLetterGrade() {
        return letterGrade;
    }
}
