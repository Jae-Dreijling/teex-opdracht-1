package nl.han.se.pizzanu.orderedpizzas;

import nl.han.se.pizzanu.pizzas.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderedPizzaController {

    @Autowired
    OrderedPizzaRepository orderedPizzaRepository;

    @GetMapping("/orderedpizzas")
    public ResponseEntity<List<OrderedPizza>> getAllOrderedPizzas() {
        List<OrderedPizza> orderedPizzas = new ArrayList<>();

        orderedPizzaRepository.findAll().forEach(orderedPizzas::add);

        if (orderedPizzas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderedPizzas, HttpStatus.OK);

    }

    @PostMapping("/orderedpizzas")
    public ResponseEntity<String> createOrderedPizza(@RequestBody OrderedPizza orderedPizza) {
        orderedPizzaRepository.save(new OrderedPizza(orderedPizza.getPizzaId()));
        return new ResponseEntity<>("Pizza was ordered successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/orderedpizzas/{id}")
    public ResponseEntity<String> deleteOrderedPizza(@PathVariable("id") long id) {
        int result = orderedPizzaRepository.deleteById(id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find OrderedPizza with id=" + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("OrderedPizza was deleted successfully.", HttpStatus.OK);

    }
}
