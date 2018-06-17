package ru.innopolis.stc9.earth_stc9.pojo;

import java.sql.Date;

/**
 * @author Azat Timershin - created 03.06.2018
 */
public class UserForJournal {

    private int idJournal;
    private int idLesson;
    private int id;
    private String fullname;
    private String name;
    private String theme;
    private Date mark_date;
    private int mark;
    private int attendance;
    private String namegroup;

    public UserForJournal(int id, String fullname, String name, String theme, Date mark_date, int mark, int attendance,
                          String namegroup) {
        this.id = id;
        this.fullname = fullname;
        this.name = name;
        this.theme = theme;
        this.mark_date = mark_date;
        this.mark = mark;
        this.attendance = attendance;
        this.namegroup = namegroup;
    }

    public UserForJournal(int idJournal, String fullname, int id, String name, String theme, int idLesson, Date mark_date, int mark, int attendance, String namegroup) {
        this.idJournal = idJournal;
        this.fullname = fullname;
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.idLesson = idLesson;
        this.mark_date = mark_date;
        this.mark = mark;
        this.attendance = attendance;
        this.namegroup = namegroup;
    }


    public int getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(int idJournal) {
        this.idJournal = idJournal;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getMark_date() {
        return mark_date;
    }

    public void setMark_date(Date mark_date) {
        this.mark_date = mark_date;
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

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }
}