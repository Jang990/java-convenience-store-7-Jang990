package product;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {
    static final LocalDate now = LocalDate.from(DateTimes.now());

    @DisplayName("현재 시간이 프로모션 기간에 포함된 경우 이용가능하다.")
    @ParameterizedTest(name = "프로모션 기간 : {0} ~ {1}")
    @MethodSource("promotionDurationOptions")
    void test1(LocalDate startDate, LocalDate endDate, boolean expected) {
        Promotion promotion = new Promotion(startDate, endDate, "");
        assertEquals(promotion.isAvailable(now), expected);
    }

    static Stream<Arguments> promotionDurationOptions() {
        return Stream.of(
                Arguments.of(now.minusDays(1), now.plusDays(1), true),
                Arguments.of(now.minusDays(0), now.plusDays(0), true),
                Arguments.of(now.minusDays(1), now.minusDays(1), false)
        );
    }

    @DisplayName("프로모션 이름을 확인할 수 있다.")
    @Test
    void test2() {
        String promotionName = "MD추천상품";
        Promotion promotion = new Promotion(now, now, promotionName);
        assertEquals(promotion.toString(), promotionName);
    }

}