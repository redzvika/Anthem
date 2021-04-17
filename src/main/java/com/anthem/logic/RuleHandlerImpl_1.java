package com.anthem.logic;

import com.anthem.db.model.*;
import com.anthem.db.respository.*;
import com.anthem.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Component
public class RuleHandlerImpl_1 extends RuleHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(RuleHandlerImpl_1.class.getName());

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private DailyTrackingRepository dailyTrackingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RuleRepository ruleRepository;

    private RuleEntity ruleEntity;


    private static List<String> requiredTypes = Arrays.asList("steps", "medication", "Mind", "Water");
    private static Set<Integer> filteredTypeIdSet = new HashSet<>();

    @PostConstruct
    private void preparations() {


        if (filteredTypeIdSet.isEmpty()) {
            requiredTypes.forEach(requireType -> {
                TypeEntity entity = typeRepository.findByNameIgnoreCase(requireType);
                if (entity != null) {
                    filteredTypeIdSet.add(entity.getId());
                }
            });
        }
        logger.info("FilterTypeSet {}", filteredTypeIdSet);


    }

    public RuleHandlerImpl_1() {
        super();

    }


    @Override
    public int getRuleId() {
        return 1;
    }

    @Override
    public void handleIncomingUserAction(UserActionsEntity userActionsEntity) {

        logger.info("receiving userActionsEntity {}", userActionsEntity);
        if (filteredTypeIdSet.contains(userActionsEntity.getTypeId())) {
            // get only the date.
            LocalDate localDate = ResponseUtils.convertToLocalDateViaSqlDate(new Date(userActionsEntity.getTime()));
            DailyTrackingEntity dailyTrackingEntity = dailyTrackingRepository.findByUserIdAndDate(userActionsEntity.getUserId(), localDate);
            if (dailyTrackingEntity != null) {
                dailyTrackingEntity.setCount(dailyTrackingEntity.getCount() + 1);
            } else {
                dailyTrackingEntity = new DailyTrackingEntity();
                dailyTrackingEntity.setDate(localDate);
                dailyTrackingEntity.setUserId(userActionsEntity.getUserId());
                dailyTrackingEntity.setCount(1);
            }
            dailyTrackingRepository.save(dailyTrackingEntity);
            logger.info("handleIncomingUserAction  updateDailTracking {}", dailyTrackingEntity);
        } else {
            logger.info("type id {} not in filter", typeRepository.getOne(userActionsEntity.getTypeId()).getName());
        }
        updateFinishedHandlingIncomingUserAction(userActionsEntity);
    }

    @Override
    public void handleReportCycle(LocalDate handlingDate) {
        List<UserEntity> userList = userRepository.getUserNotInDailyForDate(handlingDate);
        if (ruleEntity == null) {
            ruleEntity = ruleRepository.getOne(getRuleId());
        }
        String ruleMessage = ruleEntity.getMessage();
        userList.forEach(userEntity -> {
            ReportsEntity reportsEntity = new ReportsEntity();
            reportsEntity.setUserId(userEntity.getId());
            reportsEntity.setDate(handlingDate);
            reportsEntity.setMessage(ruleMessage);
            reportsRepository.save(reportsEntity);
        });
    }
}
