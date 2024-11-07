package product;

public class Product {
    private int stock;

    public Product(int stock) {
        this.stock = stock;
    }

    public void sale(int saleQuantity) {
        if(stock < saleQuantity)
            throw new IllegalStateException("재고가 부족합니다.");
        this.stock -= saleQuantity;

    }
}
