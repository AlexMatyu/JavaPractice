package org.vtb.practice.task05;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBody;
import org.vtb.practice.task05.controllers.model.InstanceArrangement;
import org.vtb.practice.task05.controllers.model.processor.acc.NecessaryFieldsAcc;
import org.vtb.practice.task05.controllers.model.processor.acc.ProdRegDoublesAcc;
import org.vtb.practice.task05.controllers.model.processor.acc.ProdRegTypeFindAcc;
import org.vtb.practice.task05.controllers.model.processor.instnew.NecessaryFieldsInstNew;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_register_type;
import org.vtb.practice.task05.data.repo.Tpp_product_register_Repo;
import org.vtb.practice.task05.data.repo.Tpp_ref_product_register_type_Repo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PacketInjection.class})
@SpringBootApplication(scanBasePackages = "org.vtb.practice.task05")
public class ApplicationTest {
    @BeforeEach
    void init_data() {
        accountBody.setInstanceId(1);
        accountBody.setRegistryTypeCode("03.012.002_47533_ComSoLd");

        instanceNewBody.setInstanceId(1);
        instanceNewBody.setProductType("typeProduct");
        instanceNewBody.setProductCode("03.012.002");
        instanceNewBody.setRegisterType("registrType");
        instanceNewBody.setMdmCode("15");
        instanceNewBody.setContractNumber("dogNum123");
        instanceNewBody.setContractDate(Timestamp.valueOf("2012-12-12 12:12:12"));
        instanceNewBody.setPriority(0L);
        instanceNewBody.setInterestRatePenalty((float) 23.45);
        instanceNewBody.setMinimalBalance((float) 23.45);
        instanceNewBody.setThresholdAmount((float) 23.45);
        instanceNewBody.setAccountingDetails("accDetails");
        instanceNewBody.setRateType("rateType");
        instanceNewBody.setTaxPercentageRate((float) 23.45);
        instanceNewBody.setContractId(123456);
        instanceNewBody.setBranchCode("0022");
        instanceNewBody.setIsoCurrencyCode("800");
        instanceNewBody.setUrgencyCode("00");

        InstanceArrangement instanceArrangementNew = new InstanceArrangement();
        instanceArrangementNew.setNumber("num456");
        instanceArrangementNew.setOpeningDate(Timestamp.valueOf("2012-12-12 12:12:12"));

        instanceNewBody.setInstanceArrangement(List.of(instanceArrangementNew));

        instanceUpdBody.setInstanceId(1);
        instanceUpdBody.setProductType("typeProduct");
        instanceUpdBody.setProductCode("03.012.002");
        instanceUpdBody.setRegisterType("registrType");
        instanceUpdBody.setMdmCode("15");
        instanceUpdBody.setContractNumber("dogNum123");
        instanceUpdBody.setContractDate(Timestamp.valueOf("2012-12-12 12:12:12"));
        instanceUpdBody.setPriority(0L);
        instanceUpdBody.setInterestRatePenalty((float) 23.45);
        instanceUpdBody.setMinimalBalance((float) 23.45);
        instanceUpdBody.setThresholdAmount((float) 23.45);
        instanceUpdBody.setAccountingDetails("accDetails");
        instanceUpdBody.setRateType("rateType");
        instanceUpdBody.setTaxPercentageRate((float) 23.45);
        instanceUpdBody.setContractId(123456);
        instanceUpdBody.setBranchCode("0022");
        instanceUpdBody.setIsoCurrencyCode("800");
        instanceUpdBody.setUrgencyCode("00");

        InstanceArrangement instanceArrangementUpd = new InstanceArrangement();
        instanceArrangementUpd.setNumber("num456");
        instanceArrangementUpd.setOpeningDate(Timestamp.valueOf("2012-12-12 12:12:12"));

        instanceUpdBody.setInstanceArrangement(List.of(instanceArrangementUpd));
    }

    CorpSettlAccountBody accountBody = new CorpSettlAccountBody();
    CorpSettlInstanceBody instanceNewBody = new CorpSettlInstanceBody();
    CorpSettlInstanceBody instanceUpdBody = new CorpSettlInstanceBody();

    @Test
    @DisplayName("Account 1. Checking necessary fields")
    void testAccountNecessaryFields(@Autowired NecessaryFieldsAcc necessaryFieldsAcc) {
        accountBody.setInstanceId(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> necessaryFieldsAcc.apply(accountBody));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getStatus());

        accountBody.setInstanceId(1);
        Assertions.assertDoesNotThrow(() -> necessaryFieldsAcc.apply(accountBody));
    }

    @Test
    @DisplayName("Account 2. Checking product register doubles")
    void testAccountProdRegisterDoubles(@Autowired ProdRegDoublesAcc prodRegDoublesAcc) {
        Tpp_product_register_Repo productRegisterRepo = mock(Tpp_product_register_Repo.class);

        List<String[]> resDouble = new ArrayList<>();
        resDouble.add(new String[]{"03.012.002_47533_ComSoLd", "1"});
        when(productRegisterRepo.findByParam(1)).thenReturn(resDouble);

        Executable executable = () -> prodRegDoublesAcc.apply(accountBody);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getStatus());

        List<String[]> resUniq = new ArrayList<>();
        resUniq.add(new String[]{"03.012.002_47533_ComSoLd_Uniq", "2"});
        when(productRegisterRepo.findByParam(2)).thenReturn(resUniq);
        accountBody.setInstanceId(2);

        Assertions.assertDoesNotThrow(() -> prodRegDoublesAcc.apply(accountBody));
    }

    @Test
    @DisplayName("Account 3. Register type exist")
    void testAccountRegisterTypeExist(@Autowired ProdRegTypeFindAcc regTypeFindAcc) {
        Tpp_ref_product_register_type_Repo registerTypeRepo = mock(Tpp_ref_product_register_type_Repo.class);
        when(registerTypeRepo.findFirstByValue("xxx_03.012.002_47533_ComSoLd_xxx")).thenReturn(null);
        when(registerTypeRepo.findFirstByValue("03.012.002_47533_ComSoLd")).thenReturn(new Tpp_ref_product_register_type());

        Assertions.assertDoesNotThrow(() -> regTypeFindAcc.apply(accountBody));

        accountBody.setRegistryTypeCode("xxx_03.012.002_47533_ComSoLd_xxx");

        Executable executable = () -> regTypeFindAcc.apply(accountBody);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, executable);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), exception.getBody().getStatus());
    }

    @Test
    @DisplayName("Instance New 1. Checking necessary fields")
    void testInstNewnecFields(@Autowired NecessaryFieldsInstNew necessaryFieldsInstNew) {
        instanceNewBody.setProductCode(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> necessaryFieldsInstNew.apply(instanceNewBody));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getBody().getStatus());

        instanceNewBody.setProductCode("03.012.002");
        Assertions.assertDoesNotThrow(() -> necessaryFieldsInstNew.apply(instanceNewBody));
    }
}
