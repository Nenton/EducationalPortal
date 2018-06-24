package ru.innopolis.stc9.earth_stc9.pojo;

import java.sql.Date;

/**
 * @author Aleksandr Tikhonov - created 23.06.2018, modified 23.06.2018
 */

public class Performance {
    private int studentId;
    private int sublectId;
    private String subjectName;
    private int lessonId;
    private String lessonTheme;
    private Date lessonDate;
    private int mark;
    private Date markDate;
    private int attendance;

    public Performance(int lessonId, String lessonTheme, Date lessonDate, int mark, Date markDate, int attendance, String subjectName) {
        this.lessonId = lessonId;
        this.lessonTheme = lessonTheme;
        this.lessonDate = lessonDate;
        this.mark = mark;
        this.markDate = markDate;
        this.attendance = attendance;
        this.subjectName = subjectName;
    }

    public int getSublectId() {
        return sublectId;
    }

    public void setSublectId(int sublectId) {
        this.sublectId = sublectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTheme() {
        return lessonTheme;
    }

    public void setLessonTheme(String lessonTheme) {
        this.lessonTheme = lessonTheme;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
