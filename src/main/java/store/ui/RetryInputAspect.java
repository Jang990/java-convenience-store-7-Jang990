package store.ui;

import store.basic.StringPrinter;
import store.ui.dto.ProductRequest;

import java.util.List;

public class RetryInputAspect extends StoreInputView {
    private final StoreInputView main;
    private final StringPrinter printer;

    public RetryInputAspect(StoreInputView storeInputView, StringPrinter printer) {
        super(null, null, null);
        main = storeInputView;
        this.printer = printer;
    }

    @Override
    public List<ProductRequest> askForPurchaseProduct() {
        while (true) {
            try {
                return main.askForPurchaseProduct();
            } catch (IllegalArgumentException e) {
                printer.print(toErrorMessage(e));
            }
        }
    }

    @Override
    public boolean askForMembershipDiscountApplicable() {
        while (true) {
            try {
                return main.askForMembershipDiscountApplicable();
            } catch (IllegalArgumentException e) {
                printer.print(toErrorMessage(e));
            }
        }
    }

    @Override
    public boolean askForPurchaseRetry() {
        while (true) {
            try {
                return main.askForPurchaseRetry();
            } catch (IllegalArgumentException e) {
                printer.print(toErrorMessage(e));
            }
        }
    }

    @Override
    public boolean askToProceedWithoutPromotion(String productName, String quantity) {
        while (true) {
            try {
                return main.askToProceedWithoutPromotion(productName, quantity);
            } catch (IllegalArgumentException e) {
                printer.print(toErrorMessage(e));
            }
        }
    }

    @Override
    public boolean askForMorePromotionDiscount(String productName, String quantity) {
        while (true) {
            try {
                return main.askForMorePromotionDiscount(productName, quantity);
            } catch (IllegalArgumentException e) {
                printer.print(toErrorMessage(e));
            }
        }
    }

    private static String toErrorMessage(IllegalArgumentException e) {
        return "[ERROR] ".concat(e.getMessage()).concat("\n");
    }
}
