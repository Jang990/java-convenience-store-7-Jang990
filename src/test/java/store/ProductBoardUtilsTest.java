package store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.money.Money;
import store.product.Product;
import store.product.ProductQuantity;
import store.product.PromotionTestBuilder;
import store.product.Quantity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductBoardUtilsTest {
    private final Product product1 = new Product(
            "AAA", new Money(1000),
            new ProductQuantity(Quantity.EMPTY, Quantity.EMPTY),
            PromotionTestBuilder.TWO_PLUS_ONE
    );

    private final Product product2 = new Product(
            "BBB", new Money(1000),
            new ProductQuantity(Quantity.of(10), Quantity.of(10)),
            PromotionTestBuilder.TWO_PLUS_ONE
    );

    private final List<Product> productBoardList = List.of(product1, product2);
    private final ProductBoard productBoard = new ProductBoard(productBoardList);

    @DisplayName("포함 여부를 확인할 수 있다.")
    @Test
    void test1() {
        ProductBoardUtils utils = new ProductBoardUtils(productBoard);
        assertTrue(utils.contains("AAA"));
        assertTrue(utils.contains("BBB"));
    }

    @DisplayName("요청을 할 만큼 충분한 재고를 가지고 있는지 확인할 수 있다.")
    @Test
    void test2() {
        ProductBoardUtils utils = new ProductBoardUtils(productBoard);
        assertFalse(utils.hasEnoughStock("AAA", 1));
        assertFalse(utils.hasEnoughStock("AAA", 100));
        assertTrue(utils.hasEnoughStock("BBB", 5));
    }
}