package org.lessons.java.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Il campo nome non può essere vuoto")
    @Size(min = 1, max = 255, message = "Il nome non può avere più di 255 caratteri")
    @Column(nullable = false)
    private String name;
    @Size(min=10, max=255, message="La descrizione deve avere minimo 10 caratteri e massimo 255")
    private String description;
    @Positive(message = "Il prezzo non può essere zero o inferiore a zero")
    @NotNull(message = "Il prezzo deve essere inserito")
    @Column(nullable = false)
    private float price;

    //GETTERS
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
