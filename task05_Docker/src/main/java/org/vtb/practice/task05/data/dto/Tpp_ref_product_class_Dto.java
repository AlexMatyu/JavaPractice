package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;

public record Tpp_ref_product_class_Dto(
        Integer internal_id,
        @Size(min = 0, max = 100)
        String value,
        @Size(min = 0, max = 50)
        String gbi_code,
        @Size(min = 0, max = 100)
        String gbi_name,
        @Size(min = 0, max = 50)
        String product_row_code,
        @Size(min = 0, max = 100)
        String product_row_name,
        @Size(min = 0, max = 50)
        String subclass_code,
        @Size(min = 0, max = 100)
        String subclass_name
) {
}