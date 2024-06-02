package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;

public record Account_pool_Dto(
        Integer id,
        @Size(min = 0, max = 50)
        String branch_code,
        @Size(min = 0, max = 30)
        String currency_code,
        @Size(min = 0, max = 50)
        String mdm_code,
        @Size(min = 0, max = 30)
        String priority_code,
        @Size(min = 0, max = 30)
        String registry_type_code
) {
}
