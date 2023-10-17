package net.safedata.microservices.training.shipping.inbound.adapter;

import net.safedata.microservices.training.marker.adapter.InboundAdapter;
import net.safedata.microservices.training.message.command.order.ShipOrderCommand;
import net.safedata.microservices.training.shipping.inbound.port.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer implements InboundAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private final ShippingService shippingService;

    @Autowired
    public MessageConsumer(final ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void orderCreated(final ShipOrderCommand shipOrderCommand) {
        LOGGER.debug("Received a '{}' event for the order {} of the customer {}...",
                shipOrderCommand.getName(), shipOrderCommand.getOrderId(), shipOrderCommand.getCustomerId());

        shippingService.shipOrder(shipOrderCommand);
    }
}
