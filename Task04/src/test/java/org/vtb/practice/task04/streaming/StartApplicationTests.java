package org.vtb.practice.task04.streaming;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.vtb.practice.task04.components.*;
import org.vtb.practice.task04.model.AuthorizationModel;
import org.vtb.practice.task04.model.Login;
import org.vtb.practice.task04.model.User;
import org.vtb.practice.task04.repo.LoginRepo;
import org.vtb.practice.task04.repo.UserRepo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Testcontainers
class StartApplicationTests {
    @Autowired
    LoginRepo loginRepo;

    @Autowired
    UserRepo userRepo;

    @Test
    @DisplayName("checking DB writer")
    void testDBWriter() {
        loginRepo.deleteAll();
        userRepo.deleteAll();

        List<List<String>> recs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
        ));

        AuthorizationModel authModel = new AuthorizationModel(recs);
        new LocalDBWriter(userRepo, loginRepo).accept(authModel);
        List<User> users = userRepo.findByUsername("Popov");
        List<Login> logins = loginRepo.findByApplication("web");

        Assertions.assertEquals(users.size(), 1);
        Assertions.assertEquals(users.get(0).getFio(), "Попов Поп Попович");

        Assertions.assertEquals(logins.size(), 1);
        Assertions.assertEquals(logins.get(0).getApplication(), "web");
    }

    @Test
    @DisplayName("checking application name change")
    void testCheckApp() {
        List<List<String>> recs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
                , Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 smthg".split(" ")).toList()
        ));
        List<List<String>> actualRecs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
                , Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 other:smthg".split(" ")).toList()
        ));

        AuthorizationModel authModel = new AuthorizationModel(recs);
        new CheckApp().apply(authModel);

        Assertions.assertLinesMatch(authModel.getAuthorizFacts().get(0), actualRecs.get(0));
        Assertions.assertLinesMatch(authModel.getAuthorizFacts().get(1), actualRecs.get(1));
    }

    @Test
    @DisplayName("checking for rejection of records by date and writing to/reading from a file")
    void testCheckDate() {
        List<List<String>> recs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
                , Arrays.stream("Popov Попов Поп Попович".split(" ")).toList()
        ));

        List<String> actualStr = Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList();
        List<String> actualRejStr = Arrays.stream("Popov Попов Поп Попович".split(" ")).toList();

        for (File fileToDelete : new File("C:\\!Matyu\\temp\\tstoutdata\\").listFiles())
            if (fileToDelete.isFile()) fileToDelete.delete();

        AuthorizationModel authModel = new AuthorizationModel(recs);
        CheckDate checkDate = new CheckDate(new LocalFileWriter("C:\\!Matyu\\temp\\tstoutdata\\output.txt"));
        checkDate.apply(authModel);

        Assertions.assertEquals(authModel.getAuthorizFacts().size(), 1);
        Assertions.assertLinesMatch(authModel.getAuthorizFacts().get(0), actualStr);

        AuthorizationModel actualRejAuthModel = new LocalFileReader("C:\\!Matyu\\temp\\tstoutdata\\").get();

        Assertions.assertEquals(actualRejAuthModel.getAuthorizFacts().size(), 1);
        Assertions.assertLinesMatch(actualRejAuthModel.getAuthorizFacts().get(0), actualRejStr);
    }

    @Test
    @DisplayName("checking FIO change")
    void testCheckFIO() {
        List<List<String>> recs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
                , Arrays.stream("Popov попов поп попович 2021-01-01 00:00:00 smthg".split(" ")).toList()
        ));
        List<List<String>> actualRecs = new ArrayList<>(List.of(
                Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 web".split(" ")).toList()
                , Arrays.stream("Popov Попов Поп Попович 2021-01-01 00:00:00 smthg".split(" ")).toList()
        ));

        AuthorizationModel authModel = new AuthorizationModel(recs);
        new CheckFIO().apply(authModel);

        Assertions.assertLinesMatch(authModel.getAuthorizFacts().get(0), actualRecs.get(0));
        Assertions.assertLinesMatch(authModel.getAuthorizFacts().get(1), actualRecs.get(1));
    }
}
