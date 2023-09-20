package nl.han.se.pizzanu.pizzas;

public class Pizza {
    private long id;
    private String productName;
    private String description;
    private int price;

    public Pizza(long id, String productName, String description, int price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public Pizza() {
    }

    public Pizza(String productName, String description, int price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

