package com.anthem.logic;

import com.anthem.db.model.RuleEntity;
import com.anthem.db.model.StorageEntity;
import com.anthem.db.model.UserActionsEntity;
import com.anthem.db.respository.RuleRepository;
import com.anthem.db.respository.StorageRepository;
import com.anthem.db.respository.UserActionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Component
public class RulesEngine {


    private final Logger logger = LoggerFactory.getLogger(RulesEngine.class.getName());
    private static String LAST_HANDLED_USER_ACTION="LastHandledUserActions";

    @Autowired
    private  List<RuleHandler> handlersList;
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private UserActionsRepository userActionsRepository;
    @Autowired
    private RuleRepository ruleRepository;



    private List<Integer> supportedRules= Arrays.asList(1,4);
    private Map<Integer,RuleHandler> rulesHandlerMap=new HashMap<>();

    @PostConstruct
    public void init(){
        handlersList.forEach(ruleHandler -> {
            if (supportedRules.contains(ruleHandler.getRuleId())){
                rulesHandlerMap.put(ruleHandler.getRuleId(),ruleHandler);
            }
        });
        logger.info("Filtered Supported rules {}",rulesHandlerMap);
    }


    private int getLastHandledUserActions(){
        StorageEntity storageEntity=storageRepository.findByKey(LAST_HANDLED_USER_ACTION);
        if(storageEntity!=null){
            return Integer.parseInt(storageEntity.getValue());
        }else{
            return 0;
        }
    }

    private void updateLastHandledUserActions(int id){
        StorageEntity storageEntity=new StorageEntity();
        storageEntity.setKey(LAST_HANDLED_USER_ACTION);
        storageEntity.setValue(Integer.toString(id));
        storageRepository.save(storageEntity);
        logger.info("updateLastHandledUserActions {}",id);
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}" ,initialDelayString ="${fixedDelay.delay.milliseconds}" )
    public void handleInterval5Min(){
        logger.info("handleInterval5Min");
        List<UserActionsEntity> list=userActionsRepository.getAllByIdIsGreaterThan(getLastHandledUserActions());
        if (list.isEmpty()){
            logger.info("no new data to handle");
        }
        List<Integer> filteredRulesByPriorityToHandleList = getFilteredRulesByPriorityToHandle();
        for (UserActionsEntity userActionsEntity:list){
            logger.info("handling  {}",userActionsEntity.toString());
            for(Integer ruleId : filteredRulesByPriorityToHandleList){
                logger.info("passing to Handler of ruleId {}",ruleId);
                rulesHandlerMap.get(ruleId).handleIncomingUserAction(userActionsEntity);
            }
            updateLastHandledUserActions(userActionsEntity.getId());
        }
    }

    @Scheduled(cron = "${cron.expression}")
    public void handleIntervalEvery24Hour(){
        logger.info("handleIntervalEvery24Hour");
        List<Integer> filteredRulesByPriorityToHandleList = getFilteredRulesByPriorityToHandle();
        logger.info("filteredRulesByPriorityToHandleList {}",filteredRulesByPriorityToHandleList);
        for(Integer ruleId : filteredRulesByPriorityToHandleList){
            logger.info("passing to Handler of ruleId {}",ruleId);
            // pass previous day
            rulesHandlerMap.get(ruleId).handleReportCycle(LocalDate.now().minusDays(1));
        }
    }

    private List<Integer> getFilteredRulesByPriorityToHandle(){
        List<RuleEntity> ruleEntityList=ruleRepository.findAll(Sort.by(Sort.Direction.ASC,"priority"));
        List<Integer> list=new ArrayList<>();
        ruleEntityList.forEach(ruleEntity -> {
            if(supportedRules.contains(ruleEntity.getId())){
                list.add(ruleEntity.getId());
            }
        });
        return list;
    }




}
