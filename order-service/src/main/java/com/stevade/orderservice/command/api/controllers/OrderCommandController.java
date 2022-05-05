package com.stevade.orderservice.command.api.controllers;

import com.stevade.orderservice.command.api.command.CreateOrderCommand;
import com.stevade.orderservice.command.api.models.OrderRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("")
    public String createOrder(@RequestBody OrderRestModel orderRestModel){
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .addressId(orderRestModel.getAddressId())
                .productId(orderRestModel.getProductId())
                .orderId(UUID.randomUUID().toString())
                .userId(orderRestModel.getUserId())
                .quantity(orderRestModel.getQuantity())
                .orderStatus("CREATED")
                .build();

        return commandGateway.sendAndWait(createOrderCommand);
    }
}
