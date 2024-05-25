package org.vtb.practice.task04.components;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Order(1)
@LogTransformation
public class CheckFIO implements UnaryOperator<AuthorizationModel> {
    @Override
    public AuthorizationModel apply(AuthorizationModel authorizationModel) {
        List<List<String>> authLists = new ArrayList<>(authorizationModel.getAuthorizFacts());
        List<List<String>> res = new ArrayList<>();

        for (List<String> lst : authLists) {
            if (lst.size() < 4) {
                res.add(lst);
                continue;
            }

            List<String> authRecord = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                if (i > 0 && i < 4)
                    authRecord.add(lst.get(i).isBlank() || lst.get(i).isEmpty()
                            ? lst.get(i)
                            : lst.get(i).toUpperCase().charAt(0) + lst.get(i).substring(1));
                else authRecord.add(lst.get(i));
            }
            res.add(authRecord);
        }

        authorizationModel.setAuthorizFacts(res);
        return authorizationModel;
    }
}
