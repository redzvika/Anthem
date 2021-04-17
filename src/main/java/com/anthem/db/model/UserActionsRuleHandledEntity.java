package com.anthem.db.model;


import com.anthem.db.converters.EpochTimeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "USER_ACTION_RULE_HANDLED")
@ToString
public class UserActionsRuleHandledEntity {

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
    @Column(name = "user_actions_id", updatable = false)
    private int userActionsId;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_actions_id", nullable = false, insertable = false, updatable = false)
    private UserActionsEntity userActionsEntity;

    @Setter
    @Getter
    @Column(name = "rule_id", updatable = false)
    private int ruleId;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rule_id", nullable = false, insertable = false, updatable = false)
    private RuleEntity ruleEntity;

}


