package org.vtb.practice.task05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBodyDto;
import org.vtb.practice.task05.controllers.model.service.CorpSettlInstanceServiceable;

@RestController
@RequestMapping("/corporate-settlement-instance/")
public class CorporateSettlementInstanceController {
    @Autowired
    CorpSettlInstanceServiceable instanceService;

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> corporateSettlementInstance(@RequestBody CorpSettlInstanceBodyDto instanceMsgIn)
    {
//        System.out.println("/corporate-settlement-instance/create : " + this.getClass().getSimpleName());

        Object responce = instanceService.process(instanceMsgIn);

        return new ResponseEntity<>(responce, new HttpHeaders(), HttpStatus.CREATED);
    }
}
