package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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
    public String index(@RequestParam(name = "s") Optional<String> s, @RequestParam(name = "p") Optional<BigDecimal> p, Model model){
        List<Pizza> pizzas;

        if(s.isEmpty()){
            pizzas =  pizzaRepo.findAll();
            if(!p.isEmpty()){
                pizzas = pizzaRepo.findByPriceLessThanEqual(p.get());
            }
        } else if (!p.isEmpty()) {
            pizzas = pizzaRepo.findByNameContainingIgnoreCaseAndPriceLessThanEqual(s.get(), p.get());
        } else {
            pizzas = pizzaRepo.findByNameContainingIgnoreCase(s.get());
            model.addAttribute("search", s.get());
        }

        model.addAttribute("pizzas", pizzas);

        return "/pizzas/index";
    }

    //ADVANCED SEARCH
    @GetMapping("/advanced-search")
    public String advancedSearch(){
        return "/pizzas/advanced-search";
    }

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        Pizza pizza = pizzaRepo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("pizza", pizza);
        return "/pizzas/show";
    }

    //CREATE
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza" , new Pizza());
        return "/pizzas/create";
    }

    //STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/pizzas/create";
        }
        pizzaRepo.save(formPizza);
        return "redirect:/pizzas";
    }

    //EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Pizza pizza = pizzaRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("pizza", pizza);
        return "/pizzas/edit";
    }

    //UPDATE
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/pizzas/edit";
        }

        pizzaRepo.save(pizza);

        return "redirect:/pizzas";
    }

}
