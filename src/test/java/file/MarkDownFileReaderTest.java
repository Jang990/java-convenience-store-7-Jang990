package file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MarkDownFileReaderTest {
    @DisplayName(".md 파일을 읽어올 수 있다.")
    @Test
    void test1() {
        MarkDownFileReader reader = new MarkDownFileReader();
        String path = "classspath:TestProduct.md";

        List<Map<String, String>> result = reader.read(path);

        assertEquals(result.size(), 2);
        assertEquals(result, List.of(
                Map.of(
                        "ID","1",
                        "이름", "콜라"
                ),
                Map.of(
                        "ID","2",
                        "이름", "컵라면"
                )
        ));
    }

}