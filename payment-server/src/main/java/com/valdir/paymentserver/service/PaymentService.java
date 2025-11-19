package com.valdir.paymentserver.service;

import com.valdir.paymentserver.model.Payment;

public interface PaymentService {
    void sendPayment(Payment payment);
}
