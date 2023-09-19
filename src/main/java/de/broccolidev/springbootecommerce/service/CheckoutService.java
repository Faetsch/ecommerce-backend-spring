package de.broccolidev.springbootecommerce.service;

import de.broccolidev.springbootecommerce.dto.Purchase;
import de.broccolidev.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
