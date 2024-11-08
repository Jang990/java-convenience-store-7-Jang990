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
}