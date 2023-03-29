package org.lessons.java.springlamiapizzeriacrud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private double price;

    //GETTERS
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
