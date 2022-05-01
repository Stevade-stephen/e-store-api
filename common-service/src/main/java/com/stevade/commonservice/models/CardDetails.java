package com.stevade.commonservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {
    private String name;
    private String cardNumber;
    private Integer validUntilMonth;
    private Integer validUntilYear;
    private Integer cvv;
}
