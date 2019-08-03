package net.safedata.microservices.training.order.adapters;

import net.safedata.microservices.training.order.channels.InboundChannels;
import net.safedata.microservices.training.order.events.CustomerUpdatedEvent;
import net.safedata.microservices.training.order.message.CreateOrderMessage;
import net.safedata.microservices.training.order.marker.InboundAdapter;
import net.safedata.microservices.training.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InboundChannels.class)
public class MessageConsumer implements InboundAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private final OrderService orderService;

    @Autowired
    public MessageConsumer(final OrderService orderService) {
        this.orderService = orderService;
    }

    @StreamListener(InboundChannels.ORDER_CREATE)
    public void createOrder(final CreateOrderMessage createOrderMessage) {
        LOGGER.debug("Received a '{}' command, the ordered item is '{}', the customer ID is {}",
                createOrderMessage.getName(), createOrderMessage.getProductName(), createOrderMessage.getCustomerId());

        orderService.createOrder(createOrderMessage);
    }

    @StreamListener(InboundChannels.CUSTOMER_UPDATED)
    public void customerUpdated(final CustomerUpdatedEvent customerUpdatedEvent) {
        LOGGER.info("Received a '{}' event, the ID of the updated customer is {}",
                customerUpdatedEvent.getName(), customerUpdatedEvent.getCustomerId());

        orderService.customerUpdated(customerUpdatedEvent);
    }
}
