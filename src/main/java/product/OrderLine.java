package product;

import money.Money;

public class OrderLine {
    private final String productName;
    private final Money productPrice;
    private final Quantity productToPay;
    private final Quantity freeProduct;

    public OrderLine(
            String productName, Money productPrice,
            Quantity productToPay, Quantity freeProduct) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productToPay = productToPay;
        this.freeProduct = freeProduct;
    }

    public Money getProductPrice() {
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
