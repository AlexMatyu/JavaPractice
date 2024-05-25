package org.vtb.practice.task04.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@Component
@Order(3)
@LogTransformation
public class CheckDate implements UnaryOperator<AuthorizationModel> {


    Consumer<AuthorizationModel> writer;

    public CheckDate(@Autowired
                     @Qualifier("file") Consumer<AuthorizationModel> writer) {
        this.writer = writer;
    }

    @Override
    public AuthorizationModel apply(AuthorizationModel authorizationModel) {
        List<List<String>> authLists = authorizationModel.getAuthorizFacts();

        List<List<String>> res = new ArrayList<>();
        List<List<String>> resReject = new ArrayList<>();

        for (int i = 0; i < authLists.size(); i++) {
            if (authLists.get(i).size() < 6
                    || authLists.get(i).get(4).isEmpty()
                    || authLists.get(i).get(5).isEmpty()
            ) {
                resReject.add(authLists.get(i));
            }
            else res.add(authLists.get(i));
        }

        authorizationModel.setAuthorizFacts(res);

        writer.accept(new AuthorizationModel(resReject));

        return authorizationModel;
    }
}