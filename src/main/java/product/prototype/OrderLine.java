package product.prototype;

public class OrderLine {
    private final String productName;
    private final int productPrice;
    private final ProductQuantity productQuantity;

    public OrderLine(String productName, int productPrice, ProductQuantity productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getPromotionQuantity() {
        return productQuantity.promotion;
    }

    public int getNormalQuantity() {
        return productQuantity.normal;
    }
}
