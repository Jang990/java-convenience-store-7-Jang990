package product;

public class OrderLine {
    private final String productName;
    private final int productPrice;
    private final Quantity productToPay;
    private final Quantity freeProduct;

    public OrderLine(
            String productName, int productPrice,
            Quantity productToPay, Quantity freeProduct) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productToPay = productToPay;
        this.freeProduct = freeProduct;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getProductToPay() {
        return productToPay;
    }
    public Quantity getFreeProduct() {
        return freeProduct;
    }
}
