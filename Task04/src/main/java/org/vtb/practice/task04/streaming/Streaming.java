package org.vtb.practice.task04.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.vtb.practice.task04.logging.LogTransformation;
import org.vtb.practice.task04.model.AuthorizationModel;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Component
@LogTransformation("c:\\!Matyu\\Temp\\LogTransformation\\Streamig.log")
public class Streaming implements Streamable {
    @Autowired
    Supplier<AuthorizationModel> datareader;

    @Autowired
    List<UnaryOperator<AuthorizationModel>> operators;

    @Autowired @Qualifier("db")
    Consumer<AuthorizationModel> writer;

    public void run() {
        AuthorizationModel authorizationModel = datareader.get();
        operators.stream().forEach(x -> x.apply(authorizationModel));
        writer.accept(authorizationModel);
    }
}
