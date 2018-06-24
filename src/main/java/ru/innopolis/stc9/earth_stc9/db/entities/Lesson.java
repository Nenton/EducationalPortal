package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Setter
    private long id;
    @Getter
    @Setter
    private String theme;
    @Getter
    @Setter
    private Date date;
    @Setter
    private Group group;
    @Setter
    private Subject subject;
    @Setter
    private User teacher;

    public Lesson() {
    }

    @Id
    @SequenceGenerator(name = "lessonSeq", sequenceName = "LESSON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonSeq")
    public long getId() {
        return id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Group getGroup() {
        return group;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Subject getSubject() {
        return subject;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public User getTeacher() {
        return teacher;
    }
}
