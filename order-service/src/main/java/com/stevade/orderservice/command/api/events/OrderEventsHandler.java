package com.stevade.orderservice.command.api.events;

import com.stevade.orderservice.command.api.data.Order;
import com.stevade.orderservice.command.api.data.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public record OrderEventsHandler(OrderRepository orderRepository) {


    @EventHandler
    public void on (OrderCreatedEvent event){
        Order order = new Order();
        BeanUtils.copyProperties(event, order);
        orderRepository.save(order);
    }
}
