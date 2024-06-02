package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;

public record Tpp_ref_account_type_Dto(
        Integer internal_id,
        @Size(min = 0, max = 100)
        String value
) {
}
