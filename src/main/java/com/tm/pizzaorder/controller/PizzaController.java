package com.tm.pizzaorder.controller;

import com.tm.pizzaorder.model.Pizza;
import com.tm.pizzaorder.service.PizzaService;
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
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping({"/", "/all"})
    public String getAll(Model model) {
        model.addAttribute("pizzas", pizzaService.findAll());
        model.addAttribute("pizza", new Pizza());
        return "pizza-list";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("pizza") Pizza pizza, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/pizza/all";
        }
        pizzaService.create(pizza);
        return "redirect:/pizza/all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        pizzaService.delete(id);
        return "redirect:/pizza/all";
    }
}
