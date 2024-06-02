package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_pool")
public class Account_pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "account_pool_id")
    List<Account> accountList = new ArrayList<>();

    @Column(name = "branch_code")
    private String branch_code;

    @Column(name = "currency_code")
    private String currency_code;

    @Column(name = "mdm_code")
    private String mdm_code;

    @Column(name = "priority_code")
    private String priority_code;

    @Column(name = "registry_type_code")
    private String registry_type_code;
}
