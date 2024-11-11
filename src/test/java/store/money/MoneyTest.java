package store.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    private static final Money ONE = new Money(1);
    private static final Money TWO = new Money(2);
    private static final Money THREE = new Money(3);
    private static final Money FOUR = new Money(4);

    @DisplayName("마이너스 돈은 불가능하다.")
    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1));
    }

    @DisplayName("Money 간 더하기 연산이 가능하다.")
    @Test
    void test2() {
        assertEquals(ONE.plus(TWO), THREE);
        assertEquals(THREE.plus(ONE), FOUR);
    }

    @DisplayName("Money 곱하기 연산이 가능하다.")
    @Test
    void test3() {
        assertEquals(ONE.times(3), THREE);
        assertEquals(TWO.times(2), FOUR);
    }
}