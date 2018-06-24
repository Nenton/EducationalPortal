package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "subjectSeq", sequenceName = "SUBJECT_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjectSeq")
    public long getId() {
        return id;
    }
}
