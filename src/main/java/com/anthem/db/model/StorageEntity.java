package com.anthem.db.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "STORAGE")
@Getter
@ToString
public class StorageEntity {

    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Id
    @Setter
    @Column(name = "key")
    private String key;

    @Setter
    @Column(name = "value")
    private String value;

}


