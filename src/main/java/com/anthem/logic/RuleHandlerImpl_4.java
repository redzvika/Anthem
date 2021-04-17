package com.anthem.logic;

import com.anthem.db.model.DailyTrackingEntity;
import com.anthem.db.model.ReportsEntity;
import com.anthem.db.model.RuleEntity;
import com.anthem.db.model.UserActionsEntity;
import com.anthem.db.respository.DailyTrackingRepository;
import com.anthem.db.respository.RuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class RuleHandlerImpl_4  extends RuleHandlerImpl {

    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private DailyTrackingRepository dailyTrackingRepository;

    private RuleEntity ruleEntity;

    private static int COUNT_MIN=5;


    @Override
    public int getRuleId() {
        return 4;
    }

    @Override
    public void handleIncomingUserAction(UserActionsEntity userActionsEntity) {
        log.info("receiving userActionsEntity {} do nothing already handled by rule 1",userActionsEntity);
    }

    @Override
    public void handleReportCycle(LocalDate handlingDate) {
        if (ruleEntity==null){
            ruleEntity=ruleRepository.getOne(getRuleId());
        }
        List<DailyTrackingEntity> dailyTrackingEntityList =dailyTrackingRepository.findByDateAndCountIsGreaterThanEqual(handlingDate,COUNT_MIN);
        log.info("found {} for {} ",dailyTrackingEntityList,handlingDate);
        dailyTrackingEntityList.forEach(dailyTrackingEntity->{
            ReportsEntity reportsEntity = new ReportsEntity();
            reportsEntity.setUserId(dailyTrackingEntity.getUserId());
            reportsEntity.setDate(handlingDate);
            reportsEntity.setMessage(ruleEntity.getMessage());
            log.info("saving {}",reportsEntity);
            reportsRepository.save(reportsEntity);
        });
    }
}
