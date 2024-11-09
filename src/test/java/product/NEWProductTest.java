package product;

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

}