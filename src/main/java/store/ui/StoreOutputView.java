package store.ui;

import store.basic.StringPrinter;
import store.users.Receipt;
import store.ui.dto.ProductView;

import java.util.List;

public class StoreOutputView {
    private final StringPrinter printer;

    public StoreOutputView(StringPrinter printer) {
        this.printer = printer;
    }

    public void welcome(String storeId, List<ProductView> productView) {
        StringBuilder productInfo = new StringBuilder();
        productView.forEach(productInfo::append);

        printer.print("""
                안녕하세요. %s입니다.
                현재 보유하고 있는 상품입니다.
                
                %s
                """.formatted(
                storeId, productInfo.toString()));
    }

    public void print(Receipt receipt) {
        printer.print(receipt.toString());
    }
}
