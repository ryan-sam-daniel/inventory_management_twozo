package com.twozo.app.dao;

import com.twozo.app.model.Payment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public interface PaymentDbManager {
    int store(Payment payment, Connection connection);
    Payment checkExistence(int id);
}
