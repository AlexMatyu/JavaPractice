package org.vtb.practice.task05.controllers.model.processor.instnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.data.repo.Tpp_ref_product_register_type_Repo;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlInstNew")
@Order(4)
public class ProdRegTypeFindInstNew implements UnaryOperator<CorpSettlInstanceBody> {

    @Autowired
    Tpp_ref_product_register_type_Repo registerTypeRepo;

    @Override
    public CorpSettlInstanceBody apply(CorpSettlInstanceBody corpSettlInstanceBody) {
//        System.out.println("Step 4: " + this.getClass().getSimpleName());

        List<String> res = registerTypeRepo.findByParam(corpSettlInstanceBody.getProductCode(), "Клиентский");
        corpSettlInstanceBody.setExistRegisterTypes(res);

        if (res.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                    , "КодПродукта " + corpSettlInstanceBody.getProductCode()
                    + " не найдено в Каталоге продуктов tpp_ref_product_class");
        }

        return corpSettlInstanceBody;
    }
}