package org.vtb.practice.task05.data.dto;

import jakarta.validation.constraints.Size;
import org.vtb.practice.task05.data.entity.Account_pool;

public record Account_Dto(
        Integer id,
        Account_pool account_pool_id,
        @Size(min = 0, max = 25)
        String account_number,
        Boolean bussy
) {
}
