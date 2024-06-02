package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_ref_product_register_type")
public class Tpp_ref_product_register_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Tpp_product_register> tpp_product_registers = new ArrayList<>();

    @Column(unique = true)
    private String value;
    private String register_type_name;

    @ManyToOne
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private Tpp_ref_product_class product_class_code;

    private Timestamp register_type_start_date;
    private Timestamp register_type_end_date;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "value")
    private Tpp_ref_account_type account_type;
}
