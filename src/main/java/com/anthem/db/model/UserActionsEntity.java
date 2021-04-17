package com.anthem.db.model;


import com.anthem.db.converters.EpochTimeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USER_ACTIONS")
@ToString
public class UserActionsEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;


    @Column(name = "time", updatable = false)
    @Convert(converter = EpochTimeConverter.class)
    @Setter
    @Getter
    private Long time;


    @Setter
    @Getter
    @Column(name = "user_id", updatable = false)
    private int userId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private UserEntity userEntity;


    @Getter
    @Setter
    @Column(name = "type_id", updatable = false)
    private int typeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", nullable = false, insertable = false, updatable = false)
    private TypeEntity typeEntity;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "userActionsEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@JoinColumn(name = "user_actions_id")
    private List<UserActionsRuleHandledEntity> userActionsRuleHandledEntityList;

}


