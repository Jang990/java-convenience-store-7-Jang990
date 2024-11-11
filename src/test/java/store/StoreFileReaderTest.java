package store;

import file.HeaderFileReaderStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import product.Promotion;

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

    @DisplayName("프로모션 파일을 읽어옵니다.")
    @Test
    void test1() {
        StoreFileReader reader = createStoreFileReader(samplePromotionData);
        List<Promotion> promotions = reader.readPromotions();
        assertEquals(promotions.size(), 2);
        assertThat(promotions.stream().map(Promotion::toString).toList()).contains("탄산2+1", "반짝할인");
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

    private static StoreFileReader createStoreFileReader(List<Map<String, String>> sample) {
        HeaderFileReaderStub headerFileReaderStub = new HeaderFileReaderStub();
        headerFileReaderStub.setReadValue(sample);
        return new StoreFileReader(headerFileReaderStub);
    }

}