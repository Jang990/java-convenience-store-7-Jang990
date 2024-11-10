package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import product.OrderLine;
import product.Product;
import product.ProductQuantity;
import product.Quantity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @DisplayName("프로모션 재고가 부족할 때 일반 재고를 사용한다.")
    @ParameterizedTest(name = "{0}을 {1}개 구매 : 상품 재고 - {2}.{3}")
    @MethodSource("buyOptions")
    void test2(ProductQuantity stock, int buyQuantity, ProductQuantity stockAfterBuy) {
        Product product = new Product("ABC", 1000, stock);
        product.purchase(buyQuantity);

        assertEquals(stockAfterBuy, product.getStock());
    }

    static Stream<Arguments> buyOptions() {
        return Stream.of(
                Arguments.of(toProductQuantity(2, 2), 1, toProductQuantity(1, 2)),
                Arguments.of(toProductQuantity(2, 2), 2, toProductQuantity(0, 2)),
                Arguments.of(toProductQuantity(2, 2), 3, toProductQuantity(0, 1)),
                Arguments.of(toProductQuantity(2, 2), 4, toProductQuantity(0, 0))
        );
    }

    @DisplayName("상품을 0개 구매하려 한다면 예외가 발생한다.")
    @Test
    void test3() {
        ProductQuantity productStock = toProductQuantity(1, 0);
        Product product = new Product("ABC", 1000, productStock);
        assertThrows(IllegalArgumentException.class, () -> product.purchase(0));
    }

    @DisplayName("상품의 가격, 재고, 이름을 설정할 수 있다.")
    @Test
    void test4() {
        ProductQuantity productStock = toProductQuantity(1, 0);
        Product product = new Product("콜라", 1000, productStock);

        assertEquals("콜라", product.getName());
        assertEquals(1000, product.getPrice());
        assertEquals(productStock, product.getStock());
    }

    @DisplayName("구매 후 구매정보를 확인할 수 있어야 한다.")
    @Test
    void test5() {
        ProductQuantity productStock = toProductQuantity(10, 10);
        String productName = "콜라";
        int productPrice = 1000;
        Product product = new Product(productName, productPrice, productStock);

        OrderLine result = product.purchase(10);

        assertEquals(productName, result.getProductName());
        assertEquals(productPrice, result.getProductPrice());
        assertEquals(toProductQuantity(10, 0), result.getPurchaseQuantity());
    }

    private static ProductQuantity toProductQuantity(int promotion, int normal) {
        return new ProductQuantity(toQuantity(promotion), toQuantity(normal));
    }

    private static Quantity toQuantity(int quantity) {
        return new Quantity(quantity);
    }
}