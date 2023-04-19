package com.tm.pizzaorder.controller;

import com.tm.pizzaorder.model.Ingredient;
import com.tm.pizzaorder.model.Order;
import com.tm.pizzaorder.model.Pizza;
import com.tm.pizzaorder.service.IngredientService;
import com.tm.pizzaorder.service.OrderService;
import com.tm.pizzaorder.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/", "/order"})
public class OrderController {
    private final OrderService orderService;
    private final PizzaService pizzaService;
    private final IngredientService ingredientService;

    @ModelAttribute("allPizzas")
    public List<Pizza> allPizzaList(){
        return this.pizzaService.findAll();
    }

    @ModelAttribute("allIngredients")
    public List<Ingredient> allIngredientList(){
        return this.ingredientService.findAll();
    }

    @ModelAttribute("allOrders")
    public List<Order> allOrderList(){
        return this.orderService.findAll();
    }

    public OrderController(OrderService orderService, PizzaService pizzaService, IngredientService ingredientService) {
        this.orderService = orderService;
        this.pizzaService = pizzaService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String showOrder(final Order order) {
        return "order";
    }
    @RequestMapping(value="/order", params={"save"})
    public String saveOrder(final Order order, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "order";
        }
        var allCost = order.getPizza().getPrice() + order.getIngredientList().stream().mapToDouble(Ingredient::getPrice).sum();;
        order.setPriceForAll(allCost);
        var newOrder = orderService.create(order);
        model.addAttribute("order", newOrder);
        return "order-info";
    }

    @RequestMapping(value="/order", params={"addRow"})
    public String addRow(final Order order, final BindingResult bindingResult) {
        order.getIngredientList().add(new Ingredient());
//        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping(value="/order", params={"removeRow"})
    public String removeRow(final Order order, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        if (order.getIngredientList().size() != 0) {
            order.getIngredientList().remove(rowId.intValue());
        }
        return "order";
    }

    @GetMapping({ "/order/all"})
    public String getAll(Model model) {
        return "order-list";
    }
    @GetMapping("order/{id}")
    public String read(@PathVariable long id, Model model) {
        model.addAttribute("order", orderService.readById(id));
        return "order-info";
    }
}
