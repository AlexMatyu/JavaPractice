package org.vtb.practice.task05.controllers.model.processor.acc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.State;
import org.vtb.practice.task05.data.entity.*;
import org.vtb.practice.task05.data.repo.Account_Repo;
import org.vtb.practice.task05.data.repo.Account_pool_Repo;
import org.vtb.practice.task05.data.repo.Tpp_product_register_Repo;
import org.vtb.practice.task05.data.repo.Tpp_ref_product_register_type_Repo;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Qualifier("CorpSettlAcc")
@Order(4)
@RequiredArgsConstructor
public class CreateProdRegAcc implements UnaryOperator<CorpSettlAccountBody> {
    @Autowired
    Account_Repo accountRepo;
    @Autowired
    Account_pool_Repo accountPoolRepo;
    @Autowired
    Tpp_product_register_Repo productRegisterRepo;
    @Autowired
    Tpp_ref_product_register_type_Repo registerTypeRepo;

    @Override
    public CorpSettlAccountBody apply(CorpSettlAccountBody corpSettlAccountBody) {
//        System.out.println("Step 4: " + this.getClass().getSimpleName());

        List<Integer> accountPools = accountPoolRepo.findByParam(
                corpSettlAccountBody.getBranchCode()
                , corpSettlAccountBody.getCurrencyCode()
                , corpSettlAccountBody.getMdmCode()
                , corpSettlAccountBody.getPriorityCode()
                , corpSettlAccountBody.getRegistryTypeCode()
        );

        Long accId = null;
        String accNum = null;

        if (!accountPools.isEmpty()) {
            List<String[]> res = accountRepo.findByParam(accountPools.get(0));

            if (!res.isEmpty()) {
                accId = Long.parseLong(res.get(0)[0]);
                accNum = res.get(0)[1];
            }
        }

        Tpp_ref_product_register_type registerType = registerTypeRepo.findFirstByValue(corpSettlAccountBody.getRegistryTypeCode());

        var productRegistr = new Tpp_product_register(
                Long.valueOf(corpSettlAccountBody.getInstanceId())
                , registerType
                , accId
                , corpSettlAccountBody.getCurrencyCode()
                , State.OPEN.getState()
                , accNum
        );

        Tpp_product_register registr = productRegisterRepo.save(productRegistr);
        corpSettlAccountBody.setProductRegister(registr);
        return corpSettlAccountBody;
    }
}
