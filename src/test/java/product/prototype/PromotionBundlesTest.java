package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionBundlesTest {
    @DisplayName("남는 수량이 있는지 확인할 수 있다.")
    @Test
    void test1() {
        assertTrue(new PromotionBundles(toQuantity(10), PromotionType.TWO_PLUS_ONE).hasRemainder());
        assertFalse(new PromotionBundles(toQuantity(10), PromotionType.ONE_PLUS_ONE).hasRemainder());
    }

    @DisplayName("나머지 없는 번들을 만드는데 부족한 수량이 얼마인지 알 수 있다.")
    @Test
    void test2() {
        assertEquals(new PromotionBundles(toQuantity(10), PromotionType.TWO_PLUS_ONE).getShortFall(), toQuantity(2));
        assertEquals(new PromotionBundles(toQuantity(10), PromotionType.ONE_PLUS_ONE).getShortFall(), toQuantity(0));
    }

    private Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

}