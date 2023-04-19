package com.tm.pizzaorder.service;

import com.tm.pizzaorder.model.Order;
import com.tm.pizzaorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    public Order create(Order order) {
        try {
            return orderRepo.save(order);
        }catch (RuntimeException e){
            throw new IllegalArgumentException("Order Null");
        }
    }

    public Order readById(Long id) {
        final Optional<Order> optional = orderRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new IllegalArgumentException("Order Null");
    }

    public void delete(Long id) {
        final Order order = readById(id);
        if (order != null) {
            orderRepo.delete(order);
        } else {
            throw new IllegalArgumentException("delete error");
        }
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
