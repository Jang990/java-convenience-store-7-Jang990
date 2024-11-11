package store;

import store.product.Product;
import store.product.Quantity;

/**
 * 외부 패키지에서 ProductBoard를 사용할 수 없을 떄 Utils를 사용한다.
 */
public class ProductBoardUtils {
    private final ProductBoard productBoard;

    public ProductBoardUtils(ProductBoard productBoard) {
        this.productBoard = productBoard;
    }

    public boolean contains(String productName) {
        return productBoard.find(productName).isPresent();
    }

    public boolean hasEnoughStock(String productName, int quantityToPurchase) {
        Product product = productBoard.find(productName)
                .orElseThrow(IllegalArgumentException::new);

        Quantity toPurchase = new Quantity(quantityToPurchase);
        return toPurchase.isLessThan(product.getStock().stock());
    }
}
