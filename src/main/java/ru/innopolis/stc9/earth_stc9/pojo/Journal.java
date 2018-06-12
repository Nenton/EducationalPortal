package ru.innopolis.stc9.earth_stc9.pojo;
/*
 *** created by Azat Ti
 */

import java.sql.Date;

public class Journal {
    private int id;
    private int studentId;
    private int lessonId;
    private Date date;
    private int mark;
    private int attendance;

    public Journal(int studentId, int lessonId, Date date, int mark, int attendance) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.date = date;
        this.mark = mark;
        this.attendance = attendance;
    }

    public Journal(int id, int studentId, int lessonId, Date date, int mark, int attendance) {
        this.id = id;
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.date = date;
        this.mark = mark;
        this.attendance = attendance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
