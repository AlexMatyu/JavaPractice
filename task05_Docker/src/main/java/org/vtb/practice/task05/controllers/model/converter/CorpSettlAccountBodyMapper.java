package org.vtb.practice.task05.controllers.model.converter;

import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBodyDto;

@Service
public class CorpSettlAccountBodyMapper implements CorpSettlAccountBodyMapperable {
    public CorpSettlAccountBody dtoToModel(CorpSettlAccountBodyDto accountBodyDto) {
        CorpSettlAccountBody accountBody = new CorpSettlAccountBody();
        accountBody.setInstanceId(accountBodyDto.instanceId());
        accountBody.setRegistryTypeCode(accountBodyDto.registryTypeCode());
        accountBody.setAccountType(accountBodyDto.accountType());
        accountBody.setCurrencyCode(accountBodyDto.currencyCode());
        accountBody.setBranchCode(accountBodyDto.branchCode());
        accountBody.setPriorityCode(accountBodyDto.priorityCode());
        accountBody.setMdmCode(accountBodyDto.mdmCode());
        accountBody.setClientCode(accountBodyDto.clientCode());
        accountBody.setTrainRegion(accountBodyDto.trainRegion());
        accountBody.setCounter(accountBodyDto.counter());
        accountBody.setSalesCode(accountBodyDto.salesCode());
        return accountBody;
    }
}
