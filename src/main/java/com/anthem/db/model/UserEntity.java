package com.anthem.db.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@ToString
public class UserEntity {

    @Id
    @Column(name = "id", updatable = false)
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "LAST_NAME")
    private String lastName;

    @Setter
    @Getter
    @Column(name = "PROGRAM")
    private String program;

    @ToString.Exclude
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserActionsEntity> userActions;

}


