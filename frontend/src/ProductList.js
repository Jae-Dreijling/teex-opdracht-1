import Product from "./Product";
import ProductIncrementer from "./ProductIncrementer";

export default function ProductList(p) {
    const products = p.products.map((product) => (
        <div id={product.productName}>
        <Product productName={product.productName} description={product.description}/>
        <ProductIncrementer productName={product.productName} plusClick={p.onProductIncPlusClick} minusClick={p.onProductIncMinusClick} orderCount={product.orderCount}/>
        </div>
      ))
  return (
    <>
      <div id="plist">
       {products}
      </div>
    </>
  );
}
