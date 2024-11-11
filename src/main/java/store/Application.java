package store;

import store.basic.StringPrinter;
import store.basic.StringReader;
import store.basic.TimeHolder;
import store.file.HeaderFileReader;
import store.ui.*;

public class Application {
    public static void main(String[] args) {
        MyStore myStore = new MyStore(
                storeFileReader(),
                new RetryInputAspect(storeInputView(), stringPrinter()),
                storeOutputView(),
                timeholder()
        );
        myStore.start();
    }

    private static TimeHolder timeholder() {
        return new TimeHolder();
    }

    private static StoreOutputView storeOutputView() {
        return new StoreOutputView(stringPrinter());
    }

    private static StoreInputView storeInputView() {
        return new StoreInputView(stringReader(), stringPrinter(), productRequestReader());
    }

    private static ProductBoard productBord() {
        return new ProductBoard(storeFileReader().readProduct(storeFileReader().readPromotions()));
    }

    private static StringPrinter stringPrinter() {
        return new StringPrinter();
    }

    private static ProductRequestReader productRequestReader() {
        return new ProductRequestReader(productRequestParser(), productBoardUtils());
    }

    private static ProductRequestParser productRequestParser() {
        return new ProductRequestParser();
    }

    private static ProductBoardUtils productBoardUtils() {
        return new ProductBoardUtils(productBord());
    }

    private static StringReader stringReader() {
        return new StringReader();
    }

    private static StoreFileReader storeFileReader() {
        return new StoreFileReader(headerFileReader());
    }

    private static HeaderFileReader headerFileReader() {
        return new HeaderFileReader();
    }
}
