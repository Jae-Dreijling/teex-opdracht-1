package nl.han.se.pizzanu.orderedpizzas;

import nl.han.se.pizzanu.SocketHandler;
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

    @Autowired
    SocketHandler socketHandler;

    @GetMapping("/orderedpizzas")
    public ResponseEntity<List<OrderedPizza>> getAllOrderedPizzas() {
        List<OrderedPizza> orderedPizzas = new ArrayList<>();

        orderedPizzaRepository.findAll().forEach(orderedPizzas::add);

        return new ResponseEntity<>(orderedPizzas, HttpStatus.OK);

    }

    @PostMapping("/orderedpizzas")
    public ResponseEntity<String> createOrderedPizza(@RequestBody OrderedPizza orderedPizza) {
        orderedPizzaRepository.save(new OrderedPizza(orderedPizza.getPizzaId()));
        socketHandler.pingAll();
        return new ResponseEntity<>("Pizza was ordered successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/orderedpizzas/{id}")
    public ResponseEntity<String> deleteOrderedPizza(@PathVariable("id") long id) {
        int result = orderedPizzaRepository.deleteById(id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find OrderedPizza with id=" + id, HttpStatus.NOT_FOUND);
        }
        socketHandler.pingAll();
        return new ResponseEntity<>("OrderedPizza was deleted successfully.", HttpStatus.OK);

    }
}
