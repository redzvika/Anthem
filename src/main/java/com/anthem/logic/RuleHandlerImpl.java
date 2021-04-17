package com.anthem.logic;

import com.anthem.db.model.UserActionsEntity;
import com.anthem.db.model.UserActionsRuleHandledEntity;
import com.anthem.db.respository.ReportsRepository;
import com.anthem.db.respository.UserActionRuleHandledRepository;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public abstract class RuleHandlerImpl implements RuleHandler {


    private final Logger logger = LoggerFactory.getLogger(RuleHandlerImpl.class.getName());
    @Autowired
    private UserActionRuleHandledRepository userActionRuleHandledRepository;

    @Autowired
    protected ReportsRepository reportsRepository;

    protected  void updateFinishedHandlingIncomingUserAction(UserActionsEntity userActionsEntity){
        logger.info("updateFinishedHandlingIncomingUserAction {} ",userActionsEntity);
        UserActionsRuleHandledEntity userActionsRuleHandledEntity=new UserActionsRuleHandledEntity();
        userActionsRuleHandledEntity.setRuleId(getRuleId());
        userActionsRuleHandledEntity.setUserActionsId(userActionsEntity.getId());
        userActionsRuleHandledEntity.setTime(System.currentTimeMillis());
        userActionRuleHandledRepository.save(userActionsRuleHandledEntity);
    }

    public abstract int getRuleId();
    public abstract void handleIncomingUserAction(UserActionsEntity userActionsEntity);
    public abstract void handleReportCycle(LocalDate handlingDate);



}
