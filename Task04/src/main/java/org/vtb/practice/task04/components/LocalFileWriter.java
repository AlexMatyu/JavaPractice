package org.vtb.practice.task04.components;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Component
@Qualifier("file")
@LogTransformation
public class LocalFileWriter implements Consumer<AuthorizationModel> {
    private String path;

    public LocalFileWriter(@Value("${spring.application.fileoutput}") String path) {
        this.path = path;
    }

    @Override
    public void accept(AuthorizationModel authorizationModel) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            for (List<String> str : authorizationModel.getAuthorizFacts()){
                fileWriter.write(String.join(" ", str) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
