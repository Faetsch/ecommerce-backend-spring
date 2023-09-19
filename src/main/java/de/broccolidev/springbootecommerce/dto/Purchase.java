package de.broccolidev.springbootecommerce.dto;

import de.broccolidev.springbootecommerce.entity.Address;
import de.broccolidev.springbootecommerce.entity.Customer;
import de.broccolidev.springbootecommerce.entity.Order;
import de.broccolidev.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
