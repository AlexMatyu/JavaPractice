package org.vtb.practice.task05.controllers.model.processor.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.processor.NecessaryFields;

import java.util.function.UnaryOperator;

@Component("necessfieldsAcc")
@Qualifier("CorpSettlAcc")
@Order(1)
public class NecessaryFieldsAcc implements UnaryOperator<CorpSettlAccountBody> {
    @Autowired
    @Qualifier("necessfields")
    private NecessaryFields necessaryFields;

    @Override
    public CorpSettlAccountBody apply(CorpSettlAccountBody corpSettlAccountBody) {
//        System.out.println("Step 1: " + this.getClass().getSimpleName());
        return (CorpSettlAccountBody) necessaryFields.apply(corpSettlAccountBody);
    }
}
