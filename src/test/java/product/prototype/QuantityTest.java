package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @DisplayName("as")
    @Test
    void te2() {
        System.out.println(
                Quantity.isEmpty(new Bundles(new Quantity(0), new Quantity(1)))
        );
    }

}