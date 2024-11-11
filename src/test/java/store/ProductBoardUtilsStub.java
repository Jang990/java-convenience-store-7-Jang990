package store;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBoardUtilsStub extends ProductBoardUtils {

    public ProductBoardUtilsStub() {
        super(null);
    }

    private boolean testContainsValue;
    private boolean testHasEnoughStockValue;

    public void setTestContainsValue(boolean testContainsValue) {
        this.testContainsValue = testContainsValue;
    }

    public void setTestHasEnoughStockValue(boolean testHasEnoughStockValue) {
        this.testHasEnoughStockValue = testHasEnoughStockValue;
    }

    @Override
    public boolean contains(String productName) {
        return testContainsValue;
    }

    @Override
    public boolean hasEnoughStock(String productName, int quantityToPurchase) {
        return testHasEnoughStockValue;
    }
}