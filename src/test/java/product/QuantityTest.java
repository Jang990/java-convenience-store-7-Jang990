package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @DisplayName("수량이 마이너스로 주어지면 예외가 발생한다.")
    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }
}