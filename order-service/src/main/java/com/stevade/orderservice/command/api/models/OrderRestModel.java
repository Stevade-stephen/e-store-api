package com.stevade.orderservice.command.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderRestModel {
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
}
