package store.product.exception;

import store.product.Quantity;

public abstract class PromotionException extends Exception {
    private final Quantity error;
    public PromotionException(String message, Quantity error) {
        super(message);
        this.error = error;
    }

    protected Quantity getErrorQuantity() {
        return error;
    }
}
