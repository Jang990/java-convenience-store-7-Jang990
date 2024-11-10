package ui;

import ui.dto.ProductRequest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRequestReader {

    private static final int NAME_ORDER = 1;
    private static final int QUANTITY_ORDER = 2;
    private static final String PRODUCT_DELIMITER = ",";
    private static final String PRODUCT_DELIMITER_REGEX = Pattern.quote(PRODUCT_DELIMITER);
    private static final String NAME_QUANTITY_DELIMITER_REGEX = "\\[(.*)-(\\d+)\\]";
    private static final String INVALID_PRODUCT_INFO_ERROR_MESSAGE = "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";

    public List<ProductRequest> read(String requestLine) {
        return Arrays.stream(requestLine.split(PRODUCT_DELIMITER_REGEX))
                .map(this::toProductRequest)
                .toList();
    }

    private ProductRequest toProductRequest(String productLine) {
        try {
            Matcher productInfoMatcher = Pattern.compile(NAME_QUANTITY_DELIMITER_REGEX)
                    .matcher(productLine);
            if (!productInfoMatcher.matches())
                throw new IllegalArgumentException(INVALID_PRODUCT_INFO_ERROR_MESSAGE);

            return toProductRequest(productInfoMatcher);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_ERROR_MESSAGE, e);
        }
    }

    private static ProductRequest toProductRequest(Matcher matcher) {
        return new ProductRequest(
                matcher.group(NAME_ORDER),
                Integer.parseInt(matcher.group(QUANTITY_ORDER))
        );
    }
}
