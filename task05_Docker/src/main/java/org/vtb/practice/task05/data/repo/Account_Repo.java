package org.vtb.practice.task05.data.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vtb.practice.task05.data.entity.Account;

import java.util.List;

public interface Account_Repo extends JpaRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query(value = "select id, account_number from account where account_pool_id =?1 ", nativeQuery = true)
    List<String[]> findByParam(Integer account_pool_id);
}
