package store.product;

import store.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.product.exception.MissedPromotionBenefitException;
import store.product.exception.PromotionException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @ParameterizedTest(name = "{0}을 {1}개 구매 : 상품 재고 - {2}.{3}")
    @MethodSource("buyOptions")
    void test2(ProductQuantity stock, int buyQuantity, ProductQuantity stockAfterBuy) throws PromotionException {
        Product product = new Product("ABC", toMoney(1000), stock);
        product.purchase(toQuantity(buyQuantity));

        assertEquals(stockAfterBuy, product.getStock());
    }

    static Stream<Arguments> buyOptions() {
        return Stream.of(
                Arguments.of(toStock(2, 2), 1, toStock(1, 2)),
                Arguments.of(toStock(2, 2), 2, toStock(0, 2)),
                Arguments.of(toStock(2, 2), 3, toStock(0, 1)),
                Arguments.of(toStock(2, 2), 4, toStock(0, 0))
        );
    }

    @DisplayName("상품을 0개 구매하려 한다면 예외가 발생한다.")
    @Test
    void test3() {
        ProductQuantity productStock = toStock(1, 0);
        Product product = new Product("ABC", toMoney(1000), productStock);
        assertThrows(IllegalArgumentException.class, () -> product.purchase(toQuantity(0)));
    }

    @DisplayName("상품의 가격, 재고, 이름을 설정할 수 있다.")
    @Test
    void test4() {
        ProductQuantity productStock = toStock(1, 0);
        Product product = new Product("콜라", toMoney(1000), productStock);

        assertEquals("콜라", product.getName());
        assertEquals(toMoney(1000), product.getPrice());
        assertEquals(productStock, product.getStock());
    }

    @DisplayName("구매 후 구매정보를 확인할 수 있어야 한다.")
    @Test
    void test5() throws PromotionException {
        ProductQuantity productStock = toStock(10, 10);
        String productName = "콜라";
        Money productPrice = toMoney(1000);
        Product product = new Product(productName, productPrice, productStock);

        OrderLine result = product.purchase(toQuantity(10));

        assertEquals(productName, result.getProductName());
        assertEquals(productPrice, result.getProductPrice());
        Assertions.assertEquals(toQuantity(10), result.getProductToPay());
    }

    @DisplayName("프로모션 적용 시 프로모션으로 받은 공짜 상품 수량과 지불해야할 수량을 파악할 수 있다.")
    @ParameterizedTest
    @MethodSource("applyPromotionOptions")
    void test6(int promotionStock, Promotion promotion, int purchase,
               int expectedToPay, int expectedFree) throws PromotionException {
        Product product = new Product(
                "콜라", toMoney(1000),
                toStock(promotionStock, 0),
                promotion
        );

        OrderLine result = product.purchase(toQuantity(purchase));

        Assertions.assertEquals(toQuantity(expectedToPay), result.getProductToPay());
        Assertions.assertEquals(toQuantity(expectedFree), result.getFreeProduct());
    }

    static Stream<Arguments> applyPromotionOptions() {
        return Stream.of(
                Arguments.of(10, PromotionTestBuilder.ONE_PLUS_ONE, 10, 5, 5),
                Arguments.of(10, PromotionTestBuilder.TWO_PLUS_ONE, 6, 4, 2)
        );
    }

    @DisplayName("프로모션에서 증정품을 놓쳤다는 예외가 발생하면 구매를 중단한다.")
    @Test
    void test7() {
        Product product = new Product(
                "콜라", toMoney(1000),
                toStock(3, 0),
                PromotionTestBuilder.TWO_PLUS_ONE
        );

        assertThrows(MissedPromotionBenefitException.class, () -> product.purchase(toQuantity(2)));
    }

    @DisplayName("프로모션에서 증정품을 놓친 예외가 발생했지만 재고가 없다면 예외를 무시하고 구매를 진행한다.")
    @Test
    void test8() throws PromotionException {
        Product product = new Product(
                "콜라", toMoney(1000),
                toStock(2, 0),
                PromotionTestBuilder.TWO_PLUS_ONE
        );

        OrderLine orderLine = product.purchase(toQuantity(2));
        assertEquals(orderLine.getProductToPay(), toQuantity(2));
        assertEquals(orderLine.getFreeProduct(), toQuantity(0));
    }

    @DisplayName("프로모션에서 증정품을 놓친 예외가 발생했지만 프로모션을 적용할 재고가 없다면 구매를 종료한다.")
    @Test
    void test9() throws PromotionException {
        Product product = new Product(
                "콜라", toMoney(1000),
                toStock(2, 1),
                PromotionTestBuilder.TWO_PLUS_ONE
        );

        OrderLine orderLine = product.purchase(toQuantity(2));
        assertEquals(orderLine.getProductToPay(), toQuantity(2));
        assertEquals(orderLine.getFreeProduct(), toQuantity(0));
    }

    private static Money toMoney(int amount) {
        return new Money(amount);
    }
    private static ProductQuantity toStock(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private static Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }
}