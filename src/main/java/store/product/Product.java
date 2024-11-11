package store.product;

import store.money.Money;
import store.product.exception.MissedPromotionBenefitException;
import store.product.exception.PromotionException;

public class Product {
    private static final String REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE = "0개를 구매할 수 없습니다.";

    private final String name;
    private final Money price;
    private final Promotion promotion;
    private ProductQuantity productQuantity;

    public Product(
            String name, Money price,
            ProductQuantity productQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.productQuantity = productQuantity;
    }

    public Product(String name, Money price, ProductQuantity productQuantity) {
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
        promotion = null;
    }

    public OrderLine purchase(Quantity requested) throws PromotionException {
        if(Quantity.isEmpty(requested))
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);

        Quantity freeQuantity = applyPromotion(requested);
        productQuantity = productQuantity.decrease(requested);
        return new OrderLine(
                name, price,
                requested.minus(freeQuantity),
                freeQuantity
        );
    }

    public OrderLine purchaseWithoutPromotionException(Quantity requested) {
        if(Quantity.isEmpty(requested))
            throw new IllegalArgumentException(REQUESTING_EMPTY_QUANTITY_ERROR_MESSAGE);

        Quantity freeQuantity = applyPromotionWithoutException(requested);
        productQuantity = productQuantity.decrease(requested);
        return new OrderLine(
                name, price,
                requested.minus(freeQuantity),
                freeQuantity
        );
    }

    public ProductQuantity getStock() {
        return productQuantity;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    private Quantity applyPromotionWithoutException(Quantity requested) {
        if(promotion == null)
            return Quantity.EMPTY;

        ProductQuantity quantityToPurchase = getProductQuantityToPurchase(requested);
        return promotion.calculateFreeWithoutException(quantityToPurchase);
    }

    private Quantity applyPromotion(Quantity requested) throws PromotionException {
        if(promotion == null)
            return Quantity.EMPTY;

        try {
            return promotion.calculateFree(getProductQuantityToPurchase(requested));
        } catch (MissedPromotionBenefitException e) {
            return handleMissedException(requested, e);
        }
    }

    private Quantity handleMissedException(Quantity requested, MissedPromotionBenefitException e) throws MissedPromotionBenefitException {
        ProductQuantity quantityToPurchase = getProductQuantityToPurchase(requested);
        if(productQuantity.lacksPromotionStock(requested.plus(e.getMissingFreeQuantity())))
            return promotion.calculateFreeWithoutException(quantityToPurchase);
        throw e;
    }

    private ProductQuantity getProductQuantityToPurchase(Quantity requested) {
        ProductQuantity quantityAfterPurchase = productQuantity.decrease(requested);
        return productQuantity.calculateDifference(quantityAfterPurchase);
    }
}
