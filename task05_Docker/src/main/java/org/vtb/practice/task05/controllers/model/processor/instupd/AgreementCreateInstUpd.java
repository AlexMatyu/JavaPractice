package org.vtb.practice.task05.controllers.model.processor.instupd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.controllers.model.InstanceArrangement;
import org.vtb.practice.task05.data.entity.Agreement;
import org.vtb.practice.task05.data.entity.Tpp_product;
import org.vtb.practice.task05.data.repo.Agreement_Repo;
import org.vtb.practice.task05.data.repo.Tpp_product_Repo;

import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlInstUpd")
@Order(4)
public class AgreementCreateInstUpd implements UnaryOperator<CorpSettlInstanceBody> {
    @Autowired
    Tpp_product_Repo productRepo;

    @Autowired
    Agreement_Repo agreementRepo;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 4: " + this.getClass().getSimpleName());

        for (InstanceArrangement instanceArrangement : corpSettlInstanceBody.getInstanceArrangement()) {

            Tpp_product product = productRepo.findById(corpSettlInstanceBody.getInstanceId()).orElse(null);
            Agreement agreement = new Agreement(
                    product
                    , instanceArrangement.getGeneralAgreementId()
                    , instanceArrangement.getSupplementaryAgreementId()
                    , instanceArrangement.getArrangementType()
                    , instanceArrangement.getShedulerJobId()
                    , instanceArrangement.getNumber()
                    , instanceArrangement.getOpeningDate()
                    , instanceArrangement.getClosingDate()
                    , instanceArrangement.getCancelDate()
                    , instanceArrangement.getValidityDuration()
                    , instanceArrangement.getCancellationReason()
                    , instanceArrangement.getStatus()
                    , instanceArrangement.getInterestCalculationDate()
                    , instanceArrangement.getInterestRate()
                    , instanceArrangement.getCoefficient()
                    , instanceArrangement.getCoefficientAction()
                    , instanceArrangement.getMinimumInterestRate()
                    , instanceArrangement.getMinimumInterestRateCoefficient()
                    , instanceArrangement.getMinimumInterestRateCoefficientAction()
                    , instanceArrangement.getMaximalnterestRate()
                    , instanceArrangement.getMaximalnterestRateCoefficient()
                    , instanceArrangement.getMaximalnterestRateCoefficientAction()
            );

            agreementRepo.save(agreement);
        }

        return corpSettlInstanceBody;
    }
}
