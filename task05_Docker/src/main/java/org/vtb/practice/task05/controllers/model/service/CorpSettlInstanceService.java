package org.vtb.practice.task05.controllers.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.*;
import org.vtb.practice.task05.controllers.model.converter.CorpSettlInstanceBodyMapper;
import org.vtb.practice.task05.data.repo.Agreement_Repo;
import org.vtb.practice.task05.data.repo.Tpp_product_register_Repo;

import java.util.List;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class CorpSettlInstanceService implements CorpSettlInstanceServiceable{
    @Autowired
    @Qualifier("CorpSettlInstNew")
    private List<UnaryOperator<CorpSettlInstanceBody>> operatorsNew;

    @Autowired
    @Qualifier("CorpSettlInstUpd")
    private List<UnaryOperator<CorpSettlInstanceBody>> operatorsUpd;
    private CorpSettlInstanceBody instanceBody;
    private final CorpSettlInstanceBodyMapper instanceBodyMapper;
    @Autowired
    Tpp_product_register_Repo registerRepo;

    @Autowired
    Agreement_Repo agreementRepo;

    public ResponceInstMsg process(CorpSettlInstanceBodyDto instanceMsgIn) {
//        System.out.println("Instance Service : " + this.getClass().getSimpleName());

        instanceBody = instanceBodyMapper.dtoToModel(instanceMsgIn);

        if (instanceBody.getInstanceId() == null) operatorsNew.stream().forEach(x -> x.apply(instanceBody));
        else operatorsUpd.stream().forEach(x -> x.apply(instanceBody));

        instanceBody.setRegisterId(registerRepo.findAllByParam(instanceBody.getInstanceId()));
        instanceBody.setSupplementaryAgreementId(agreementRepo.findAllByParam(instanceBody.getInstanceId()));
        return new ResponceInstMsg(instanceBody.getInstanceId().toString(), instanceBody.getRegisterId(), instanceBody.getSupplementaryAgreementId());
    }
}
