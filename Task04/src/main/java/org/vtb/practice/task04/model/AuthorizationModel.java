package org.vtb.practice.task04.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AuthorizationModel {
    @Getter
    @Setter
    private List<List<String>> authorizFacts;

    public AuthorizationModel(List<List<String>> authorizFacts) {
        this.authorizFacts = authorizFacts;
    }

    @Override
    public String toString() {
        return "AuthorizationModel{" +
                "authorizFacts=" + authorizFacts +
                '}';
    }
}
