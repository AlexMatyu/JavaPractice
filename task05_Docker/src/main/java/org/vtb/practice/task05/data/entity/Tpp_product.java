package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_product")
@NoArgsConstructor
public class Tpp_product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agreement> agreementList = new ArrayList<>();

    private Long product_code_id;
    private Long client_id;
    private String type;
    private String number;
    private Long priority;
    private Timestamp date_of_conclusion;
    private Timestamp start_date_time;
    private Timestamp end_date_time;
    private Long days;
    private Float penalty_rate;
    private Float nso;
    private Float threshold_amount;
    private String requisite_type;
    private String interest_rate_type;
    private Float tax_rate;
    private String reasone_close;
    private String state;

    public Tpp_product(String type, String number, Long priority, Float penalty_rate, Float nso, Float threshold_amount, String requisite_type, String interest_rate_type, Float tax_rate) {
        this.type = type;
        this.number = number;
        this.priority = priority;
        this.penalty_rate = penalty_rate;
        this.nso = nso;
        this.threshold_amount = threshold_amount;
        this.requisite_type = requisite_type;
        this.interest_rate_type = interest_rate_type;
        this.tax_rate = tax_rate;
    }
}
