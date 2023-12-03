package com.baum.canteenApp.response;

import java.time.LocalDateTime;
import java.util.List;

public record BuyResponse
        (
                String buyId,
                LocalDateTime localDateTime,
                CustomerResponse customerResponse,
                List<ProductWithCustomerResponse> productWithCustomerResponses
        ) {
}
