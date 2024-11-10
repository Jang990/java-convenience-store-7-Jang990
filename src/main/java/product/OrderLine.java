package product;

public class OrderLine {
    private final String productName;
    private final int productPrice;
    private final ProductQuantity purchaseQuantity;

    public OrderLine(String productName, int productPrice, ProductQuantity purchaseQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public ProductQuantity getPurchaseQuantity() {
        return purchaseQuantity;
    }
}
