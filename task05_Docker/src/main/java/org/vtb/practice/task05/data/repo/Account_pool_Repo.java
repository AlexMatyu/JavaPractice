package org.vtb.practice.task05.data.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vtb.practice.task05.data.entity.Account_pool;

import java.util.List;

public interface Account_pool_Repo extends JpaRepository<Account_pool, Integer> {
    @Modifying
    @Transactional
    @Query(value = "select id from account_pool where branch_code =?1 " +
            "and currency_code =?2 " +
            "and mdm_code =?3 " +
            "and priority_code =?4 " +
            "and registry_type_code =?5 ", nativeQuery = true)
    List<Integer> findByParam(
            String branch_code,
            String currency_code,
            String mdm_code,
            String priority_code,
            String registry_type_code
    );
}