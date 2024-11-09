package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NEWProductTest {
    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        NEWProduct product = new NEWProduct(10);
        assertThrows(IllegalStateException.class, () -> product.buy(11));
    }

    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @Test
    void test2() {
        NEWProduct product = new NEWProduct(2,2);
        product.buy(3);

        assertEquals(0, product.getPromotionStock());
        assertEquals(1, product.getNormalStock());
    }

}