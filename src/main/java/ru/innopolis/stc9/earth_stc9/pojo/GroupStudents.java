package ru.innopolis.stc9.earth_stc9.pojo;

public class GroupStudents {
    private int idGS;
    private int studentIdGS;
    private int groupIdGS;

    public GroupStudents(int idGS, int studentIdGS, int groupIdGS) {

        this.idGS = idGS;
        this.studentIdGS = studentIdGS;
        this.groupIdGS = groupIdGS;
    }

    public int getIdGS() {
        return idGS;
    }

    public void setIdGS(int idGS) {
        this.idGS = idGS;
    }

    public int getStudentIdGS() {
        return studentIdGS;
    }

    public void setStudentIdGS(int studentIdGS) {
        this.studentIdGS = studentIdGS;
    }

    public int getGroupIdGS() {
        return groupIdGS;
    }

    public void setGroupIdGS(int groupIdGS) {
        this.groupIdGS = groupIdGS;
    }
}
