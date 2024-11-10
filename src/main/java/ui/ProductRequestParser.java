package ui;

import ui.dto.ProductRequest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRequestParser {
    private static final int EMPTY_QUANTITY = 0;
    private static final int NAME_ORDER = 1;
    private static final int QUANTITY_ORDER = 2;

    private static final String PRODUCT_DELIMITER = ",";
    private static final String PRODUCT_DELIMITER_REGEX = Pattern.quote(PRODUCT_DELIMITER);
    private static final String NAME_QUANTITY_DELIMITER_REGEX = "\\[([^\\-]+)-(\\d+)\\]";

    private static final String INVALID_PRODUCT_INFO_ERROR_MESSAGE = "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";


    public List<ProductRequest> parse(String requestLine) {
        return Arrays.stream(requestLine.split(PRODUCT_DELIMITER_REGEX))
                .map(this::toProductRequest)
                .toList();
    }

    private ProductRequest toProductRequest(String productLine) {
        Matcher productMatcher = Pattern.compile(NAME_QUANTITY_DELIMITER_REGEX).matcher(productLine);
        if (!productMatcher.matches())
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_ERROR_MESSAGE);

        String name = productMatcher.group(NAME_ORDER);
        int quantity = toQuantity(productMatcher.group(QUANTITY_ORDER));
        if(name.isBlank() || quantity <= EMPTY_QUANTITY)
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_ERROR_MESSAGE);
        System.out.println(quantity);
        return new ProductRequest(name, quantity);
    }

    private static int toQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_ERROR_MESSAGE, e);
        }
    }
}
