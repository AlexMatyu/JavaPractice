package org.vtb.practice.task05.controllers.model.processor.instnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.data.repo.Tpp_product_Repo;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlInstNew")
@Order(2)
public class ProdDoublesInstNew implements UnaryOperator<CorpSettlInstanceBody> {
    @Autowired
    protected
    Tpp_product_Repo productRepo;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 2: " + this.getClass().getSimpleName());
        List<String> products = productRepo.findByParam(corpSettlInstanceBody.getContractNumber());

        if (!products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Параметр ContractNumber № договора " + corpSettlInstanceBody.getContractNumber()
                    + " уже существует для ЭП с ИД  " + products.get(0) + ".");
        }

        return corpSettlInstanceBody;
    }
}
