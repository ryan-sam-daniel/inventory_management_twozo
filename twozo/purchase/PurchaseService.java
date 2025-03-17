package com.twozo.purchase;

import java.time.LocalDateTime;

public interface PurchaseService {
    void addToList(String name, int quantity, LocalDateTime purLocalDateTime);
}
