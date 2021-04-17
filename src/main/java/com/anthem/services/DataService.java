package com.anthem.services;

import com.anthem.dataobjects.*;
import com.anthem.db.model.*;
import com.anthem.db.respository.*;
import com.anthem.logic.RulesEngine;
import com.anthem.rest.DataController;
import com.anthem.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {


    private final Logger logger = LoggerFactory.getLogger(DataController.class.getName());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActionsRepository userActionsRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RulesEngine rulesEngine;

    @Autowired
    private ReportsRepository reportsRepository;


    public List<Report> getReports(){
        List<ReportsEntity> reportsEntityList=reportsRepository.findAll();
        List<Report> reportList=new ArrayList<>();
        reportsEntityList.forEach(reportsEntity -> {
            UserEntity userEntity=userRepository.getOne(reportsEntity.getUserId());
            Report report=new Report();
            report.setId(reportsEntity.getId());
            report.setDate(reportsEntity.getDate());
            report.setName(userEntity.getFirstName()+" "+userEntity.getLastName());
            report.setMessage(reportsEntity.getMessage());
            reportList.add(report);
        });
        return reportList;
    }

    public void addUsers(UserListRequest userListRequest){
        for (User user : userListRequest.getUsers()) {
            UserEntity userEntity=new UserEntity();
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setProgram(user.getProgram());
            userRepository.save(userEntity);
        }
    }

    public void addUserActions(UserActionsListRequest userActionsListRequest){
        for (UserActions userAction : userActionsListRequest.getUserActions()) {
            UserActionsEntity userActionsEntity=new UserActionsEntity();
            userActionsEntity.setUserId(userAction.getUserId());
            if (userAction.getTypeName()!=null){
                TypeEntity typeEntity=typeRepository.findByNameIgnoreCase(userAction.getTypeName());
                if(typeEntity!=null){
                    userActionsEntity.setTypeId(typeEntity.getId());
                }else{
                    userActionsEntity.setTypeId(userAction.getTypeId());
                }
            }
            if (userAction.getRfc3339DateTime()!=null){
                userActionsEntity.setTime(ResponseUtils.parse(userAction.getRfc3339DateTime()).getTime());
            }else{
                userActionsEntity.setTime(System.currentTimeMillis());
            }
            userActionsEntity.setDescription(userAction.getDescription());

            userActionsRepository.save(userActionsEntity);
        }
    }

    public void addTypes(TypeListRequest typeListRequest){
        for (Type type : typeListRequest.getTypes()) {
            TypeEntity typeEntity=new TypeEntity();
            typeEntity.setName(type.getName());
            typeRepository.save(typeEntity);
        }
    }

    public void addRules(RuleListRequest ruleListRequest){
        for (Rule rule : ruleListRequest.getRules()) {
            RuleEntity ruleEntity=new RuleEntity();
            ruleEntity.setRule(rule.getRule());
            ruleEntity.setMessage(rule.getMessage());
            ruleEntity.setPriority(rule.getPriority());
            ruleRepository.save(ruleEntity);
        }
    }

    public void action(){

        //rulesEngine.handleInterval5Min();
        rulesEngine.handleIntervalEvery24Hour();

//        UserActionsRuleHandledEntity entity=new UserActionsRuleHandledEntity();
//        entity.setUserActionsId(1);
//        entity.setRuleId(44);
//        entity.setTime(System.currentTimeMillis());
//        userActionRuleHandledRepository.save(entity);
//        StorageEntity storageEntity=new StorageEntity();
//        storageEntity.setKey("lastLine");
//        storageEntity.setValue("7");
//
//        storageRepository.save(storageEntity);
//        StorageEntity s=storageRepository.findByKey("lastLine");
//        if (s!=null) {
//            s.getValue();
//        }
//        DailyTrackingEntity dailyTrackingEntity=new DailyTrackingEntity();
//
//        Optional<UserActionsEntity> userActionsEntity=userActionsRepository.findById(1);
//
//        Date d=new Date(userActionsEntity.get().getTime());
//
//
//
//        dailyTrackingEntity.setDate(convertToLocalDateViaSqlDate(d));
//        dailyTrackingEntity.setCount(0);
//
//
//        dailyTrackingRepository.save(dailyTrackingEntity);
    }



}
