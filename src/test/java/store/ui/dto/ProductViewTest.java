package store.ui.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.money.Money;
import store.product.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ProductViewTest {
    @DisplayName("보유 상품 출력을 위한 기능을 한다.")
    @Test
    void test1() {
        LocalDate now = LocalDate.now();
        Promotion promotion = new Promotion(PromotionType.TWO_PLUS_ONE, new PromotionDuration(now, now), "탄산 2+1");
        Product product = new Product("콜라", new Money(1000), new ProductQuantity(Quantity.of(10), Quantity.of(0)), promotion);
        ProductView productView = new ProductView(product);

        assertThat(productView.toString()).contains(
                "- 콜라 1,000원 재고없음 탄산 2+1",
                "- 콜라 1,000원 10개"
        );
    }

}