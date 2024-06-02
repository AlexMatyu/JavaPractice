package org.vtb.practice.task05.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vtb.practice.task05.data.entity.Tpp_product;

import java.util.List;

public interface Tpp_product_Repo extends JpaRepository<Tpp_product, Integer> {
    @Query(value = "select id from tpp_product where number =?1 ", nativeQuery = true)
    List<String> findByParam(String number);

    @Query(value = "select id from tpp_product where id =?1 ", nativeQuery = true)
    List<String> findByIdent(Integer id);

}