package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public record Tpp_product_Dto(
        Integer id,
        Long product_code_id,
        Long client_id,
        @Size(min = 0, max = 50)
        String type,
        @Size(min = 0, max = 50)
        String number,
        Long priority,
        Timestamp date_of_conclusion,
        Timestamp start_date_time,
        Timestamp end_date_time,
        Long days,
        Float penalty_rate,
        Float nso,
        Float threshold_amount,
        @Size(min = 0, max = 50)
        String requisite_type,
        @Size(min = 0, max = 50)
        String interest_rate_type,
        Float tax_rate,
        @Size(min = 0, max = 100)
        String reasone_close,
        @Size(min = 0, max = 50)
        String state
) {
}
