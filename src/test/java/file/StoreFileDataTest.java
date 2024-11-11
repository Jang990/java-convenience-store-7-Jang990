package file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StoreFileDataTest {
    private final Map<String, String> product1 = Map.of(
            "name", "콜라",
            "price", "1000",
            "quantity", "10",
            "promotion", "탄산2+1"
    );
    private final Map<String, String> product2 = Map.of(
            "name", "콜라",
            "price", "1000",
            "quantity", "10",
            "promotion", "null"
    );
    private final Map<String, String> product3 = Map.of(
            "name", "사이다",
            "price", "1000",
            "quantity", "7",
            "promotion", "null"
    );
    private final List<Map<String, String>> productData = List.of(product1, product2, product3);
    private final StoreFileData storeFileData = new StoreFileData(productData);

    @DisplayName("특정 키에 해당하는 값을 가진 요소들을 확인할 수 있다.")
    @Test
    void test1() {
        List<Map<String, String>> result = storeFileData.findElementsContainingEntry("name", "콜라");
        assertEquals(result, List.of(product1,product2));
    }

    @DisplayName("특정 키의 값들을 확인할 수 있다.")
    @Test
    void test2() {
        assertEquals(storeFileData.findValues("name"), Set.of("콜라", "사이다"));
    }

    @DisplayName("전체 파일 데이터를 확인할 수 있다.")
    @Test
    void test3() {
        assertEquals(storeFileData.getElements(), productData);
    }

}