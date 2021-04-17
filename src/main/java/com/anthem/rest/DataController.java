package com.anthem.rest;


import com.anthem.dataobjects.*;
import com.anthem.services.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Validated
@RestController
public class DataController {

    private final Logger logger = LoggerFactory.getLogger(DataController.class.getName());



    @Autowired
    private DataService dataService;


    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Report>> getReports() {
        return new ResponseEntity<>(dataService.getReports(),HttpStatus.OK);
    }

    @GetMapping(value = "/action", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> action() {
        dataService.action();
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addUsers(@RequestBody @Valid UserListRequest userListRequest) {
        logger.info("User signIn :"+ userListRequest);
        dataService.addUsers(userListRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/useractions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addUserActions(@RequestBody @Valid UserActionsListRequest userActionsListRequest) {
        logger.info("User signIn :"+ userActionsListRequest);
        dataService.addUserActions(userActionsListRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping(value = "/type", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addTypes(@RequestBody @Valid TypeListRequest typeListRequest) {
        logger.info("User signIn :"+ typeListRequest);
        dataService.addTypes(typeListRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/rule", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addRules(@RequestBody @Valid RuleListRequest ruleListRequest) {
        logger.info("User signIn :"+ ruleListRequest);
        dataService.addRules(ruleListRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}