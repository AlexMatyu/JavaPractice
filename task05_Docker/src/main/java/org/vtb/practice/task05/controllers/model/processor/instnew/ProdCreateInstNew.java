package org.vtb.practice.task05.controllers.model.processor.instnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.data.entity.Tpp_product;
import org.vtb.practice.task05.data.repo.Tpp_product_Repo;

import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlInstNew")
@Order(5)
public class ProdCreateInstNew implements UnaryOperator<CorpSettlInstanceBody> {
    @Autowired
    Tpp_product_Repo productRepo;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 5: " + this.getClass().getSimpleName());

        Tpp_product product = new Tpp_product(
                corpSettlInstanceBody.getProductType()
                , corpSettlInstanceBody.getContractNumber()
                , corpSettlInstanceBody.getPriority()
                , corpSettlInstanceBody.getInterestRatePenalty()
                , corpSettlInstanceBody.getMinimalBalance()
                , corpSettlInstanceBody.getThresholdAmount()
                , corpSettlInstanceBody.getAccountingDetails()
                , corpSettlInstanceBody.getRateType()
                , corpSettlInstanceBody.getTaxPercentageRate()
        );

        product = productRepo.save(product);
        corpSettlInstanceBody.setInstanceId(product.getId());
        return corpSettlInstanceBody;
    }
}