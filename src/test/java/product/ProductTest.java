package product;

import basic.TimeHolderStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.exception.PromotionException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static Promotion availablePromotion() {
        return PromotionTestBuilder.builder()
                .duration(PromotionDurationStub.withInPeriod)
                .build();
    }

    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        Product product = new Product(10);
        assertThrows(IllegalStateException.class, () -> product.sale(new TimeHolderStub(), 11));
    }

    @DisplayName("프로모션을 적용할 수 있지만, 프로모션 재고가 없다면 예외가 발생한다.")
    @Test
    void test2() {
        Product product = new Product(10, 0, availablePromotion());

        assertThrows(PromotionException.class, () -> product.sale(new TimeHolderStub(), 1));
    }

}