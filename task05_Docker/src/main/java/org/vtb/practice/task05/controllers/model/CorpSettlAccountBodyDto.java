package org.vtb.practice.task05.controllers.model;

public record CorpSettlAccountBodyDto(
        Integer instanceId,					// Идентификатор экземпляра продукта
        String registryTypeCode,			// Тип регистра
        String accountType,					// Тип счета
        String currencyCode,				// Код валюты
        String branchCode,					// Код филиала
        String priorityCode,				// Код срочности
        String mdmCode,						// Id клиента
        String clientCode,					// Код клиента
        String trainRegion,					// Регион принадлежности железной дороги
        String counter,						// Счетчик
        String salesCode					//Код точки продаж
) {
}
