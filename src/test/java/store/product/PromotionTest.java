package store.product;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.product.exception.MissedPromotionBenefitException;
import store.product.exception.PartialProductExclusionException;
import store.product.exception.PromotionException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    static final LocalDate now = LocalDate.from(DateTimes.now());

    private static Promotion promotion(PromotionDuration duration) {
        return PromotionTestBuilder.builder()
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
        Promotion promotion = PromotionTestBuilder.builder()
                .name(promotionName)
                .build();

        assertEquals(promotion.getName(), promotionName);
    }

    @DisplayName("프로모션 무료 제공 수량이 있다면 예외가 발생하고 무료 제공 수량을 공지한다.")
    @ParameterizedTest(name = "{0} 행사에 요청한 {1} : 무시된 무료 제공 상품 {2}개")
    @MethodSource("ignoredFreeExceptionOptions")
    void test3(PromotionType buyNToGetN, ProductQuantity requested, Quantity ignoredFree) {
        Promotion promotion = PromotionTestBuilder.builder()
                .type(buyNToGetN)
                .build();
        MissedPromotionBenefitException exception = assertThrows(
                MissedPromotionBenefitException.class,
                () -> promotion.calculateFree(requested)
        );
        assertEquals(ignoredFree, exception.getMissingFreeQuantity());
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
        Promotion promotion = PromotionTestBuilder.builder()
                .type(PromotionType.ONE_PLUS_ONE)
                .build();
        ProductQuantity requested = toProductQuantity(0, 10);

        assertEquals(toQuantity(0), promotion.calculateFree(requested));
    }

    @DisplayName("프로모션을 적용했을 때의 무료 수량을 확인할 수 있다.")
    @ParameterizedTest(name = "{0} 행사에 요청한 {1} : 무료 제공 상품 {2}개")
    @MethodSource("calculateFreeOptions")
    void test5(PromotionType buyNToGetN, ProductQuantity requested, Quantity free) throws PromotionException {
        Promotion promotion = PromotionTestBuilder.builder()
                .type(buyNToGetN)
                .build();
        assertEquals(free, promotion.calculateFree(requested));
    }

    static Stream<Arguments> calculateFreeOptions() {
        return Stream.of(
                Arguments.of(PromotionType.ONE_PLUS_ONE, toProductQuantity(4, 0), toQuantity(2)),
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(6, 0), toQuantity(2))
        );
    }

    @DisplayName("몇 개는 프로모션이 적용되지만 몇 개는 정가구매 해야 한다면 예외가 발생한다. 정가구매 수량을 공지한다.")
    @ParameterizedTest(name = "{0} 행사에 요청한 {1} : 정가 구매 상품 {2}개")
    @MethodSource("ignoredFreeExceptionOptions2")
    void test6(PromotionType buyNToGetN, ProductQuantity requested, Quantity ignoredFree) {
        Promotion promotion = PromotionTestBuilder.builder()
                .type(buyNToGetN)
                .build();
        PartialProductExclusionException exception = assertThrows(
                PartialProductExclusionException.class,
                () -> promotion.calculateFree(requested)
        );
        assertEquals(ignoredFree, exception.getNonPromotionalQuantity());
    }

    static Stream<Arguments> ignoredFreeExceptionOptions2() {
        return Stream.of(
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(3, 2), toQuantity(2)),
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(4, 0), toQuantity(1)),
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(3, 3), toQuantity(3)),
                Arguments.of(PromotionType.ONE_PLUS_ONE, toProductQuantity(7, 3), toQuantity(4))
        );
    }

    @DisplayName("예외를 신경쓰지 않고 증정품 만을 계산할 수 있다.")
    @ParameterizedTest(name = "{0} 행사. {1}개 구매 : 증정품 {2}개")
    @MethodSource("freeWithoutExceptionOptions")
    void test7(PromotionType buyNToGetN, ProductQuantity requested, Quantity expectedFree) {
        Promotion promotion = PromotionTestBuilder.builder()
                .type(buyNToGetN)
                .build();

        assertEquals(promotion.calculateFreeWithoutException(requested), expectedFree);
    }

    static Stream<Arguments> freeWithoutExceptionOptions() {
        return Stream.of(
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(3, 2), toQuantity(1)),
                Arguments.of(PromotionType.TWO_PLUS_ONE, toProductQuantity(2, 0), toQuantity(0))
        );
    }

    private static ProductQuantity toProductQuantity(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private static Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }

}