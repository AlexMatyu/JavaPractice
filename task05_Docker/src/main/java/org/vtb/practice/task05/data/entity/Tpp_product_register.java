package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tpp_product_register")
@NoArgsConstructor
public class Tpp_product_register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Long product_id;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "value")
    Tpp_ref_product_register_type type;

    private Long account;
    private String currency_code;
    private String state;
    private String account_number;

    public Tpp_product_register(Long product_id, Tpp_ref_product_register_type type, Long account, String currency_code, String state, String account_number) {
        this.product_id = product_id;
        this.type = type;
        this.account = account;
        this.currency_code = currency_code;
        this.state = state;
        this.account_number = account_number;
    }
}
