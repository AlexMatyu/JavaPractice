package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;
import org.vtb.practice.task05.data.entity.Tpp_ref_product_register_type;

public record Tpp_product_register_Dto(
        Integer id,
        Long product_id,
        Tpp_ref_product_register_type type,
        Long account,
        @Size(min = 0, max = 30)
        String currency_code,
        @Size(min = 0, max = 50)
        String state,
        @Size(min = 0, max = 25)
        String account_number
) {
}
