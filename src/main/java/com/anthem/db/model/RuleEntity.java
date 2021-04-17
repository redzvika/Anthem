package com.anthem.db.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "RULES")
@Getter
@ToString
public class RuleEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(name = "priority")
    private int priority;
    @Setter
    @Column(name = "rule")
    private String rule;
    @Setter
    @Column(name = "message")
    private String message;

    @ToString.Exclude
    @OneToMany( mappedBy = "ruleEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@JoinColumn(name = "rule_id")
    private List<UserActionsRuleHandledEntity>  userActionsRuleHandledEntityList;
}


