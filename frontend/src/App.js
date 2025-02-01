import "./App.css";
import ProductList from "./ProductList";
import Cart from "./Cart";
import OrderList from "./OrderList";
import { useEffect, useState } from "react";
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs'; // eslint-disable-line

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
      finished: false
    },
  ]);

  useEffect(() => { fetchPizzas(); }, []);

  // eslint-disable-next-line react-hooks/exhaustive-deps
  useEffect(() => { fetchOrderedPizzas(); }, [products]);

  const fetchPizzas = function () {
    console.log("fetching pizzas");
    fetch("http://localhost:8080/api/pizzas")
      .then((response) => response.json())
      .then((responseJSON) => {
        setProducts(responseJSON);
      });
  }

  const fetchOrderedPizzas = function () {
    console.log("fetching orderedpizzas");
    fetch("http://localhost:8080/api/orderedpizzas")
      .then((opresponse) => opresponse.json() || {})
      .then((opresponseJSON) => {
        var newOrderedPizzas = opresponseJSON.map((orderedPizza) => {
          var pizza = products.find((p) => p.id === orderedPizza.pizzaId) || {};
          return {
            ...orderedPizza,
            productName: pizza.productName,
            description: pizza.description,
            price: pizza.price
          };
        });
        setOrderedPizzas(newOrderedPizzas);
      });
  };

  const onOrderClick = function (clickedProductId) {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ pizzaId: clickedProductId }),
    };
    fetch("http://localhost:8080/api/orderedpizzas", requestOptions)
      .then((opresponse) => opresponse.json() || {})
      .then((orderedPizza) => {
        var newOrderedPizzas = [...orderedPizzas];
        var pizza = products.find((p) => p.id === orderedPizza.pizzaId) || {};
        newOrderedPizzas.push({
          ...orderedPizza,
            productName: pizza.productName,
            description: pizza.description,
            price: pizza.price
        });
        setOrderedPizzas(newOrderedPizzas);
      });
  };

  const removeOrderedPizzaClick = function (clickedOrderedPizzaId) {
    const requestOptions = {
      method: "DELETE",
    };
    fetch(`http://localhost:8080/api/orderedpizzas/${clickedOrderedPizzaId}`, requestOptions)
      .then(() => {
        setOrderedPizzas(orderedPizzas.filter(op => op.id !== clickedOrderedPizzaId));
      });
  };

  const finishOrderedPizzaClick = function (clickedOrderedPizzaId) {
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id: clickedOrderedPizzaId, finished: true }),
    };
    fetch(`http://localhost:8080/api/orderedpizzas/${clickedOrderedPizzaId}`, requestOptions)
      .then(() => {
        const newOrderedPizzas = [...orderedPizzas];
        newOrderedPizzas.find(a => a.id === clickedOrderedPizzaId).finished = true;
        setOrderedPizzas(newOrderedPizzas);
      });
  };

  return (
    <>
      <h1>Pizza di Papavione</h1>
      <Tabs>
        <TabList>
          <Tab>Klant</Tab>
          <Tab>Bakker</Tab>
        </TabList>
        <TabPanel>
          <ProductList onOrderClick={onOrderClick} products={products} />
          <Cart
            products={products}
            orderedPizzas={orderedPizzas}
            onRemoveClick={removeOrderedPizzaClick}
          ></Cart>
        </TabPanel>
        <TabPanel>
          <OrderList
            products={products}
            orderedPizzas={orderedPizzas}
            onFinishedClick={finishOrderedPizzaClick}
          ></OrderList>
        </TabPanel>
      </Tabs>
    </>
  );
}
export default App;
