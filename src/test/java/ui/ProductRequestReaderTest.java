package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.dto.ProductRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestReaderTest {
    private final ProductRequestReader reader = new ProductRequestReader();

    @DisplayName("사용자 입력을 ProductRequest 리스트로 바꿔준다")
    @Test
    void test1() {
        String input = "[콜라-3],[에너지바-5]";
        List<ProductRequest> result = reader.read(input);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getProductName(), "콜라");
        assertEquals(result.get(0).getQuantity(), 3);

        System.out.println(result.get(1));
        assertEquals(result.get(1).getProductName(), "에너지바");
        assertEquals(result.get(1).getQuantity(), 5);
    }

    @DisplayName("잘못된 문자열이 들어오면 예외가 발생한다.")
    @ParameterizedTest(name = "잘못된 문자열 : {0}")
    @ValueSource(strings = {"[]", "[-]", "[[-]]", "[콜라-a]", "abc", "[a-1][b-b]"})
    void test2(String input) {
        assertThrows(IllegalArgumentException.class, () -> reader.read(input));
    }
}