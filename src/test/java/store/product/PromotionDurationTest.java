package store.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotionDurationTest {
    private static final LocalDate now = LocalDate.now();


    @DisplayName("기간은 종료 시간이 시작 시간보다 느릴 수 없다.")
    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class,
                () -> new PromotionDuration(now, now.minusDays(1)));
    }

    @DisplayName("현재 시간이 프로모션 기간인지 확인할 수 있다.")
    @ParameterizedTest(name = "프로모션 기간 : {0} ~ {1}")
    @MethodSource("promotionDurationOptions")
    void test1(LocalDate startDate, LocalDate endDate, boolean expected) {
        PromotionDuration duration = new PromotionDuration(startDate, endDate);
        assertEquals(duration.isWithIn(now), expected);
    }

    static Stream<Arguments> promotionDurationOptions() {
        return Stream.of(
                Arguments.of(now.minusDays(1), now.plusDays(1), true),
                Arguments.of(now.minusDays(0), now.plusDays(0), true),
                Arguments.of(now.minusDays(1), now.minusDays(1), false)
        );
    }
}