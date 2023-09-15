package nl.han.se.pizzanu.orderedpizzas;

public class OrderedPizza {

    private long id;
    private long pizzaId;


    public OrderedPizza(long id, long pizzaId) {
        this.id = id;
        this.pizzaId = pizzaId;
    }

    public OrderedPizza(long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public OrderedPizza() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(long pizzaId) {
        this.pizzaId = pizzaId;
    }
}
