package store.ui;

import store.ProductBoardUtils;
import store.ui.dto.ProductRequest;

import java.util.List;

public class ProductRequestReader {
    private static final String UNKNOWN_PRODUCT_ERROR_MESSAGE = "존재하지 않는 상품입니다. 다시 입력해 주세요.";
    private static final String NO_STOCK_PRODUCT_ERROR_MESSAGE = "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";

    private final ProductRequestParser productRequestParser;
    private final ProductBoardUtils productBoardUtils;

    public ProductRequestReader(ProductRequestParser productRequestParser, ProductBoardUtils productBoardUtils) {
        this.productRequestParser = productRequestParser;
        this.productBoardUtils = productBoardUtils;
    }

    public List<ProductRequest> read(String requestLine) {
        List<ProductRequest> result = productRequestParser.parse(requestLine);
        if(hasUnknownProduct(result))
            throw new IllegalArgumentException(UNKNOWN_PRODUCT_ERROR_MESSAGE);
        if(hasNoStockProduct(result))
            throw new IllegalArgumentException(NO_STOCK_PRODUCT_ERROR_MESSAGE);
        return result;
    }

    private boolean hasNoStockProduct(List<ProductRequest> result) {
        return result.stream()
                .anyMatch(
                        request -> !productBoardUtils
                                .hasEnoughStock(
                                        request.getProductName(),
                                        request.getQuantity()
                                )
                );
    }

    private boolean hasUnknownProduct(List<ProductRequest> result) {
        return result.stream()
                .map(ProductRequest::getProductName)
                .anyMatch(name -> !productBoardUtils.contains(name));
    }
}
