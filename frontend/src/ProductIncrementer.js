export default function ProductIncrementer(p) {
  return (
    <>
      <div className="counter">
        <button
          className="decrement-btn"
          onClick={() => p.minusClick(p.productName)}
        >
          -
        </button>
        <input className="counter-input" type="number" value={p.orderCount} readOnly />
        <button
          className="increment-btn"
          onClick={() => p.plusClick(p.productName)}
        >
          +
        </button>
      </div>

    </>
  );
}
