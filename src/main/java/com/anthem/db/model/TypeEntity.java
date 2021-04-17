package com.anthem.db.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "TYPES")
@ToString
public class TypeEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Setter
    @Getter
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "typeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserActionsEntity> userActions;

}


