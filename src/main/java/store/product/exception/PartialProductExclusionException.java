package store.product.exception;

import store.product.Quantity;

public class PartialProductExclusionException extends PromotionException {
    public PartialProductExclusionException(String message, Quantity error) {
        super(message, error);
    }

    public Quantity getNonPromotionalQuantity() {
        return getErrorQuantity();
    }
}
