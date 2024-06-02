package org.vtb.practice.task05.controllers.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBodyDto;
import org.vtb.practice.task05.controllers.model.ResponceAccMsg;
import org.vtb.practice.task05.controllers.model.converter.CorpSettlAccountBodyMapper;

import java.util.List;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class CorpSettlAccountService implements CorpSettlAccountServiceable {
    @Autowired
    private List<UnaryOperator<CorpSettlAccountBody>> operators;
    private CorpSettlAccountBody accountBody;
    private final CorpSettlAccountBodyMapper accountBodyMapper;

    public ResponceAccMsg process(CorpSettlAccountBodyDto accountMsgIn) {
//        System.out.println("Account Service : " + this.getClass().getSimpleName());

        accountBody = accountBodyMapper.dtoToModel(accountMsgIn);

        operators.stream().forEach(x -> x.apply(accountBody));
        return new ResponceAccMsg(accountBody.getProductRegister().getId());
    }
}
