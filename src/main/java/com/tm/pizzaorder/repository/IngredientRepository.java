package com.tm.pizzaorder.repository;

import com.tm.pizzaorder.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
