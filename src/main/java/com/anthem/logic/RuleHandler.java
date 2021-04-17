package com.anthem.logic;

import com.anthem.db.model.UserActionsEntity;

import java.time.LocalDate;

public interface RuleHandler {

    public  int getRuleId();
    public  void handleIncomingUserAction(UserActionsEntity userActionsEntity);
    public  void handleReportCycle(LocalDate handlingDate);
}
