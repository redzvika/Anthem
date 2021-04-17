package com.anthem.db.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "DAILY_TRACKING")
@ToString
public class DailyTrackingEntity {

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
    @Column(name = "count")
    private int count;
}


