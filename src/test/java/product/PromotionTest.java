package product;

import camp.nextstep.edu.missionutils.DateTimes;
import money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {
    static final LocalDate now = LocalDate.from(DateTimes.now());

    private static Promotion promotion(PromotionDuration duration) {
        return new Promotion(duration, "이벤트");
    }

    @DisplayName("현재 시간이 프로모션 기간에 포함된 경우 이용가능하다.")
    @Test
    void test1() {
        assertTrue(promotion(PromotionDurationStub.withInPeriod).isAvailable(now));
        assertFalse(promotion(PromotionDurationStub.outsidePeriod).isAvailable(now));
    }

    @DisplayName("프로모션 이름을 확인할 수 있다.")
    @Test
    void test2() {
        String promotionName = "MD추천상품";
        Promotion promotion = new Promotion(
                PromotionDurationStub.withInPeriod,
                promotionName
        );
        assertEquals(promotion.toString(), promotionName);
    }

    @DisplayName("프로모션을 적용하면 할인된 가격을 확인할 수 있다.")
    @Test
    void test3() {
        Promotion activePromotion = new Promotion(
                2, 1, "이벤트",
                PromotionDurationStub.withInPeriod
        );
        Money productPrice = new Money(1000);
        int saleQuantity = 3;

        Money discountedPrice = activePromotion.apply(productPrice, saleQuantity);

        assertEquals(discountedPrice, new Money(2000));
    }

}