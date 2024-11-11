package store;

import store.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductBoard {
    private final Map<String, Product> values;

    public ProductBoard(List<Product> values) {
        this.values = values.stream()
                .collect(
                        Collectors.toMap(
                                Product::getName,
                                Function.identity()
                        )
                );
    }

    protected Optional<Product> find(String productName) {
        return Optional.ofNullable(values.get(productName));
    }

    protected List<Product> findAll() {
        return values.values().stream().toList();
    }
}
