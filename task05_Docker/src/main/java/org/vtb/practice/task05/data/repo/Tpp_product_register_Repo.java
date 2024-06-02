package org.vtb.practice.task05.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vtb.practice.task05.data.entity.Tpp_product_register;

import java.util.List;

public interface Tpp_product_register_Repo extends JpaRepository<Tpp_product_register, Integer> {
    @Query(value = "select type, id from tpp_product_register where product_id =?1 ", nativeQuery = true)
    List<String[]> findByParam(Integer product_id);

    @Query(value = "select id from tpp_product_register where product_id =?1 ", nativeQuery = true)
    List<String> findAllByParam(Integer product_id);
}