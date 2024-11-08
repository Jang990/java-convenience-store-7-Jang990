package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    private static final Quantity TWO = new Quantity(2);
    private static final Quantity FIVE = new Quantity(5);
    private static final Quantity SIX = new Quantity(6);
    @DisplayName("수량이 마이너스로 주어지면 예외가 발생한다.")
    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @DisplayName("수량 나누기는 나머지를 버리고 결과를 반환한다.")
    @Test
    void test2() {
        assertEquals(FIVE.divide(TWO), 2);
        assertEquals(SIX.divide(TWO), 3);
    }
}