package org.vtb.practice.task05.exts;

import org.vtb.practice.task05.controllers.model.processor.AgreementDoubles;
import org.vtb.practice.task05.data.repo.Agreement_Repo;

public class AgreementDoublesTest extends AgreementDoubles {
    public AgreementDoublesTest(Agreement_Repo agreementRepo) {
        super.agreementRepo = agreementRepo;
    }
}
