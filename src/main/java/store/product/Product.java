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

    private Quantity applyPromotion(Quantity requested) throws PromotionException {
        if(promotion == null)
            return Quantity.EMPTY;

        ProductQuantity quantityToPurchase = getProductQuantityToPurchase(requested);
        try {
            return promotion.calculateFree(quantityToPurchase);
        } catch (MissedPromotionBenefitException e) {
            if(productQuantity.stock().isLessThan(requested.plus(e.getMissingFreeQuantity())))
                return promotion.calculateFreeWithoutException(quantityToPurchase);
            throw e;
        }
    }

    private ProductQuantity getProductQuantityToPurchase(Quantity requested) {
        ProductQuantity quantityAfterPurchase = productQuantity.decrease(requested);
        return productQuantity.calculateDifference(quantityAfterPurchase);
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






    public OrderLine purchaseWithoutPromotion(Quantity purchaseQuantity) {
        return null;
    }
}
