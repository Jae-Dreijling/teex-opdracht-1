import "./App.css";
import ProductList from "./ProductList";
import Cart from "./Cart";
import { useEffect, useState } from "react";
// import useWebSocket from 'react-use-websocket';
import { StompSessionProvider, useSubscription } from "react-stomp-hooks";

const WS_URL = "http://localhost:8080/gs-guide-websocket";

function App() {
  // useWebSocket(WS_URL, {
  //   onOpen: () => {
  //     console.log('WebSocket connection established.');
  //   }
  // });
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

  const onProductIncPlusClick = function (clickedProductId) {
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

  const wserror = function (error) {
    console.log(error);
    debugger;
  };
  const connectws = function () {};

  return (
    <>
      <StompSessionProvider
        url={WS_URL}
        errorCallback={(e) => wserror(e)}
        debug={(str) => {
          console.log(str);
        }}
      >
        <SubscribingComponent />
        <h1>Pizza di Papavione</h1>
        <ProductList
          onProductIncPlusClick={onProductIncPlusClick}
          products={products}
        />
        <Cart
          products={products}
          orderedPizzas={orderedPizzas}
          onRemoveClick={removeOrderedPizzaClick}
        ></Cart>
      </StompSessionProvider>
      <button onClick={() => connectws()} />
    </>
  );
}

function SubscribingComponent() {
  const [lastMessage, setLastMessage] = useState("No message received yet");

  //Subscribe to /topic/test, and use handler for all received messages
  //Note that all subscriptions made through the library are automatically removed when their owning component gets unmounted.
  //If the STOMP connection itself is lost they are however restored on reconnect.
  //You can also supply an array as the first parameter, which will subscribe to all destinations in the array
  useSubscription("/topic/greetings", (message) =>
    setLastMessage(message.body)
  );

  return <div>Last Message: {lastMessage}</div>;
}

export default App;
