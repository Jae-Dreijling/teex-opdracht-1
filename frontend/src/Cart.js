export default function Cart(p) {
  const products = p.orderedPizzas.map((orderedPizza) => (
    <div key={orderedPizza.id} className="cart-item">
      <span className="item-name">{orderedPizza.id}</span>
      <span className="item-name">{orderedPizza.pizzaId}</span>
      <span className="item-name">{orderedPizza.productName}</span>
      <span className="item-total">{orderedPizza.price}</span>
      <span><button className=".button">Remove</button></span>
    </div>
  ));
  const total = p.orderedPizzas.reduce(
    (partial, p) => partial + p.price,
    0
  );
  return (
    <>
      <div key="cartid" className="cart-container">
        <h2>Your Ordered Pizzas</h2>
        {products}

        <div className="price-total">Total: {total}</div>
      </div>
    </>
  );
}
