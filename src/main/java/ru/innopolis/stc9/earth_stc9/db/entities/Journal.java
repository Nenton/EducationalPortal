package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journals")
public class Journal {
    @Setter
    private long id;
    @Setter
    private User student;
    @Setter
    private Lesson lesson;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    private int mark;
    @Getter
    @Setter
    private int attendance;

    public Journal() {

    }

    @Id
    @SequenceGenerator(name = "journalSeq", sequenceName = "JOURNAL_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journalSeq")
    public long getId() {
        return id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public User getStudent() {
        return student;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Lesson getLesson() {
        return lesson;
    }
}
