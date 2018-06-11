package ru.innopolis.stc9.earth_stc9.pojo;

public class Subject {
    private int id;
    private String name;
    private String namegroup;

    public Subject(String name, String namegroup) {
        this.name = name;
        this.namegroup = namegroup;
    }
    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", namegroup='" + namegroup + '\'' +
                '}';
    }
}
