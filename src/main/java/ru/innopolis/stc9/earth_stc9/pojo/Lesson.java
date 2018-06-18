package ru.innopolis.stc9.earth_stc9.pojo;

import java.util.Date;

public class Lesson {
    private int id;
    private String theme;
    private Date date;
    private Subject subject;
    private int subjectId;
    private User teacher;
    private int teacherId;
    private Group group;
    private int groupId;
    private String namesubject;


    public Lesson(int id, String theme, Date date, Subject subject, int subjectId, User teacher, int teacherId,
                  Group group, int groupId) {
        this.id = id;
        this.theme = theme;
        this.date = date;
        this.subject = subject;
        this.subjectId = subjectId;
        this.teacher = teacher;
        this.teacherId = teacherId;
        this.group = group;
        this.groupId = groupId;
    }

    public Lesson(int id, String theme, Date date, int subjectId, int teacherId, int groupId) {
        this.id = id;
        this.theme = theme;
        this.date = date;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupId = groupId;
    }

    public Lesson(String theme, Date date, int subjectId, int teacherId, int groupId) {
        this.theme = theme;
        this.date = date;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupId = groupId;
    }

    public Lesson(int id, String theme, String namesubject) {
        this.id = id;
        this.theme = theme;
        this.namesubject = namesubject;
    }



    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public User getTeacher() {
        return teacher;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getTheme() {
        return theme;
    }

    public Date getDate() {
        return date;
    }

    public Group getGroup() {
        return group;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getNamesubject() {
        return namesubject;
    }

    public void setNamesubject(String namesubject) {
        this.namesubject = namesubject;
    }
}
