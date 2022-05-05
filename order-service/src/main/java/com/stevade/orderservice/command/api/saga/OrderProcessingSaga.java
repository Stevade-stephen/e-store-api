package com.stevade.orderservice.command.api.saga;

import com.stevade.commonservice.commands.ValidatePaymentCommand;
import com.stevade.commonservice.models.User;
import com.stevade.commonservice.queries.GetUserPaymentDetailsQuery;
import com.stevade.orderservice.command.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event){
        log.info("OrderCreatedEvent in Saga for order with id : {}", event.getOrderId());

        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(event.getUserId());
        User user = null;

        try{
            user = queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();

        }catch (Exception e) {
            log.info(e.getMessage());
            //Start the compensating transaction.
        }

        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                .orderId(event.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .cardDetails(user.getCardDetails())
                .build();

        commandGateway.sendAndWait(validatePaymentCommand);

    }
}
