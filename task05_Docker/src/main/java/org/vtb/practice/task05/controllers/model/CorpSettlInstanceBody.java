package org.vtb.practice.task05.controllers.model;

import lombok.*;
import org.vtb.practice.task05.controllers.model.processor.RequiredParameter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CorpSettlInstanceBody {
    private Integer instanceId;									// Идентификатор экземпляра продукта
    @RequiredParameter
    private String productType;									// Тип Экземпляра Продукта
    @RequiredParameter
    private String productCode;									// Код продукта в каталоге продуктов
    @RequiredParameter
    private String registerType;								// Тип регистра
    @RequiredParameter
    private String mdmCode;										// Код Клиента (mdm)
    @RequiredParameter
    private String contractNumber;								// Номер договора
    @RequiredParameter
    private Timestamp contractDate;								// Дата заключения договора обслуживания
    private Long priority;									// Приоритет
    private Float interestRatePenalty;							// Штрафная процентная ставка
    private Float minimalBalance;								// Неснижаемый остаток
    private Float thresholdAmount;								// Пороговая сумма
    private String accountingDetails;							// Реквизиты выплаты
    private String rateType;									// Выбор ставки в зависимости от суммы
    private Float taxPercentageRate;							// Ставка налогообложения
    private Float technicalOverdraftLimitAmount;				// Сумма лимита технического овердрафта
    @RequiredParameter
    private Integer contractId;									// ID Договора
    @RequiredParameter
    private String branchCode;									// Код филиала
    @RequiredParameter
    private String isoCurrencyCode;								// Код валюты
    @RequiredParameter
    private String urgencyCode;									// Код срочности договора
    private Integer referenceCode;								// Код точки продаж
    private List<AdditionalPropertiesVip> additionalPropertiesVip = new ArrayList<>();		// массив дополнительных признаков для сегмента КИБ(VIP)
    @RequiredParameter
    private List<InstanceArrangement> instanceArrangement = new ArrayList<>();		// массив Доп.Соглашений

    private List<String> existRegisterTypes;
    private List<String> registerId;
    private List<String> supplementaryAgreementId;

    @Override
    public String toString() {
        return "CorpSettlInstanceBody{" +
                "instanceId=" + instanceId +
                ", productType='" + productType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", registerType='" + registerType + '\'' +
                ", mdmCode='" + mdmCode + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractDate='" + contractDate + '\'' +
                ", priority=" + priority +
                ", interestRatePenalty=" + interestRatePenalty +
                ", minimalBalance=" + minimalBalance +
                ", thresholdAmount=" + thresholdAmount +
                ", accountingDetails='" + accountingDetails + '\'' +
                ", rateType='" + rateType + '\'' +
                ", taxPercentageRate=" + taxPercentageRate +
                ", technicalOverdraftLimitAmount=" + technicalOverdraftLimitAmount +
                ", contractId=" + contractId +
                ", branchCode='" + branchCode + '\'' +
                ", isoCurrencyCode='" + isoCurrencyCode + '\'' +
                ", urgencyCode='" + urgencyCode + '\'' +
                ", referenceCode=" + referenceCode +
                ", additionalPropertiesVip=" + additionalPropertiesVip +
                ", instanceArrangement=" + instanceArrangement +
                ", registerId=" + registerId +
                ", supplementaryAgreementId=" + supplementaryAgreementId +
                '}';
    }
}
