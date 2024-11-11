package store;

import store.product.Product;

import java.util.List;
import java.util.Optional;

public class ProductBoardStub extends ProductBoard{
    private Optional<Product> testFindValue;
    private List<Product> testFindAllValue;

    public ProductBoardStub() {
        super(null);
    }

    public void setTestFindValue(Optional<Product> testFindValue) {
        this.testFindValue = testFindValue;
    }

    public void setTestFindAllValue(List<Product> testFindAllValue) {
        this.testFindAllValue = testFindAllValue;
    }

    @Override
    protected Optional<Product> find(String productName) {
        return testFindValue;
    }

    @Override
    protected List<Product> findAll() {
        return testFindAllValue;
    }
}
