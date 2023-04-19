package com.tm.pizzaorder.repository;

import com.tm.pizzaorder.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
