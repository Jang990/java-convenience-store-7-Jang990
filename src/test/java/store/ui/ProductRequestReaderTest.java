package store.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.ProductBoardUtilsStub;
import store.ui.dto.ProductRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestReaderTest {

    public static final ProductRequest SAMPLE_REQUEST = new ProductRequest("AAA", 1);

    @DisplayName("알 수 없는 제품 이름이라면 예외가 발생합니다.")
    @Test
    void test1() {
        ProductBoardUtilsStub productUtilsStub = productUtilsStub();
        productUtilsStub.setTestContainsValue(false);
        productUtilsStub.setTestHasEnoughStockValue(true);
        ProductRequestReader reader = new ProductRequestReader(parserStub(List.of(SAMPLE_REQUEST)), productUtilsStub);

        assertThrows(IllegalArgumentException.class, () -> reader.read("AAA"));
    }

    @DisplayName("충분한 재고가 없다면 예외가 발생합니다.")
    @Test
    void test2() {
        ProductBoardUtilsStub productUtilsStub = productUtilsStub();
        productUtilsStub.setTestContainsValue(true);
        productUtilsStub.setTestHasEnoughStockValue(false);
        ProductRequestReader reader = new ProductRequestReader(parserStub(List.of(SAMPLE_REQUEST)), productUtilsStub);

        assertThrows(IllegalArgumentException.class, () -> reader.read("AAA"));
    }

    @DisplayName("예외가 없다면 Parser가 파싱한 결과를 반환합니다.")
    @Test
    void test3() {
        List<ProductRequest> parsingResult = List.of(SAMPLE_REQUEST);
        ProductBoardUtilsStub productUtilsStub = productUtilsStub();
        productUtilsStub.setTestContainsValue(true);
        productUtilsStub.setTestHasEnoughStockValue(true);

        ProductRequestReader reader = new ProductRequestReader(parserStub(parsingResult), productUtilsStub);
        assertEquals(reader.read("AAA"), parsingResult);
    }

    private static ProductBoardUtilsStub productUtilsStub() {
        return new ProductBoardUtilsStub();
    }

    private static ProductRequestParserStub parserStub() {
        return new ProductRequestParserStub();
    }

    private static ProductRequestParserStub parserStub(List<ProductRequest> productRequests) {
        ProductRequestParserStub result = parserStub();
        result.setTestReadValue(productRequests);
        return result;
    }


}