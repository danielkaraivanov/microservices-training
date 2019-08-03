package net.safedata.microservices.training.order.message;

import net.safedata.microservices.training.order.message.subscriber.Service;
import net.safedata.microservices.training.order.message.subscriber.Subscriber;

import java.util.Objects;

@Subscriber(Service.CUSTOMER_SERVICE)
public class CreateOrderCommand extends AbstractCommand {

    private static final String NAME = "CreateOrder";

    private final String productName;
    private final long productPrice;
    private final long customerId;

    public CreateOrderCommand(long messageId, String productName, long productPrice, long customerId) {
        super(messageId);
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public long getCustomerId() {
        return customerId;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderCommand that = (CreateOrderCommand) o;
        return productPrice == that.productPrice &&
                customerId == that.customerId &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, customerId);
    }
}
