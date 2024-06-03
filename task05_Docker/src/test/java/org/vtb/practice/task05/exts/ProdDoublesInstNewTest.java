package org.vtb.practice.task05.exts;

import org.vtb.practice.task05.controllers.model.processor.instnew.ProdDoublesInstNew;
import org.vtb.practice.task05.data.repo.Tpp_product_Repo;

public class ProdDoublesInstNewTest extends ProdDoublesInstNew {
    public ProdDoublesInstNewTest(Tpp_product_Repo productRepo) {
        super.productRepo = productRepo;
    }
}
