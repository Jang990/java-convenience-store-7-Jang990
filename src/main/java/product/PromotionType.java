package product;

import java.util.Arrays;
import java.util.Optional;

public enum PromotionType {
    ONE_PLUS_ONE(1,1),
    TWO_PLUS_ONE(2,1);

    private static final String UNKNOWN_PROMOTION_TYPE_ERROR_MESSAGE = "존재하지 않은 프로모션 타입입니다.";
    private final Quantity required;
    private final Quantity free;

    PromotionType(int required, int free) {
        this.required = new Quantity(required);
        this.free = new Quantity(free);
    }

    public static PromotionType find(int required, int free) {
        return Arrays.stream(values())
                .filter(type ->
                        type.required.equals(new Quantity(required))
                                && type.free.equals(new Quantity(free))
                )
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_PROMOTION_TYPE_ERROR_MESSAGE));
    }

    public Quantity getAppliedUnit() {
        return required.plus(free);
    }

    public Quantity getFree() {
        return free;
    }
}
