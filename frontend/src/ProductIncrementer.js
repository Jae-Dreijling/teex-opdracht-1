export default function ProductIncrementer(p) {
  return (
    <>
      <div className="counter">
        <button
          className="increment-btn"
          onClick={() => p.plusClick(p.id)}
        >
          Order
        </button>
      </div>

    </>
  );
}
