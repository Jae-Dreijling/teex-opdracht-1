package nl.han.se.pizzanu.orderedpizzas;

public class OrderedPizza {

    private long id;
    private long pizzaId;
    private boolean isFinished;

    public OrderedPizza(long id, long pizzaId, boolean isFinished) {
        this.id = id;
        this.pizzaId = pizzaId;
        this.isFinished = isFinished;
    }

    public OrderedPizza(long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public OrderedPizza() {

    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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
