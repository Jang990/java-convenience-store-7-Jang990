package product.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NEWProductTest {
    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        ProductQuantity stock = new ProductQuantity(10, 0);
        NEWProduct product = new NEWProduct("ABC", 1000,stock);
        assertThrows(IllegalStateException.class, () -> product.purchase(11));
    }

    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @ParameterizedTest(name = "{0}을 {1}개 구매 : 상품 재고 - {2}.{3}")
    @MethodSource("buyOptions")
    void test2(ProductQuantity stock, int buyQuantity, ProductQuantity stockAfterBuy) {
        NEWProduct product = new NEWProduct("ABC", 1000, stock);
        product.purchase(buyQuantity);

        assertEquals(stockAfterBuy, product.getStock());
    }

    static Stream<Arguments> buyOptions() {
        return Stream.of(
                Arguments.of(new ProductQuantity(2, 2), 1, new ProductQuantity(1, 2)),
                Arguments.of(new ProductQuantity(2, 2), 2, new ProductQuantity(0, 2)),
                Arguments.of(new ProductQuantity(2, 2), 3, new ProductQuantity(0, 1)),
                Arguments.of(new ProductQuantity(2, 2), 4, new ProductQuantity(0, 0))
        );
    }

    @DisplayName("상품을 0개 구매하려 한다면 예외가 발생한다.")
    @Test
    void test3() {
        ProductQuantity productStock = new ProductQuantity(1, 0);
        NEWProduct product = new NEWProduct("ABC", 1000, productStock);
        assertThrows(IllegalArgumentException.class, () -> product.purchase(0));
    }

    @DisplayName("상품의 가격, 재고, 이름을 설정할 수 있다.")
    @Test
    void test4() {
        ProductQuantity productStock = new ProductQuantity(1, 0);
        NEWProduct product = new NEWProduct("콜라", 1000, productStock);

        assertEquals("콜라", product.getName());
        assertEquals(1000, product.getPrice());
        assertEquals(productStock, product.getStock());
    }

    @DisplayName("구매 후 구매정보를 확인할 수 있어야 한다.")
    @Test
    void test5() {
        ProductQuantity productStock = new ProductQuantity(10, 10);
        String productName = "콜라";
        int productPrice = 1000;
        NEWProduct product = new NEWProduct(productName, productPrice, productStock);

        OrderLine result = product.purchase(10);

        assertEquals(productName, result.getProductName());
        assertEquals(productPrice, result.getProductPrice());
        assertEquals(new ProductQuantity(10, 0), result.getPurchaseQuantity());
    }

}