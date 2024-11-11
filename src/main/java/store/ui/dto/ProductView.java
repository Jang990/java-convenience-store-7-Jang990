package store.ui.dto;

import store.money.Money;
import store.product.Product;
import store.product.Quantity;

public class ProductView {
    private static final String EMPTY = "";
    private static final String QUANTITY_UNIT = "개";

    private final String name;
    private final Money price;
    private final Quantity promotionStock;
    private final Quantity normalStock;
    private final String promotionName;

    public ProductView(
            String name, Money price,
            Quantity promotionStock, Quantity normalStock,
            String promotionName) {
        this.name = name;
        this.price = price;
        this.promotionStock = promotionStock;
        this.normalStock = normalStock;
        this.promotionName = promotionName;
    }

    public ProductView(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.promotionStock = product.getPromotionStock();
        this.normalStock = product.getNormalStock();
        this.promotionName = product.getPromotionName();
    }

    @Override
    public String toString() {
        String format = "- %s %s원 %s %s";
        String normalLine = format.formatted(name, price, toStockString(normalStock), EMPTY);
        String promotionLine = format.formatted(name, price, toStockString(promotionStock), toPromotionNameString());
        return normalLine.concat("\n").concat(promotionLine).concat("\n");
    }

    private String toPromotionNameString() {
        if(promotionName == null)
            return EMPTY;
        return promotionName;
    }

    private String toStockString(Quantity quantity) {
        if(quantity.equals(Quantity.EMPTY))
            return "재고 없음";
        return quantity.toString().concat(QUANTITY_UNIT);
    }
}
