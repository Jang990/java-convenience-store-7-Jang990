package product.prototype;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import product.PromotionDuration;
import product.PromotionDurationStub;
import product.exception.PromotionException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NEWPromotionTest {

    static final LocalDate now = LocalDate.from(DateTimes.now());

    private static NEWPromotion promotion(PromotionDuration duration) {
        return NEWPromotionTestBuilder.builder()
                .duration(duration)
                .build();
    }

    @DisplayName("현재 시간이 프로모션 기간에 포함된 경우 이용가능하다.")
    @Test
    void test1() {
        assertTrue(promotion(PromotionDurationStub.withInPeriod).isAvailable(now));
        assertFalse(promotion(PromotionDurationStub.outsidePeriod).isAvailable(now));
    }

    @DisplayName("프로모션 이름을 설정하고 확인할 수 있다.")
    @Test
    void test2() {
        String promotionName = "MD추천상품";
        NEWPromotion promotion = NEWPromotionTestBuilder.builder()
                .name(promotionName)
                .build();

        assertEquals(promotion.toString(), promotionName);
    }

    @DisplayName("프로모션 무료 제공 수량이 있다면 예외가 발생하고 무료 제공 수량을 공지한다.")
    @ParameterizedTest(name = "{0} 행사에 요청한 {1} : 무시된 무료 제공 상품 {2}개")
    @MethodSource("ignoredFreeExceptionOptions")
    void test3(PromotionType buyNToGetN, ProductQuantity requested, Quantity ignoredFree) {
        NEWPromotion promotion = NEWPromotionTestBuilder.builder()
                .type(buyNToGetN)
                .build();
        PromotionException exception = assertThrows(PromotionException.class, () -> promotion.calculateFree(requested));
        assertEquals(ignoredFree, exception.getErrorQuantity());
    }

    static Stream<Arguments> ignoredFreeExceptionOptions() {
        return Stream.of(
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(2, 0), toQuantity(1)),
                Arguments.of(PromotionType.ONE_PLUS_ONE, toProductQuantity(1, 0), toQuantity(1))
        );
    }

    @DisplayName("프로모션 수량이 없다면 공짜 수량은 0을 반환한다.")
    @Test
    void test4() throws PromotionException {
        NEWPromotion promotion = NEWPromotionTestBuilder.builder()
                .type(PromotionType.ONE_PLUS_ONE)
                .build();
        ProductQuantity requested = toProductQuantity(0, 10);

        assertEquals(toQuantity(0), promotion.calculateFree(requested));
    }

    private static ProductQuantity toProductQuantity(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private static Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

}