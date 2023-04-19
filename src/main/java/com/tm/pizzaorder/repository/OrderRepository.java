package com.tm.pizzaorder.repository;

import com.tm.pizzaorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
