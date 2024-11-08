package product;

public class PromotionTestBuilder {
    public static final int DEFAULT_REQUIRED_QUANTITY = 2;
    public static final int DEFAULT_FREE_QUANTITY = 1;
    public static final PromotionDuration DEFAULT_DURATION = PromotionDurationStub.withInPeriod;
    public static final String DEFAULT_NAME = "이벤트";

    private int requiredQuantity;
    private int freeQuantity;
    private PromotionDuration duration;
    private String name;

    protected PromotionTestBuilder(int requiredQuantity, int freeQuantity, PromotionDuration duration, String name) {
        this.requiredQuantity = requiredQuantity;
        this.freeQuantity = freeQuantity;
        this.duration = duration;
        this.name = name;
    }

    public static PromotionTestBuilder builder() {
        return new PromotionTestBuilder(
                DEFAULT_REQUIRED_QUANTITY,
                DEFAULT_FREE_QUANTITY,
                DEFAULT_DURATION,
                DEFAULT_NAME
        );
    }

    public PromotionTestBuilder condition(int requiredQuantity, int freeQuantity) {
        return new PromotionTestBuilder(
                requiredQuantity, freeQuantity, duration, name
        );
    }

    public PromotionTestBuilder name(String name) {
        return new PromotionTestBuilder(
                requiredQuantity, freeQuantity, duration, name
        );
    }

    public PromotionTestBuilder duration(PromotionDuration duration) {
        return new PromotionTestBuilder(
                requiredQuantity, freeQuantity, duration, name
        );
    }

    public Promotion build() {
        return new Promotion(
                requiredQuantity,
                freeQuantity,
                name,
                duration
        );
    }

}
