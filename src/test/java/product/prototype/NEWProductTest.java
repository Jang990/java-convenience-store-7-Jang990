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
    @ParameterizedTest(name = "{0}을 {1}개 구매 : 상품 재고 - {2}.{3}")
    @MethodSource("buyOptions")
    void test2(ProductQuantity productQuantity, int buyQuantity, int expectedPromotion, int expectedNormal) {
        NEWProduct product = new NEWProduct(productQuantity.promotion, productQuantity.normal);
        product.buy(buyQuantity);

        assertEquals(expectedPromotion, product.getPromotionStock());
        assertEquals(expectedNormal, product.getNormalStock());
    }

    static Stream<Arguments> buyOptions() {
        ProductQuantity quantity = new ProductQuantity(2, 2);
        return Stream.of(
                Arguments.of(quantity, 1, 1, 2),
                Arguments.of(quantity, 2, 0, 2),
                Arguments.of(quantity, 3, 0, 1),
                Arguments.of(quantity, 4, 0, 0)
        );
    }

    static class ProductQuantity {
        public int promotion;
        public int normal;

        public ProductQuantity(int promotion, int normal) {
            this.promotion = promotion;
            this.normal = normal;
        }

        @Override
        public String toString() {
            return "상품재고 {" + "프로모션=" + promotion + ", 일반=" + normal + '}';
        }
    }

}