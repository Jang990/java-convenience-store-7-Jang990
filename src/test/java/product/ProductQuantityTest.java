package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.ProductQuantity;
import product.Quantity;

import static org.junit.jupiter.api.Assertions.*;

class ProductQuantityTest {
    @DisplayName("프로모션 수량과 기본 수량이 같아야 같은 객체다.")
    @Test
    void test1() {
        assertTrue(
                quantity(1,1)
                        .equals(quantity(1,1))
        );

        assertFalse(
                quantity(1,1)
                        .equals(quantity(0,1))
        );

        assertFalse(
                quantity(1,1)
                        .equals(quantity(1,0))
        );
    }

    @DisplayName("프로모션 재고가 부족할 때 일반 재고가 감소된다.")
    @Test
    void test2() {
        ProductQuantity quantity = quantity(2, 2);
        assertEquals(quantity(1, 2), quantity.decrease(1));
        assertEquals(quantity(0, 2), quantity.decrease(2));
        assertEquals(quantity(0, 1), quantity.decrease(3));
        assertEquals(quantity(0, 0), quantity.decrease(4));
    }

    @DisplayName("많은 재고에서 적은 재고의 차이를 확인하려 하면 예외가 발생한다.")
    @Test
    void test3() {
        ProductQuantity smaller = quantity(1, 1);
        ProductQuantity bigger = quantity(10, 10);
        assertThrows(IllegalArgumentException.class, () -> smaller.calculateDifference(bigger));
    }

    @DisplayName("재고의 차이를 비교할 수 있다.")
    @Test
    void test4() {
        assertEquals(
                quantity(5,5)
                        .calculateDifference(quantity(3,3)),
                quantity(2,2)
        );

        assertEquals(
                quantity(5,10)
                        .calculateDifference(quantity(3,6)),
                quantity(2,4)
        );
    }

    @DisplayName("재고를 감소하려고 시도할 떄 재고가 0보다 작으면 예외가 발생한다.")
    @Test
    void test5() {
        assertThrows(IllegalStateException.class, () -> quantity(1, 1).decrease(10));
    }

    private static ProductQuantity quantity(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private static Quantity toQuantity(int normal) {
        return new Quantity(normal);
    }

}