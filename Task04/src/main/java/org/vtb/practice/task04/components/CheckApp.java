package org.vtb.practice.task04.components;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@Component
@Order(2)
@LogTransformation
public class CheckApp implements UnaryOperator<AuthorizationModel> {
    @Override
    public AuthorizationModel apply(AuthorizationModel authorizationModel) {
        List<List<String>> authLists = new ArrayList<>(authorizationModel.getAuthorizFacts());
        List<List<String>> res = new ArrayList<>();
        List<String> templateApp = List.of("web", "mobile");

        for (List<String> lst : authLists) {
            if (lst.size() < 7 || (lst.size() == 7 && templateApp.contains(lst.get(6)))) {
                res.add(lst);
                continue;
            }

            List<String> authRecord = new ArrayList<>(lst.subList(0, 6));
            authRecord.add("other:" + (lst.size() < 7 ? "" : lst.get(6)));
            res.add(authRecord);
        }

        authorizationModel.setAuthorizFacts(res);
        return authorizationModel;
    }
}
