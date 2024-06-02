package org.vtb.practice.task05.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_register_type;

import java.util.List;

public interface Tpp_ref_product_register_type_Repo extends JpaRepository<Tpp_ref_product_register_type, Integer> {
    @Query(value = "select value from tpp_ref_product_register_type where product_class_code =?1 and account_type = ?2 ", nativeQuery = true)
    List<String> findByParam(String product_class_code, String account_type);
    Tpp_ref_product_register_type findFirstByValue(String value);
}
