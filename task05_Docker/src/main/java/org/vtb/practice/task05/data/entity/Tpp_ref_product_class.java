package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_ref_product_class")
public class Tpp_ref_product_class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;

    @OneToMany(mappedBy = "product_class_code", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Tpp_ref_product_register_type> tpp_ref_product_register_types = new ArrayList<>();

    @Column(unique = true)
    private String value;
    private String gbi_code;
    private String gbi_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
}