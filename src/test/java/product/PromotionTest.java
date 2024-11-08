package product;

import camp.nextstep.edu.missionutils.DateTimes;
import money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {
    static final LocalDate now = LocalDate.from(DateTimes.now());
    static final Quantity ONE = new Quantity(1);
    static final Quantity TWO = new Quantity(2);
    static final PromotionCondition ONE_PLUS_ONE = new PromotionCondition(ONE, ONE);
    static final PromotionCondition TWO_PLUS_ONE = new PromotionCondition(TWO, ONE);
    static final PromotionCondition ONE_PLUS_TWO = new PromotionCondition(ONE, TWO);

    private static Promotion promotion(PromotionDuration duration) {
        return PromotionTestBuilder.builder()
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
        Promotion promotion = PromotionTestBuilder.builder()
                .name(promotionName)
                .build();

        assertEquals(promotion.toString(), promotionName);
    }

    @DisplayName("프로모션을 적용하면 가격을 할인 받을 수 있다.")
    @ParameterizedTest(name = "{2}개를 {1}개 가격으로 구매")
    @MethodSource("applyOptions")
    void test3(PromotionCondition condition, int pay, int buy) {
        Promotion promotion = PromotionTestBuilder.builder()
                .condition(condition)
                .duration(PromotionDurationStub.withInPeriod)
                .build();
        Money productPrice = new Money(1000);

        assertEquals(
                promotion.apply(productPrice, new Quantity(buy)),
                productPrice.times(pay)
        );
    }

    static Stream<Arguments> applyOptions() {
        return Stream.of(
                Arguments.of(TWO_PLUS_ONE, 2, 2+1),
                Arguments.of(ONE_PLUS_ONE, 1, 1+1),

                Arguments.of(TWO_PLUS_ONE, 4, 2+1+2+1),
                Arguments.of(TWO_PLUS_ONE, 2, 2+1+1),

                Arguments.of(ONE_PLUS_TWO, 1, 1+2)
        );
    }

}