package org.vtb.practice.task04.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;
import org.vtb.practice.task04.model.Login;
import org.vtb.practice.task04.model.User;
import org.vtb.practice.task04.repo.LoginRepo;
import org.vtb.practice.task04.repo.UserRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@Qualifier("db")
@LogTransformation
public class LocalDBWriter implements Consumer<AuthorizationModel> {
    UserRepo userRepo;

    LoginRepo loginRepo;

    public LocalDBWriter(@Autowired UserRepo userRepo, @Autowired LoginRepo loginRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    @Override
    public void accept(AuthorizationModel authorizationModel) {
        List<List<String>> authList = new ArrayList<>(authorizationModel.getAuthorizFacts());

        for (List<String> authRecord : authList) {
            List<User> users = userRepo.findByUsername(authRecord.get(0));

            User curUser;
            if (users.isEmpty())
                curUser = userRepo.save(new User(authRecord.get(0), authRecord.get(1) + " " + authRecord.get(2) + " " + authRecord.get(3)));
            else curUser = users.get(0);

            loginRepo.save(new Login(Timestamp.valueOf(authRecord.get(4) + " " + authRecord.get(5)), curUser, authRecord.get(6)));
        }
    }
}
