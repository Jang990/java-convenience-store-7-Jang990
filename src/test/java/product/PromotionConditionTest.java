package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotionConditionTest {

    static final Quantity ONE = new Quantity(1);
    static final Quantity TWO = new Quantity(2);
    static final PromotionCondition ONE_PLUS_ONE = new PromotionCondition(ONE, ONE);
    static final PromotionCondition TWO_PLUS_ONE = new PromotionCondition(TWO, ONE);
    static final PromotionCondition TWO_PLUS_TWO = new PromotionCondition(TWO, TWO);

    @DisplayName("공짜로 얻을 수 있는 수량을 계산할 수 있다.")
    @ParameterizedTest(name = "{0} : {1}개 사면 {2}개가 공짜")
    @MethodSource("calculateDiscountOptions")
    void test1(
            PromotionCondition condition,
            int requestedProductAmount,
            int expectedAmount) {
        assertEquals(
                new Quantity(expectedAmount),
                condition.calculateFreeQuantity(new Quantity(requestedProductAmount))
        );
    }

    static Stream<Arguments> calculateDiscountOptions() {
        return Stream.of(
                Arguments.of(ONE_PLUS_ONE, 2, 1),
                Arguments.of(TWO_PLUS_ONE, 3, 1),
                Arguments.of(TWO_PLUS_TWO, 4, 2),

                Arguments.of(ONE_PLUS_ONE, 1, 0),
                Arguments.of(TWO_PLUS_ONE, 2, 0),
                Arguments.of(TWO_PLUS_TWO, 2, 0),

                Arguments.of(ONE_PLUS_ONE, 7, 3),
                Arguments.of(TWO_PLUS_ONE, 5, 1),
                Arguments.of(TWO_PLUS_TWO, 3, 0)
        );
    }

}