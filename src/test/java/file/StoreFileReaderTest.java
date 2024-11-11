package file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StoreFileReaderTest {
    private static final String TEST_RESOURCE_PREFIX = "src/test/resources/";

    private static String toTestFilePath(String fileName) {
        return TEST_RESOURCE_PREFIX.concat(fileName);
    }

    private StoreFileReader reader;

    @BeforeEach
    void beforeEach() {
        reader = new StoreFileReader();
    }

    @DisplayName("스토어 파일을 읽어올 수 있다.")
    @ParameterizedTest(name = "{0} 경로의 파일을 읽어온다.")
    @MethodSource("readingFileOptions")
    void test1(String fileName, List<Map<String,String>> expected) {
        String filePath = toTestFilePath(fileName);

        StoreFileData result = reader.read(filePath);

        assertEquals(result.getElements(), expected);
    }

    static Stream<Arguments> readingFileOptions() {
        return Stream.of(
                Arguments.of("TestProduct.md",
                        List.of(
                                Map.of("ID","1", "이름", "콜라"),
                                Map.of("ID","2", "이름", "컵라면")
                        )
                ),
                Arguments.of("TestPromotions.md",
                        List.of(
                                Map.of("name","탄산2+1", "start_date", "2024-01-01"),
                                Map.of("name","MD추천상품", "start_date", "2024-12-31")
                        )
                )
        );
    }

    @DisplayName("헤더 정보와 요소들의 숫자가 일치하지 않는다면 예외가 발생한다.")
    @Test
    void test2() {
        String path = toTestFilePath("InvalidHeader.md");
        assertThrows(IllegalArgumentException.class, () -> reader.read(path));
    }

    @DisplayName("빈 파일은 헤더가 없기 때문에 읽을 수 없다.")
    @Test
    void test3() {
        String path = toTestFilePath("Empty.md");
        assertThrows(IllegalArgumentException.class, () -> reader.read(path));
    }

}