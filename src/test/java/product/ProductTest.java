package product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @DisplayName("상품 구매 시 재고가 부족하다면 예외가 발생한다.")
    @Test
    void test1() {
        Product product = new Product(10);
        assertThrows(IllegalStateException.class, () -> product.sale(11));
    }

    @DisplayName("프로모션을 적용할 수 있지만, 프로모션 재고가 없다면 예외가 발생한다.")
    @Test
    void test2() {
        Promotion promotion = new Promotion();
        Product product = new Product(10, 0, promotion);
        assertThrows(PromotionException.class, () -> product.sale(1));
    }

}