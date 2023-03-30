package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepo;

    //INDEX
    @GetMapping
    public String index(@RequestParam(name = "s") Optional<String> s, Model model){
        List<Pizza> pizzas;

        if(s.isEmpty()){
            pizzas =  pizzaRepo.findAll();
        } else {
            pizzas = pizzaRepo.findByNameContainingIgnoreCase(s.get());
            model.addAttribute("search", s.get());
        }

        model.addAttribute("pizzas", pizzas);

        return "/pizzas/index";
    }

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        Pizza pizza = pizzaRepo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("pizza", pizza);
        return "/pizzas/show";
    }
}
