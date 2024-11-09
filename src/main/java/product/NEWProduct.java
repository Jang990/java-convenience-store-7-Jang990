package product;

public class NEWProduct {
    private int stock;

    public NEWProduct(int stock) {
        this.stock = stock;
    }

    public void buy(int quantity) {
        if(stock < quantity)
            throw new IllegalStateException("재고가 부족합니다.");
    }
}
