package nl.han.se.pizzanu.orderedpizzas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderedPizzaController {

    @Autowired
    OrderedPizzaRepository orderedPizzaRepository;

    @GetMapping("/orderedpizzas")
    public ResponseEntity<List<OrderedPizza>> getAllOrderedPizzas() {
        List<OrderedPizza> orderedPizzas = new ArrayList<>();

        List<OrderedPizza> all = orderedPizzaRepository.findAll();

        all.forEach(orderedPizzas::add);

        return new ResponseEntity<>(orderedPizzas, HttpStatus.OK);

    }

    @PostMapping("/orderedpizzas")
    public ResponseEntity<OrderedPizza> createOrderedPizza(@RequestBody OrderedPizza orderedPizza) {
        int newId = orderedPizzaRepository.save(new OrderedPizza(orderedPizza.getPizzaId(), false));
        return new ResponseEntity<>(
                orderedPizzaRepository.findById(Integer.toUnsignedLong(newId)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/orderedpizzas/{id}")
    public ResponseEntity<String> updateOrderedPizza(@RequestBody OrderedPizza orderedPizza, @PathVariable("id") long id) {
        int result = orderedPizzaRepository.update(orderedPizza);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find OrderedPizza with id=" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("OrderedPizza was update successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/orderedpizzas/{id}")
    public ResponseEntity<String> deleteOrderedPizza(@PathVariable("id") long id) {
        int result = orderedPizzaRepository.deleteById(id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find OrderedPizza with id=" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("OrderedPizza was deleted successfully.", HttpStatus.OK);

    }
}
