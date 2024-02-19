import "./App.css";
import ProductList from "./ProductList";
import Cart from "./Cart";
import { useEffect, useState, useRef } from "react";
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
    fetch("http://localhost:8080/api/orderedpizzas", requestOptions)
    // .then(
    //   setTimeout(() => fetchOrderedPizzas(), 1000) // Give the server 1 sec to process and then request the ordered pizzas.
    // )
    ;
  };

  const removeOrderedPizzaClick = function (clickedOrderedPizzaId) {
    const requestOptions = {
      method: "DELETE",
    };
    fetch(
      `http://localhost:8080/api/orderedpizzas/${clickedOrderedPizzaId}`,
      requestOptions
    )
    // .then(setTimeout(() => fetchOrderedPizzas(), 1000)) // Give the server 1 sec to process and then request the ordered pizzas.
    ; 
  };


  // WebSocket stuff
  const [message, setMessage] = useState(["bla"]);

  const [connected, setConnected] = useState(["false"]);

  const ws = useRef(null);

  const connect = function () {
    ws.current = new WebSocket("ws://localhost:8080/name");
    ws.current.onopen = () => console.log("ws opened");
    ws.current.onclose = () => console.log("ws closed");
    ws.current.onmessage = function (data) {
      showGreeting(data.data);
      fetchOrderedPizzas();
    };
    ws.current.onerror = function (error) {
      console.log(error);
    };
    setConnected("true");
  };

  useEffect(() => {
    ws.current = new WebSocket("ws://localhost:8080/name");
    ws.current.onopen = () => console.log("ws opened");
    ws.current.onclose = () => console.log("ws closed");
    ws.current.onmessage = function (data) {
      showGreeting(data.data);
      fetchOrderedPizzas();
    };
    ws.current.onerror = function (error) {
      console.log(error);
    };
    setConnected("true");
    const wsCurrent = ws.current;

        return () => {
            wsCurrent.close();
        };
  }, []);

  const disconnect = function () {
    if (ws.current != null) {
      ws.current.close();
    }
    setConnected("false");
    console.log("Disconnected");
  };

  const sendName = function () {
    if (ws.current) {
      ws.current.send('{name:"jaap"}');
    } else {
      console.log("ws not connected");
    }
  };

  function showGreeting(message) {
    setMessage(message);
  }

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
          <p>{message}</p>
          <button onClick={connect}>connect</button>
          <button onClick={sendName}>send</button>
          <button onClick={disconnect}>disconnect</button>
          <p>Connected? : {connected}</p>
        </TabPanel>
        <TabPanel>
          <Cart
            products={products}
            orderedPizzas={orderedPizzas}
            onRemoveClick={removeOrderedPizzaClick}
          ></Cart>
        </TabPanel>
      </Tabs>
    </>
  );
}
export default App;
