package store.ui;

import store.ui.dto.ProductRequest;

import java.util.List;

class ProductRequestParserStub extends ProductRequestParser {
    private List<ProductRequest> testReadValue;

    public void setTestReadValue(List<ProductRequest> testReadValue) {
        this.testReadValue = testReadValue;
    }

    @Override
    public List<ProductRequest> parse(String requestLine) {
        return testReadValue;
    }
}