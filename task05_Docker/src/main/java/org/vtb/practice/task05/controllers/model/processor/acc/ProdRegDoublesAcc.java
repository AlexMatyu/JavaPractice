package org.vtb.practice.task05.controllers.model.processor.acc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.data.repo.Tpp_product_register_Repo;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlAcc")
@Order(2)
@RequiredArgsConstructor
public class ProdRegDoublesAcc implements UnaryOperator<CorpSettlAccountBody> {
    @Autowired
    Tpp_product_register_Repo productRegisterRepo;

    @Override
    public CorpSettlAccountBody apply(CorpSettlAccountBody corpSettlAccountBody) {
//        System.out.println("Step 2: " + this.getClass().getSimpleName());

        List<String[]> productRegisterParams = productRegisterRepo.findByParam(corpSettlAccountBody.getInstanceId());

        for (String[] productRegisterParam : productRegisterParams) {
            if (productRegisterParam[0].equals(corpSettlAccountBody.getRegistryTypeCode()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                        , "Параметр registryTypeCode тип регистра " + corpSettlAccountBody.getRegistryTypeCode()
                            + " уже существует для ЭП с ИД  " + productRegisterParam[1] + ".");
        }

        return corpSettlAccountBody;
    }
}
