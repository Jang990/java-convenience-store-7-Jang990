package product.prototype;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.PromotionDuration;
import product.PromotionDurationStub;

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

}