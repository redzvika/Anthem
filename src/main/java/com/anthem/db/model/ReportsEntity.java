package com.anthem.db.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "REPORTS")
@ToString
public class ReportsEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Setter
    @Getter
    @Column(name = "user_id")
    private int userId;

    @Setter
    @Getter
    @Column(name = "date")
    private LocalDate date;

    @Setter
    @Getter
    @Column(name = "message")
    private String message;
}


