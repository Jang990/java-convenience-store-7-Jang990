package store.ui;

import org.junit.jupiter.api.Test;
import store.basic.StringPrinter;
import store.basic.StringPrinterDummy;
import store.money.Money;
import store.product.Quantity;
import store.ui.dto.ProductView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreOutputViewTest {
    StoreOutputView storeOutputView = new StoreOutputView(new StringPrinter());

    @Test
    void test1() {
        List<ProductView> list = List.of(
                new ProductView("콜라", new Money(1000), Quantity.of(10), Quantity.of(10), "탄산2+1"),
                new ProductView("사이다", new Money(1000), Quantity.of(8), Quantity.of(7), "탄산2+1")
        );
        storeOutputView.welcome("W편의점", list);
    }

}