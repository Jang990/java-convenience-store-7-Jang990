package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductQuantityTest {
    @DisplayName("프로모션 수량과 기본 수량이 같아야 같은 객체다.")
    @Test
    void test1() {
        assertTrue(
                new ProductQuantity(1,1)
                        .equals(new ProductQuantity(1,1))
        );

        assertFalse(
                new ProductQuantity(1,1)
                        .equals(new ProductQuantity(0,1))
        );

        assertFalse(
                new ProductQuantity(1,1)
                        .equals(new ProductQuantity(1,0))
        );
    }

    @DisplayName("프로모션 재고가 부족할 때 일반 재고가 감소된다.")
    @Test
    void test2() {
        ProductQuantity quantity = new ProductQuantity(2, 2);
        assertEquals(new ProductQuantity(1, 2), quantity.decrease(1));
        assertEquals(new ProductQuantity(0, 2), quantity.decrease(2));
        assertEquals(new ProductQuantity(0, 1), quantity.decrease(3));
        assertEquals(new ProductQuantity(0, 0), quantity.decrease(4));
    }

}