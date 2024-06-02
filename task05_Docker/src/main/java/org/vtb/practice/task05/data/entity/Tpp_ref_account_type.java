package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_ref_account_type")
public class Tpp_ref_account_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;

    @OneToMany(mappedBy = "account_type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tpp_ref_product_register_type> tpp_ref_product_register_types = new ArrayList<>();

    @Column(unique = true)
    private String value;
}
