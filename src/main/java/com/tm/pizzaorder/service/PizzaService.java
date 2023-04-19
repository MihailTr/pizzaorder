package com.tm.pizzaorder.service;

import com.tm.pizzaorder.model.Pizza;
import com.tm.pizzaorder.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepo;

    public PizzaService(PizzaRepository pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }


    public Pizza create(Pizza pizza) {
        try {
            return pizzaRepo.save(pizza);
        }catch (RuntimeException e){
            throw new IllegalArgumentException("Pizza Null");
        }
    }

    public Pizza readById(Long id) {
        final Optional<Pizza> optional = pizzaRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new IllegalArgumentException("Pizza Null");
    }

    public void delete(Long id) {
        final Pizza pizza = readById(id);
        if (pizza != null) {
            pizzaRepo.delete(pizza);
        } else {
            throw new IllegalArgumentException("delete error");
        }
    }

    public List<Pizza> findAll() {
        return pizzaRepo.findAll();
    }
}
