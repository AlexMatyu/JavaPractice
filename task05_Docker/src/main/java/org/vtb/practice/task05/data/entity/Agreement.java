package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "agreement")
@NoArgsConstructor
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Tpp_product product_id;

    private String general_agreement_id;
    private String supplementary_agreement_id;
    private String arrangement_type;
    private Long sheduler_job_id;
    private String number;
    private Timestamp opening_date;
    private Timestamp closing_date;
    private Timestamp cancel_date;
    private Long validity_duration;
    private String cancellation_reason;
    private String status;
    private Timestamp interest_calculation_date;
    private Float interest_rate;
    private Float coefficient;
    private String coefficient_action;
    private Float minimum_interest_rate;
    private Float minimum_interest_rate_coefficient;
    private String minimum_interest_rate_coefficient_action;
    private Float maximal_interest_rate;
    private Float maximal_interest_rate_coefficient;
    private String maximal_interest_rate_coefficient_action;

    public Agreement(Tpp_product product_id, String general_agreement_id, String supplementary_agreement_id, String arrangement_type, Long sheduler_job_id, String number, Timestamp opening_date, Timestamp closing_date, Timestamp cancel_date, Long validity_duration, String cancellation_reason, String status, Timestamp interest_calculation_date, Float interest_rate, Float coefficient, String coefficient_action, Float minimum_interest_rate, Float minimum_interest_rate_coefficient, String minimum_interest_rate_coefficient_action, Float maximal_interest_rate, Float maximal_interest_rate_coefficient, String maximal_interest_rate_coefficient_action) {
        this.product_id = product_id;
        this.general_agreement_id = general_agreement_id;
        this.supplementary_agreement_id = supplementary_agreement_id;
        this.arrangement_type = arrangement_type;
        this.sheduler_job_id = sheduler_job_id;
        this.number = number;
        this.opening_date = opening_date;
        this.closing_date = closing_date;
        this.cancel_date = cancel_date;
        this.validity_duration = validity_duration;
        this.cancellation_reason = cancellation_reason;
        this.status = status;
        this.interest_calculation_date = interest_calculation_date;
        this.interest_rate = interest_rate;
        this.coefficient = coefficient;
        this.coefficient_action = coefficient_action;
        this.minimum_interest_rate = minimum_interest_rate;
        this.minimum_interest_rate_coefficient = minimum_interest_rate_coefficient;
        this.minimum_interest_rate_coefficient_action = minimum_interest_rate_coefficient_action;
        this.maximal_interest_rate = maximal_interest_rate;
        this.maximal_interest_rate_coefficient = maximal_interest_rate_coefficient;
        this.maximal_interest_rate_coefficient_action = maximal_interest_rate_coefficient_action;
    }
}
