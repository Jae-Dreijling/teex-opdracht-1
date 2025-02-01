export default function OrderList(p) {

  const products = p.orderedPizzas.map((orderedPizza) => (
    <div key={orderedPizza.id} className="cart-item">
      <span className="item-name">{orderedPizza.productName}</span>
      <span className="item-description">{orderedPizza.description}</span>
      <span className="item-description">{orderedPizza.finished ? "klaar" : "nietklaar"}</span>
      <span><button onClick={() => p.onFinishedClick(orderedPizza.id)} className=".button">ClickMeWhenFinished</button></span>
    </div>
  ));

  return (
    <>
      <div key="cartid" className="cart-container">
        <h2>Your Todo Pizzas</h2>
        {products} 
      </div>
    </>
  );
}
