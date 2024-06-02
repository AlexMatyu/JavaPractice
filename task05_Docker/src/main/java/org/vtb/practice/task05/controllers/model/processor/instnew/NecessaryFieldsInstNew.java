package org.vtb.practice.task05.controllers.model.processor.instnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.controllers.model.processor.NecessaryFields;

import java.util.function.UnaryOperator;

@Component("necessfieldsInstNew")
@Qualifier("CorpSettlInstNew")
@Order(1)
public class NecessaryFieldsInstNew implements UnaryOperator<CorpSettlInstanceBody> {
    @Autowired
    @Qualifier("necessfields")
    private NecessaryFields necessaryFields;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 1: " + this.getClass().getSimpleName());

        return (CorpSettlInstanceBody) necessaryFields.apply(corpSettlInstanceBody);
    }
}

