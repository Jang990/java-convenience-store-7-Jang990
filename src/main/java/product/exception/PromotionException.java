package product.exception;

import product.prototype.Quantity;

public class PromotionException extends Exception {
    private final Quantity error;
    public PromotionException(String message, Quantity error) {
        super(message);
        this.error = error;
    }

    public Quantity getErrorQuantity() {
        return error;
    }
}
