package store;

import file.HeaderFileReaderStub;
import money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductQuantity;
import product.Promotion;
import product.Quantity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StoreFileReaderTest {
    public final List<Map<String, String>> samplePromotionData = List.of(
            Map.of(
                    "name","탄산2+1",
                    "buy", "2",
                    "get", "1",
                    "start_date", "2024-01-01",
                    "end_date", "2024-12-31"
            ),
            Map.of(
                    "name","반짝할인",
                    "buy", "1",
                    "get", "1",
                    "start_date", "2024-11-01",
                    "end_date", "2024-11-30"
            )
    );

    public final Map<String, String> product1 = Map.of(
            "name", "콜라",
            "price", "1000",
            "quantity", "10",
            "promotion", "탄산2+1"
    );
    public final Map<String, String> product2 = Map.of(
            "name", "콜라",
            "price", "1000",
            "quantity", "10",
            "promotion", "null"
    );
    public final Map<String, String> product3 = Map.of(
            "name", "사이다",
            "price", "1000",
            "quantity", "7",
            "promotion", "null"
    );
    public final List<Map<String, String>> sampleProductData = List.of(product1, product2, product3);

    @DisplayName("프로모션 파일을 읽어옵니다.")
    @Test
    void test1() {
        LocalDate date = LocalDate.of(2024, 12, 1);
        StoreFileReader reader = createStoreFileReader(samplePromotionData);

        List<Promotion> promotions = reader.readPromotions();

        assertEquals(promotions.size(), 2);
        assertEquals(promotions.get(0).getName(), "탄산2+1");
        assertTrue(promotions.get(0).isAvailable(date));
        assertEquals(promotions.get(1).getName(), "반짝할인");
        assertFalse(promotions.get(1).isAvailable(date));
    }

    @DisplayName("중복된 이름의 프로모션이 있다면 예외가 발생한다.")
    @Test
    void test2() {
        List<Map<String, String>> sample = List.of(Map.of("name", "abc"), Map.of("name", "abc"));
        StoreFileReader reader = createStoreFileReader(sample);
        assertThrows(IllegalArgumentException.class, reader::readPromotions);
    }

    @DisplayName("프로모션 행사 수량을 숫자로 바꿀 수 없으면 예외가 발생한다.")
    @Test
    void test3() {
        List<Map<String, String>> sample = List.of(Map.of("name", "aaa", "buy", "2", "get", "k"));
        StoreFileReader reader = createStoreFileReader(sample);
        assertThrows(IllegalArgumentException.class, reader::readPromotions);
    }

    @DisplayName("상품 파일 정보를 읽어옵니다.")
    @Test
    void test4() {
        List<Promotion> promotions = createStoreFileReader(samplePromotionData).readPromotions();
        StoreFileReader reader = createStoreFileReader(sampleProductData);

        List<Product> products = reader.readProduct(promotions);

        assertEquals(products.size(), 2);
        assertEquals(products.get(0).getName(), "콜라");
        assertEquals(products.get(0).getPrice(), new Money(1000));
        assertNotNull(products.get(0).getPromotion());
        assertEquals(products.get(0).getStock(), new ProductQuantity(new Quantity(10), new Quantity(10)));
        assertEquals(products.get(1).getName(), "사이다");
        assertNull(products.get(1).getPromotion());
    }

    private static StoreFileReader createStoreFileReader(List<Map<String, String>> sample) {
        HeaderFileReaderStub headerFileReaderStub = new HeaderFileReaderStub();
        headerFileReaderStub.setReadValue(sample);
        return new StoreFileReader(headerFileReaderStub);
    }

}