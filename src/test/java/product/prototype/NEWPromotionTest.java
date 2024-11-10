package product.prototype;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.PromotionDuration;
import product.PromotionDurationStub;
import product.exception.PromotionException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NEWPromotionTest {

    static final LocalDate now = LocalDate.from(DateTimes.now());

    private static NEWPromotion promotion(PromotionDuration duration) {
        return NEWPromotionTestBuilder.builder()
                .duration(duration)
                .build();
    }

    @DisplayName("현재 시간이 프로모션 기간에 포함된 경우 이용가능하다.")
    @Test
    void test1() {
        assertTrue(promotion(PromotionDurationStub.withInPeriod).isAvailable(now));
        assertFalse(promotion(PromotionDurationStub.outsidePeriod).isAvailable(now));
    }

    @DisplayName("프로모션 이름을 설정하고 확인할 수 있다.")
    @Test
    void test2() {
        String promotionName = "MD추천상품";
        NEWPromotion promotion = NEWPromotionTestBuilder.builder()
                .name(promotionName)
                .build();

        assertEquals(promotion.toString(), promotionName);
    }

    @DisplayName("프로모션 단위보다 적게 구매할 시 예외가 발생하고 얼마나 부족한지를 알려준다.")
    @Test
    void test3() {
        NEWPromotion promotion = NEWPromotionTestBuilder.builder()
                .type(PromotionType.TWO_PLUS_ONE)
                .build();
        ProductQuantity requested = toProductQuantity(1, 0);

        PromotionException exception = assertThrows(PromotionException.class, () -> promotion.calculateFree(requested));
        assertEquals(toQuantity(2), exception.getErrorQuantity());
    }

    private ProductQuantity toProductQuantity(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

}