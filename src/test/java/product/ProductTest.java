package product;

import basic.TimeHolderStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.exception.PromotionException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static final LocalDate now = LocalDate.now();
    private static final Promotion activeNowPromotion = new Promotion(new PromotionDuration(now, now), "");

    private static TimeHolderStub nowTimeStub() {
        TimeHolderStub result = new TimeHolderStub();
        result.setTestNow(now);
        return result;
    }

    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        Product product = new Product(10);
        assertThrows(IllegalStateException.class, () -> product.sale(nowTimeStub(), 11));
    }

    @DisplayName("프로모션을 적용할 수 있지만, 프로모션 재고가 없다면 예외가 발생한다.")
    @Test
    void test2() {
        Product product = new Product(10, 0, activeNowPromotion);
        assertThrows(PromotionException.class, () -> product.sale(nowTimeStub(), 1));
    }

}