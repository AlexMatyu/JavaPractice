package org.vtb.practice.task05.controllers.model.converter;

import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.InstanceArrangement;
import org.vtb.practice.task05.controllers.model.InstanceArrangementDto;

@Service
public class InstanceArrangementMapper implements InstanceArrangementMapperable {
    public static InstanceArrangement dtoToModel(InstanceArrangementDto instanceArrangementDto) {
        InstanceArrangement instanceArrangement = new InstanceArrangement();

        instanceArrangement.setGeneralAgreementId(instanceArrangementDto.generalAgreementId());
        instanceArrangement.setSupplementaryAgreementId(instanceArrangementDto.supplementaryAgreementId());
        instanceArrangement.setArrangementType(instanceArrangementDto.arrangementType());
        instanceArrangement.setShedulerJobId(instanceArrangementDto.shedulerJobId());
        instanceArrangement.setNumber(instanceArrangementDto.number());
        instanceArrangement.setOpeningDate(instanceArrangementDto.openingDate());
        instanceArrangement.setClosingDate(instanceArrangementDto.closingDate());
        instanceArrangement.setCancelDate(instanceArrangementDto.cancelDate());
        instanceArrangement.setValidityDuration(instanceArrangementDto.validityDuration());
        instanceArrangement.setCancellationReason(instanceArrangementDto.cancellationReason());
        instanceArrangement.setStatus(instanceArrangementDto.status());
        instanceArrangement.setInterestCalculationDate(instanceArrangementDto.interestCalculationDate());
        instanceArrangement.setInterestRate(instanceArrangementDto.interestRate());
        instanceArrangement.setCoefficient(instanceArrangementDto.coefficient());
        instanceArrangement.setCoefficientAction(instanceArrangementDto.coefficientAction());
        instanceArrangement.setMinimumInterestRate(instanceArrangementDto.minimumInterestRate());
        instanceArrangement.setMinimumInterestRateCoefficient(instanceArrangementDto.minimumInterestRateCoefficient());
        instanceArrangement.setMinimumInterestRateCoefficientAction(instanceArrangementDto.minimumInterestRateCoefficientAction());
        instanceArrangement.setMaximalnterestRate(instanceArrangementDto.maximalnterestRate());
        instanceArrangement.setMaximalnterestRateCoefficient(instanceArrangementDto.maximalnterestRateCoefficient());
        instanceArrangement.setMaximalnterestRateCoefficientAction(instanceArrangementDto.maximalnterestRateCoefficientAction());

        return instanceArrangement;
    }
}
