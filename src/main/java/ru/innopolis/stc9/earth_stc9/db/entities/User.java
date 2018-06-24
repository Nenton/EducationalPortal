package ru.innopolis.stc9.earth_stc9.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Setter
    private long id;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;
    @Setter
    private Role role;
    @Getter
    @Setter
    private Date dateReg;
    @Getter
    @Setter
    private Date dateLast;
    @Getter
    @Setter
    private int enabled;
    @Setter
    private Set<Group> groups;

    public User() {
    }

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    public long getId() {
        return id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Role getRole() {
        return role;
    }

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public Set<Group> getGroups() {
        return groups;
    }
}
