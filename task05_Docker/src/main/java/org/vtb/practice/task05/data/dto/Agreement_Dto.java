package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;
import org.vtb.practice.task05.data.entity.Tpp_product;

import java.sql.Timestamp;

public record Agreement_Dto(
        Integer id,
        Tpp_product product_id,
        @Size(min = 0, max = 50)
        String general_agreement_id,
        @Size(min = 0, max = 50)
        String supplementary_agreement_id,
        @Size(min = 0, max = 50)
        String arrangement_type,
        Long sheduler_job_id,
        @Size(min = 0, max = 50)
        String number,
        Timestamp opening_date,
        Timestamp closing_date,
        Timestamp cancel_date,
        Long validity_duration,
        @Size(min = 0, max = 100)
        String cancellation_reason,
        @Size(min = 0, max = 50)
        String status,
        Timestamp interest_calculation_date,
        Float interest_rate,
        Float coefficient,
        @Size(min = 0, max = 50)
        String coefficient_action,
        Float minimum_interest_rate,
        Float minimum_interest_rate_coefficient,
        @Size(min = 0, max = 50)
        String minimum_interest_rate_coefficient_action,
        Float maximal_interest_rate,
        Float maximal_interest_rate_coefficient,
        @Size(min = 0, max = 50)
        String maximal_interest_rate_coefficient_action

) {
}
