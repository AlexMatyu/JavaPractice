package org.vtb.practice.task05.controllers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vtb.practice.task05.controllers.model.processor.RequiredParameter;
import org.vtb.practice.task05.data.entity.Tpp_product_register;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_register_type;

@NoArgsConstructor
@Getter
@Setter
public class CorpSettlAccountBody {
    @RequiredParameter
    private Integer instanceId;					// Идентификатор экземпляра продукта
    private String registryTypeCode;			// Тип регистра
    private String accountType;					// Тип счета
    private String currencyCode;				// Код валюты
    private String branchCode;					// Код филиала
    private String priorityCode;				// Код срочности
    private String mdmCode;						// Id клиента
    private String clientCode;					// Код клиента
    private String trainRegion;					// Регион принадлежности железной дороги
    private String counter;						// Счетчик
    private String salesCode;					// Код точки продаж
    private Tpp_ref_product_register_type registerType;
    private Tpp_product_register productRegister;

    @Override
    public String toString() {
        return "CorpSettlAccountBody{" +
                "instanceId=" + instanceId +
                ", registryTypeCode='" + registryTypeCode + '\'' +
                ", accountType='" + accountType + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", priorityCode='" + priorityCode + '\'' +
                ", mdmCode='" + mdmCode + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", trainRegion='" + trainRegion + '\'' +
                ", counter='" + counter + '\'' +
                ", salesCode='" + salesCode + '\'' +
                ", registerType=" + registerType +
                '}';
    }
}
