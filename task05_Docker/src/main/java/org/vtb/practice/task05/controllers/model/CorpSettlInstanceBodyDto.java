package org.vtb.practice.task05.controllers.model;

import java.sql.Timestamp;
import java.util.List;

public record CorpSettlInstanceBodyDto(
        Integer instanceId,                                    // Идентификатор экземпляра продукта
        String productType,                                    // Тип Экземпляра Продукта
        String productCode,                                    // Код продукта в каталоге продуктов
        String registerType,                                // Тип регистра
        String mdmCode,                                        // Код Клиента (mdm)
        String contractNumber,                                // Номер договора
        Timestamp contractDate,                            // Дата заключения договора обслуживания
        Long priority,                                    // Приоритет
        Float interestRatePenalty,                            // Штрафная процентная ставка
        Float minimalBalance,                                // Неснижаемый остаток
        Float thresholdAmount,                                // Пороговая сумма
        String accountingDetails,                            // Реквизиты выплаты
        String rateType,                                    // Выбор ставки в зависимости от суммы
        Float taxPercentageRate,                            // Ставка налогообложения
        Float technicalOverdraftLimitAmount,                // Сумма лимита технического овердрафта
        Integer contractId,                                    // ID Договора
        String branchCode,                                    // Код филиала
        String isoCurrencyCode,                                // Код валюты
        String urgencyCode,                                    // Код срочности договора
        Integer referenceCode,                                // Код точки продаж
        DataRecordDto additionalPropertiesVip,        // массив дополнительных признаков для сегмента КИБ(VIP)
        List<InstanceArrangementDto> instanceArrangement        // массив Доп.Соглашений
) {
}

