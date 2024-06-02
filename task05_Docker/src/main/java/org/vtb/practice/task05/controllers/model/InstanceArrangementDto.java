package org.vtb.practice.task05.controllers.model;

import java.sql.Timestamp;

public record InstanceArrangementDto(
        String generalAgreementId,                                // ID доп.Ген.соглашения
        String supplementaryAgreementId,                        // ID доп.соглашения
        String arrangementType,                                    // Тип соглашения
        Long shedulerJobId,                                    // Идентификатор периодичности учета
        String number,                                            // Номер ДС
        Timestamp openingDate,                                        // Дата начала сделки
        Timestamp closingDate,                                        // Дата окончания сделки
        Timestamp cancelDate,                                        // Дата отзыва сделки
        Long validityDuration,                                // Срок действия сделки
        String cancellationReason,                                // Причина расторжения
        String status,                                            // Состояние/статус
        Timestamp interestCalculationDate,                            // Начисление начинается с (дата
        Float interestRate,                                        // Процент начисления на остаток
        Float coefficient,                                        // Коэффициент
        String coefficientAction,                                // Действие коэффициента
        Float minimumInterestRate,                                // Минимум по ставке
        Float minimumInterestRateCoefficient,                    // Коэффициент по минимальной ставке
        String minimumInterestRateCoefficientAction,            // Действие коэффициента по минимальной ставке
        Float maximalnterestRate,                                // Максимум по ставке
        Float maximalnterestRateCoefficient,                    // Коэффициент по максимальной ставке
        String maximalnterestRateCoefficientAction                // Действие коэффициента по максимальной ставке
) {
}
