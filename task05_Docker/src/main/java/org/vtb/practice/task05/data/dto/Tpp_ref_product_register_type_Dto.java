package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;
import org.vtb.practice.task05.data.entity.Tpp_ref_account_type;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_class;

import java.sql.Timestamp;

public record Tpp_ref_product_register_type_Dto(
        Integer internal_id,
        @Size(min = 0, max = 100)
        String value,
        @Size(min = 0, max = 100)
        String register_type_name,
        Tpp_ref_product_class product_class_code,
        Timestamp register_type_start_date,
        Timestamp register_type_end_date,
        Tpp_ref_account_type account_type
) {
}
