package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.dto.ProductRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestReaderTest {
    private final ProductRequestReader reader = new ProductRequestReader();

    @DisplayName("사용자 입력을 ProductRequest 리스트로 바꿔준다")
    @Test
    void test1() {
        String input = "[콜라-3],[에너지바-5]";
        assertEquals(reader.read(input),
                List.of(
                        new ProductRequest("콜라", 3),
                        new ProductRequest("에너지바", 5)
                )
        );
    }

}