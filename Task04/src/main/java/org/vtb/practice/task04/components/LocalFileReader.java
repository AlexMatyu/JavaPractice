package org.vtb.practice.task04.components;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

@Component
@Qualifier("file")
@LogTransformation
public class LocalFileReader implements Supplier<AuthorizationModel> {
    private String path;

    public LocalFileReader(@Value("${spring.application.pathinput}") String path) {
        this.path = path;
    }

    @Override
    public AuthorizationModel get() {
        List<List<String>> res = new ArrayList<>();

        File fPath = new File(path);

        for (File file : fPath.listFiles()) {
            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String curString = sc.nextLine();
                    res.add(Arrays.stream(curString.split(" ")).toList());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return new AuthorizationModel(res);
    }
}
