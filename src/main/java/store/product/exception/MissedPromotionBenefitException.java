package store.product.exception;

import store.product.Quantity;

public class MissedPromotionBenefitException extends PromotionException {
    public MissedPromotionBenefitException(String message, Quantity error) {
        super(message, error);
    }

    public Quantity getMissingFreeQuantity() {
        return getErrorQuantity();
    }
}
