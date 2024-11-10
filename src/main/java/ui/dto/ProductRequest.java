package ui.dto;

public class ProductRequest {
    private final String productName;
    private final int quantity;

    public ProductRequest(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
