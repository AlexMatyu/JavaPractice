package org.vtb.practice.task05.controllers.model.converter;

import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBodyDto;
import org.vtb.practice.task05.controllers.model.DataRecordDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorpSettlInstanceBodyMapper implements CorpSettlInstanceBodyMapperable {
    public CorpSettlInstanceBody dtoToModel(CorpSettlInstanceBodyDto instanceBodyDto) {
        CorpSettlInstanceBody instanceBody = new CorpSettlInstanceBody();
        instanceBody.setInstanceId(instanceBodyDto.instanceId());
        instanceBody.setProductType(instanceBodyDto.productType());
        instanceBody.setProductCode(instanceBodyDto.productCode());
        instanceBody.setRegisterType(instanceBodyDto.registerType());
        instanceBody.setMdmCode(instanceBodyDto.mdmCode());
        instanceBody.setContractNumber(instanceBodyDto.contractNumber());
        instanceBody.setContractDate(instanceBodyDto.contractDate());
        instanceBody.setPriority(instanceBodyDto.priority());
        instanceBody.setInterestRatePenalty(instanceBodyDto.interestRatePenalty());
        instanceBody.setMinimalBalance(instanceBodyDto.minimalBalance());
        instanceBody.setThresholdAmount(instanceBodyDto.thresholdAmount());
        instanceBody.setAccountingDetails(instanceBodyDto.accountingDetails());
        instanceBody.setRateType(instanceBodyDto.rateType());
        instanceBody.setTaxPercentageRate(instanceBodyDto.taxPercentageRate());
        instanceBody.setTechnicalOverdraftLimitAmount(instanceBodyDto.technicalOverdraftLimitAmount());
        instanceBody.setContractId(instanceBodyDto.contractId());
        instanceBody.setBranchCode(instanceBodyDto.branchCode());
        instanceBody.setIsoCurrencyCode(instanceBodyDto.isoCurrencyCode());
        instanceBody.setUrgencyCode(instanceBodyDto.urgencyCode());
        instanceBody.setReferenceCode(instanceBodyDto.referenceCode());
        instanceBody.setAdditionalPropertiesVip(
                instanceBodyDto.additionalPropertiesVip()
                        .data().stream()
                        .map(AdditionalPropertiesVipMapper::dtoToModel)
                        .collect(Collectors.toList())
        );
//        DataRecordDto recordDto = instanceBodyDto.additionalPropertiesVip();
        instanceBody.setInstanceArrangement(
                instanceBodyDto.instanceArrangement()
                        .stream()
                        .map(InstanceArrangementMapper::dtoToModel)
                        .collect(Collectors.toList())
        );
        return instanceBody;
    }
}

