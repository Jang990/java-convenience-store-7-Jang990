package product;

import money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @ParameterizedTest(name = "{0}을 {1}개 구매 : 상품 재고 - {2}.{3}")
    @MethodSource("buyOptions")
    void test2(ProductQuantity stock, int buyQuantity, ProductQuantity stockAfterBuy) {
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
    void test5() {
        ProductQuantity productStock = toStock(10, 10);
        String productName = "콜라";
        Money productPrice = toMoney(1000);
        Product product = new Product(productName, productPrice, productStock);

        OrderLine result = product.purchase(toQuantity(10));

        assertEquals(productName, result.getProductName());
        assertEquals(productPrice, result.getProductPrice());
        assertEquals(toQuantity(10), result.getProductToPay());
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