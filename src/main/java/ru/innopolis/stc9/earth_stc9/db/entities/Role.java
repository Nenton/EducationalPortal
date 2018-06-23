package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "roles")
@Entity
public class Role {
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "ROLE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    public long getId() {
        return id;
    }
}
