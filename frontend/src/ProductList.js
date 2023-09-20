import Product from "./Product";
import ProductIncrementer from "./ProductIncrementer";

export default function ProductList(p) {
  const products = p.products.map((product) => (
    <div id={product.id} key={product.id}>
      <Product
        productName={product.productName}
        description={product.description}
        price={product.price}
      />
      <ProductIncrementer
        id={product.id}
        plusClick={p.onProductIncPlusClick}
      />
    </div>
  ));
  return (
    <>
      <div id="plist">{products}</div>
    </>
  );
}
