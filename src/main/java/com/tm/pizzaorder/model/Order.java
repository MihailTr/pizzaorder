package com.tm.pizzaorder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Pizza pizza;
    @ManyToMany
    private List<Ingredient> ingredientList = new ArrayList<>();
    private double priceForAll;

    private String firstName;
    private String address;
    private int phone;
    private String email;
    private boolean submit;
    private String time;
}
