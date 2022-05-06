package com.stevade.paymentservice.command.api.aggregate;

import com.stevade.commonservice.commands.ValidatePaymentCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public PaymentAggregate() {
    }

    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        // TODO: 5/6/22 Validate the payment details
        // TODO: 5/6/22 Publish the payment processed event
        log.info("Executing ValidatePaymentCommand for " +
                "Order Id: {}, and Payment Id: {}",
                validatePaymentCommand.getOrderId(),
                validatePaymentCommand.getPaymentId());
        
    }
}
