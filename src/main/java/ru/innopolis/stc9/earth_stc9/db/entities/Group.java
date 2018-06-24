package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Setter
    private Set<User> users;

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "GROUP_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSeq")
    public long getId() {
        return id;
    }

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> getUsers() {
        return users;
    }
}
