export default function Cart(p) {
  const products = p.products.map((product) => (
    <div key={product.productName} className="cart-item">
      <span className="item-name">{product.productName}</span>
      <span className="item-quantity">{product.orderCount}</span>
      <span className="item-total">{product.orderCount * product.price}</span>
    </div>
  ));
  const total = p.products.reduce(
    (partial, p) => partial + p.orderCount * p.price,
    0
  );
  return (
    <>
      <div key="cartid" className="cart-container">
        <h2>Your Cart</h2>
        {products}

        <div className="price-total">Total: {total}</div>
      </div>
    </>
  );
}
