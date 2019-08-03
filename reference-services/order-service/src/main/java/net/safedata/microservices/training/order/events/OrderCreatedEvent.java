package net.safedata.microservices.training.order.events;

import java.time.LocalDateTime;

public class OrderCreatedEvent extends AbstractDomainEvent {

    private final long customerId;
    private final long orderId;
    private final LocalDateTime localDateTime;

    public OrderCreatedEvent(long eventId, final long customerId, long orderId) {
        super(eventId);
        this.customerId = customerId;
        this.orderId = orderId;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return null;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
