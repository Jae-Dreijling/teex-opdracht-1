export default function Product(p) {
  return (
    <>
      <div className="pizza-container" id={"product-" + p.id}>
        <img className="pizza-image" src="pizza.png" alt="Pizza 1" />
        <div className="pizza-details">
          <h2 className="pizza-name">{p.productName}</h2>
          <p className="pizza-description">{p.description}</p>
          <p className="pizza-price">{p.price}</p>
        </div>
      </div>
    </>
  );
}

