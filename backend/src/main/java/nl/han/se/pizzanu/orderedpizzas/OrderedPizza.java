package nl.han.se.pizzanu.orderedpizzas;

public class OrderedPizza {

    private long id;
    private long pizzaId;
    private boolean finished;

    public OrderedPizza(long id, long pizzaId, boolean finished) {
        this.id = id;
        this.pizzaId = pizzaId;
        this.finished = finished;
    }

    public OrderedPizza(long pizzaId, boolean finished) {
        this.pizzaId = pizzaId;
        this.finished = finished;
   }

    public OrderedPizza() {

    }

    public boolean isFinished() {
        return finished;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
