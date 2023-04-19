package com.tm.pizzaorder.service;

import com.tm.pizzaorder.model.Ingredient;
import com.tm.pizzaorder.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingRepo;

    public IngredientService(IngredientRepository ingRepo) {
        this.ingRepo = ingRepo;
    }

    public Ingredient create(Ingredient ingredient) {
        try {
            return ingRepo.save(ingredient);
        }catch (RuntimeException e){
            throw new IllegalArgumentException("Ingredient Null");
        }
    }

    public Ingredient readById(Long id) {
        final Optional<Ingredient> optional = ingRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new IllegalArgumentException("Ingredient Null");
    }

    public void delete(Long id) {
        final Ingredient ingredient = readById(id);
        if (ingredient != null) {
            ingRepo.delete(ingredient);
        } else {
            throw new IllegalArgumentException("delete error");
        }
    }

    public List<Ingredient> findAll() {
        return ingRepo.findAll();
    }
}
