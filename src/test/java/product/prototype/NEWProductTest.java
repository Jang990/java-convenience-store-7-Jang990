package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NEWProductTest {
    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        NEWProduct product = new NEWProduct(10, 0);
        assertThrows(IllegalStateException.class, () -> product.buy(11));
    }

    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @ParameterizedTest(name = "상품({0}.{1})을 {2}개 구매 : 상품 재고 - {3}.{4}")
    @MethodSource("buyOptions")
    void test2(int promotion, int normal, int buyQuantity, int expectedPromotion, int expectedNormal) {
        NEWProduct product = new NEWProduct(promotion,normal);
        product.buy(buyQuantity);

        assertEquals(expectedPromotion, product.getPromotionStock());
        assertEquals(expectedNormal, product.getNormalStock());
    }

    static Stream<Arguments> buyOptions() {
        return Stream.of(
                Arguments.of(2, 2, 1, 1, 2),
                Arguments.of(2, 2, 2, 0, 2),
                Arguments.of(2, 2, 3, 0, 1),
                Arguments.of(2, 2, 4, 0, 0)
        );
    }

}