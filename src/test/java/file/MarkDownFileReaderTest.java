package file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarkDownFileReaderTest {
    @DisplayName(".md 파일을 읽어올 수 있다.")
    @ParameterizedTest(name = "{0} 경로의 파일을 읽어온다.")
    @MethodSource("readingFileOptions")
    void test1(String path, List<Map<String,String>> expected) {
        MarkDownFileReader reader = new MarkDownFileReader();
        List<Map<String, String>> result = reader.read(path);

        assertEquals(result.size(), expected.size());
        assertEquals(result, expected);
    }

    static Stream<Arguments> readingFileOptions() {
        return Stream.of(
                Arguments.of("src/test/resources/TestProduct.md",
                        List.of(
                                Map.of("ID","1", "이름", "콜라"),
                                Map.of("ID","2", "이름", "컵라면")
                        )
                ),
                Arguments.of("src/test/resources/TestPromotions.md",
                        List.of(
                                Map.of("name","탄산2+1", "start_date", "2024-01-01"),
                                Map.of("name","MD추천상품", "start_date", "2024-12-31")
                        )
                )
        );
    }

}