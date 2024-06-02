package org.vtb.practice.task05.controllers.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ResponceInstMsg {
    @Getter
    @Setter
    private Data data;

    @Getter
    @Setter
    static class Data {
        private String instanceId;
        private List<String> registerId;
        private List<String> supplementaryAgreementId;
    }

    public ResponceInstMsg(String instanceId, List<String> registerId, List<String> supplementaryAgreementId) {
        this.data = new Data();
        this.data.setInstanceId(instanceId);
        this.data.setRegisterId(registerId);
        this.data.setSupplementaryAgreementId(supplementaryAgreementId);
    }
}