package nl.han.se.pizzanu.pizzas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PizzaController {
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> getAllPizzas(@RequestParam(required = false) String name) {
        List<Pizza> pizzas = new ArrayList<>();

        if (name == null)
            pizzaRepository.findAll().forEach(pizzas::add);
        else
            pizzaRepository.findByNameContaining(name).forEach(pizzas::add);

        if (pizzas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pizzas, HttpStatus.OK);

    }

    @GetMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") long id) {
        Pizza pizza = pizzaRepository.findById(id);

        if (pizza != null) {
            return new ResponseEntity<>(pizza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pizzas")
    public ResponseEntity<String> createPizza(@RequestBody Pizza pizza) {
        pizzaRepository.save(new Pizza(pizza.getProductName(), pizza.getDescription(), pizza.getPrice()));
        return new ResponseEntity<>("Pizza was created successfully.", HttpStatus.CREATED);
    }

    @PutMapping("/pizzas/{id}")
    public ResponseEntity<String> updatePizza(@PathVariable("id") long id, @RequestBody Pizza pizza) {
        Pizza _Pizza = pizzaRepository.findById(id);

        if (_Pizza != null) {
            _Pizza.setId(id);
            _Pizza.setProductName(pizza.getProductName());
            _Pizza.setDescription(pizza.getDescription());
            _Pizza.setPrice(pizza.getPrice());

            pizzaRepository.update(_Pizza);
            return new ResponseEntity<>("Pizza was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Pizza with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pizzas/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable("id") long id) {
        int result = pizzaRepository.deleteById(id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find Pizza with id=" + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Pizza was deleted successfully.", HttpStatus.OK);

    }

    @DeleteMapping("/pizzas")
    public ResponseEntity<String> deleteAllPizzas() {
        int numRows = pizzaRepository.deleteAll();
        return new ResponseEntity<>("Deleted " + numRows + " Pizza(s) successfully.", HttpStatus.OK);


    }
}
