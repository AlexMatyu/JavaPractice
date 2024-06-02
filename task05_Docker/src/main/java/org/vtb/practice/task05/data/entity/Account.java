package org.vtb.practice.task05.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id", referencedColumnName = "id")
    private Account_pool account_pool_id;

    private String account_number;
    private Boolean bussy;
}
