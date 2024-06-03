package org.vtb.practice.task05.exts;

import org.vtb.practice.task05.controllers.model.processor.instupd.ProdDoublesInstUpd;
import org.vtb.practice.task05.data.repo.Tpp_product_Repo;

public class ProdDoublesInstUpdTest extends ProdDoublesInstUpd {
    public ProdDoublesInstUpdTest(Tpp_product_Repo productRepo) {
        super.productRepo = productRepo;
    }
}
