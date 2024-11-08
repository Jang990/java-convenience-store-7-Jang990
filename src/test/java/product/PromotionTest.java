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

    @DisplayName("프로모션을 적용하면 할인된 가격을 확인할 수 있다.")
    @ParameterizedTest(name = "{2}개를 구매할 때 프로모션 적용 시 {0}개 구매 가격으로 구매하고 {1}개는 무료 제공")
    @MethodSource("applyOptions")
    void test3(int requiredQuantity, int freeQuantity, int quantityToBuy) {
        Promotion promotion = PromotionTestBuilder.builder()
                .condition(requiredQuantity, freeQuantity)
                .duration(PromotionDurationStub.withInPeriod)
                .build();
        Money productPrice = new Money(1000);

        assertEquals(
                promotion.apply(productPrice, quantityToBuy),
                productPrice.times(requiredQuantity)
        );
    }

    static Stream<Arguments> applyOptions() {
        return Stream.of(
                Arguments.of(2, 1, 2+1),
                Arguments.of(1, 1, 1+1)
        );
    }

}