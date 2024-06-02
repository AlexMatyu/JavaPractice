package org.vtb.practice.task05.controllers.model;

import lombok.Getter;
import lombok.Setter;
import org.vtb.practice.task05.controllers.model.processor.RequiredParameter;

import java.sql.Timestamp;

@Getter
@Setter
public class InstanceArrangement {
    private String generalAgreementId;								// ID доп.Ген.соглашения
    private String supplementaryAgreementId;						// ID доп.соглашения
    private String arrangementType;									// Тип соглашения
    private Long shedulerJobId;									// Идентификатор периодичности учета
    @RequiredParameter
    private String number;											// Номер ДС
    @RequiredParameter
    private Timestamp openingDate;					    			// Дата начала сделки
    private Timestamp closingDate;						    		// Дата окончания сделки
    private Timestamp cancelDate;						    		// Дата отзыва сделки
    private Long validityDuration;								// Срок действия сделки
    private String cancellationReason;								// Причина расторжения
    private String status;											// Состояние/статус
    private Timestamp interestCalculationDate;				    	// Начисление начинается с (дата
    private Float interestRate;										// Процент начисления на остаток
    private Float coefficient;										// Коэффициент
    private String coefficientAction;								// Действие коэффициента
    private Float minimumInterestRate;								// Минимум по ставке
    private Float minimumInterestRateCoefficient;					// Коэффициент по минимальной ставке
    private String minimumInterestRateCoefficientAction;			// Действие коэффициента по минимальной ставке
    private Float maximalnterestRate;								// Максимум по ставке
    private Float maximalnterestRateCoefficient;					// Коэффициент по максимальной ставке
    private String maximalnterestRateCoefficientAction;				// Действие коэффициента по максимальной ставке

    @Override
    public String toString() {
        return "InstanceArrangement{" +
                "generalAgreementId='" + generalAgreementId + '\'' +
                ", supplementaryAgreementId='" + supplementaryAgreementId + '\'' +
                ", arrangementType='" + arrangementType + '\'' +
                ", shedulerJobId=" + shedulerJobId +
                ", number='" + number + '\'' +
                ", openingDate='" + openingDate + '\'' +
                ", closingDate='" + closingDate + '\'' +
                ", cancelDate='" + cancelDate + '\'' +
                ", validityDuration=" + validityDuration +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", status='" + status + '\'' +
                ", interestCalculationDate='" + interestCalculationDate + '\'' +
                ", interestRate=" + interestRate +
                ", coefficient=" + coefficient +
                ", coefficientAction='" + coefficientAction + '\'' +
                ", minimumInterestRate=" + minimumInterestRate +
                ", minimumInterestRateCoefficient='" + minimumInterestRateCoefficient + '\'' +
                ", minimumInterestRateCoefficientAction='" + minimumInterestRateCoefficientAction + '\'' +
                ", maximalnterestRate=" + maximalnterestRate +
                ", maximalnterestRateCoefficient=" + maximalnterestRateCoefficient +
                ", maximalnterestRateCoefficientAction='" + maximalnterestRateCoefficientAction + '\'' +
                '}';
    }
}
