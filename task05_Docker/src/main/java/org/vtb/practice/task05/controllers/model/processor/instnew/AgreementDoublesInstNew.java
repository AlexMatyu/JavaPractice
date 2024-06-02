package org.vtb.practice.task05.controllers.model.processor.instnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.controllers.model.processor.AgreementDoubles;

import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlInstNew")
@Order(3)
public class AgreementDoublesInstNew implements UnaryOperator<CorpSettlInstanceBody> {
    @Autowired
    @Qualifier("agrdoub")
    private AgreementDoubles agreementDoubles;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 3: " + this.getClass().getSimpleName());
        return agreementDoubles.apply(corpSettlInstanceBody);
    }
}