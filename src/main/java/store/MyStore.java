package store;

import store.basic.TimeHolder;
import store.product.OrderLine;
import store.product.Product;
import store.product.Promotion;
import store.product.Quantity;
import store.product.exception.MissedPromotionBenefitException;
import store.product.exception.PartialProductExclusionException;
import store.product.exception.PromotionException;
import store.users.MemberShip;
import store.users.Receipt;
import store.ui.StoreInputView;
import store.ui.StoreOutputView;
import store.ui.dto.ProductRequest;
import store.ui.dto.ProductView;

import java.util.LinkedList;
import java.util.List;

public class MyStore {
    public static final String STORE_ID = "W편의점";

    private final ProductBoard productBoard;
    private final StoreInputView storeInputView;
    private final StoreOutputView storeOutputView;
    private final TimeHolder timeHolder;

    public MyStore(StoreFileReader storeFileReader, StoreInputView storeInputView, StoreOutputView storeOutputView, TimeHolder timeHolder) {
        List<Promotion> promotions = storeFileReader.readPromotions();
        List<Product> products = storeFileReader.readProduct(promotions);
        productBoard = new ProductBoard(products);

        this.storeInputView = storeInputView;
        this.storeOutputView = storeOutputView;
        this.timeHolder = timeHolder;
    }

    public void start() {
        MemberShip memberShip = new MemberShip();
        do {
            storeOutputView.welcome(STORE_ID, productBoard.findAll().stream().map(ProductView::new).toList());
            List<OrderLine> orders = requestPurchase();
            Receipt receipt = createReceipt(orders, memberShip);
            storeOutputView.print(receipt);
        } while (storeInputView.askForPurchaseRetry());
    }

    private List<OrderLine> requestPurchase() {
        List<OrderLine> orders = new LinkedList<>();
        for (ProductRequest purchaseRequest : storeInputView.askForPurchaseProduct()) {
            Product product = productBoard.find(purchaseRequest.getProductName())
                    .orElseThrow(IllegalAccessError::new);

            Quantity purchaseQuantity = Quantity.of(purchaseRequest.getQuantity());
            orders.add(order(purchaseQuantity, product));
        }
        return orders;
    }

    private Receipt createReceipt(List<OrderLine> orders, MemberShip memberShip) {
        if(storeInputView.askForMembershipDiscountApplicable())
            return new Receipt(STORE_ID, orders, memberShip);
        return new Receipt(STORE_ID, orders);
    }

    private OrderLine order(Quantity purchaseQuantity, Product product) {
        try {
            return product.purchase(timeHolder, purchaseQuantity);
        } catch (PromotionException e) {
            if (e instanceof MissedPromotionBenefitException missedException)
                return handleMissedException(purchaseQuantity, product, missedException);

            if (e instanceof PartialProductExclusionException exclusionException)
                return handleExclusionException(purchaseQuantity, product, exclusionException);

            throw new IllegalStateException("알 수 없는 오류로 종료합니다");
        }
    }

    private OrderLine handleExclusionException(
            Quantity purchaseQuantity, Product product,
            PartialProductExclusionException exclusionException) {
        Quantity nonPromotionalQuantity = exclusionException.getNonPromotionalQuantity();
        if(storeInputView.askToProceedWithoutPromotion(
                product.getName(), nonPromotionalQuantity.toString()))
            return product.purchaseWithoutPromotionException(timeHolder, purchaseQuantity);
        return product.purchaseWithoutPromotionException(timeHolder, purchaseQuantity.minus(nonPromotionalQuantity));
    }

    private OrderLine handleMissedException(
            Quantity purchaseQuantity, Product product,
            MissedPromotionBenefitException missedException) {
        Quantity missingFreeQuantity = missedException.getMissingFreeQuantity();
        if(storeInputView.askForMorePromotionDiscount(product.getName(), missingFreeQuantity.toString()))
            return product.purchaseWithoutPromotionException(timeHolder, purchaseQuantity.plus(missingFreeQuantity));
        return product.purchaseWithoutPromotionException(timeHolder, purchaseQuantity);
    }
}
