package org.vtb.practice.task05.controllers.model.processor.acc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_register_type;
import org.vtb.practice.task05.data.repo.Tpp_ref_product_register_type_Repo;

import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlAcc")
@Order(3)
@RequiredArgsConstructor
public class ProdRegTypeFindAcc implements UnaryOperator<CorpSettlAccountBody> {
    @Autowired
    Tpp_ref_product_register_type_Repo registerTypeRepo;

    @Override
    public CorpSettlAccountBody apply(CorpSettlAccountBody corpSettlAccountBody) {
//        System.out.println("Step 3 " + this.getClass().getSimpleName());
        Tpp_ref_product_register_type registerType = registerTypeRepo.findFirstByValue(corpSettlAccountBody.getRegistryTypeCode());
        if (registerType == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                    , "Код Продукта " + corpSettlAccountBody.getRegistryTypeCode()
                    + " не найдено в Каталоге продуктов Tpp_ref_product_register_type для данного типа Регистра");

        return corpSettlAccountBody;
    }
}
