package product.exception;

import product.Quantity;

public class MissedPromotionBenefitException extends PromotionException {
    public MissedPromotionBenefitException(String message, Quantity error) {
        super(message, error);
    }

    public Quantity getMissingFreeQuantity() {
        return getErrorQuantity();
    }
}
