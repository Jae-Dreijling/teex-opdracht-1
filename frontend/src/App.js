import "./App.css";
import ProductList from "./ProductList";
import Cart from "./Cart";
import { useEffect, useState } from "react";

function App() {
  const [products, setProducts] = useState([
    {
      id: 0,
      productName: "Pizzas are coming",
      description: "With added awesomeness",
      price: 10,
    },
  ]);

  const [orderedPizzas, setOrderedPizzas] = useState([
    {
      id: 0,
      pizzaId: 0,
      productName: "No pizzas ordered",
      description: "Order your pizzas above",
      price: 0,
    },
  ]);

  useEffect(() => {
    fetch("http://localhost:8080/api/pizzas")
      .then((response) => response.json())
      .then((responseJSON) => {
        setProducts(responseJSON);
      });
  }, []);

  const fetchOrderedPizzas = function () {
    fetch("http://localhost:8080/api/orderedpizzas")
      .then((opresponse) => opresponse.json() || {})
      .then((opresponseJSON) => {
        console.log(opresponseJSON);
        var newOrderedPizzas = opresponseJSON.map((orderedPizza) => {
          var pizza = products.find((p) => p.id === orderedPizza.pizzaId) || {};
          return {
            ...orderedPizza,
            productName: pizza.productName,
            description: pizza.description,
            price: pizza.price,
          };
        });
        setOrderedPizzas(newOrderedPizzas);
      });
  };

  useEffect(() => {
    fetchOrderedPizzas();
    // eslint-disable-next-line
  }, [products]);

  const onOrderClick = function (clickedProductId) {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ pizzaId: clickedProductId }),
    };
    fetch("http://localhost:8080/api/orderedpizzas", requestOptions).then(
      setTimeout(() => fetchOrderedPizzas(), 1000) // Give the server 1 sec to process and then request the ordered pizzas.
    );
  };

  const removeOrderedPizzaClick = function (clickedOrderedPizzaId) {
    const requestOptions = {
      method: "DELETE",
    };
    fetch(
      `http://localhost:8080/api/orderedpizzas/${clickedOrderedPizzaId}`,
      requestOptions
    ).then(setTimeout(() => fetchOrderedPizzas(), 1000)); // Give the server 1 sec to process and then request the ordered pizzas.
  };

  return (
    <>
      <h1>Pizza di Papavione</h1>
      <ProductList onOrderClick={onOrderClick} products={products} />
      <Cart
        products={products}
        orderedPizzas={orderedPizzas}
        onRemoveClick={removeOrderedPizzaClick}
      ></Cart>
    </>
  );
}
export default App;
