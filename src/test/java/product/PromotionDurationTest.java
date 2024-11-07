package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromotionDurationTest {
    private final LocalDate now = LocalDate.now();

    @DisplayName("기간은 종료 시간이 시작 시간보다 느릴 수 없다.")
    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class,
                () -> new PromotionDuration(now, now.minusDays(1)));
    }
}