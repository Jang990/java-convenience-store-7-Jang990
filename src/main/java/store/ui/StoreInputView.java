package store.ui;

import store.basic.StringPrinter;
import store.basic.StringReader;
import store.ui.dto.ProductRequest;

import java.util.List;

public class StoreInputView {

    private static final String USER_YES = "Y";
    private static final String USER_NO = "N";
    private static final String INVALID_INPUT_ERROR_MESSAGE = "잘못된 입력입니다. 다시 입력해 주세요.";

    private static final String PURCHASE_PRODUCT_PROMPT = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    private final StringReader stringReader;
    private final StringPrinter printer;
    private final ProductRequestReader productRequestReader;
    private final String MEMBERSHIP_DISCOUNT_PROMPT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private final String PURCHASE_RETRY_PROMPT = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private final String PROCEEDS_WITHOUT_PROMOTION_PROMPT = "현재 %s %s개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private final String MORE_PROMOTION_DISCOUNT_PROMPT = "현재 %s은(는) %s개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";

    public StoreInputView(StringReader stringReader, StringPrinter printer, ProductRequestReader productRequestReader) {
        this.stringReader = stringReader;
        this.printer = printer;
        this.productRequestReader = productRequestReader;
    }

    public List<ProductRequest> askForPurchaseProduct() {
        printer.print(PURCHASE_PRODUCT_PROMPT);
        return productRequestReader.read(stringReader.readLine());
    }

    public boolean askForMembershipDiscountApplicable() {
        printer.print(MEMBERSHIP_DISCOUNT_PROMPT);
        return readYesOrNo();
    }

    public boolean askForPurchaseRetry() {
        printer.print(PURCHASE_RETRY_PROMPT);
        return readYesOrNo();
    }

    public boolean askToProceedWithoutPromotion(String productName, String quantity) {
        printer.print(
                PROCEEDS_WITHOUT_PROMOTION_PROMPT
                .formatted(productName, quantity)
        );
        return readYesOrNo();
    }

    public boolean askForMorePromotionDiscount(String productName, String quantity) {
        printer.print(
                MORE_PROMOTION_DISCOUNT_PROMPT
                        .formatted(productName, quantity)
        );
        return readYesOrNo();
    }

    private boolean readYesOrNo() {
        String line = stringReader.readLine();
        if (line.equals(USER_YES))
            return true;
        if (line.equals(USER_NO))
            return false;

        throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
    }
}
