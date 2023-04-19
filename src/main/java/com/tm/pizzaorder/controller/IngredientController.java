package com.tm.pizzaorder.controller;

import com.tm.pizzaorder.model.Ingredient;
import com.tm.pizzaorder.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping({"/", "/all"})
    public String getAll(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient-list";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/ingredient/all";
        }
        ingredientService.create(ingredient);
        return "redirect:/ingredient/all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        ingredientService.delete(id);
        return "redirect:/ingredient/all";
    }
}
