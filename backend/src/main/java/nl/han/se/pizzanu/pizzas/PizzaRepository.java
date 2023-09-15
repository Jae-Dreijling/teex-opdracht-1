package nl.han.se.pizzanu.pizzas;

import nl.han.se.pizzanu.PizzaNuRepository;

import java.util.List;

public interface PizzaRepository extends PizzaNuRepository<Pizza, Long> {

    List<Pizza> findByNameContaining(String productname);
}
