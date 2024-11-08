package product;

public class PromotionTestBuilder {
    public static final PromotionCondition DEFAULT_CONDITION = new PromotionCondition(new Quantity(1), new Quantity(1));
    public static final PromotionDuration DEFAULT_DURATION = PromotionDurationStub.withInPeriod;
    public static final String DEFAULT_NAME = "이벤트";

    private PromotionCondition condition;
    private PromotionDuration duration;
    private String name;

    protected PromotionTestBuilder(PromotionCondition condition, PromotionDuration duration, String name) {
        this.condition = condition;
        this.duration = duration;
        this.name = name;
    }

    public static PromotionTestBuilder builder() {
        return new PromotionTestBuilder(
                DEFAULT_CONDITION,
                DEFAULT_DURATION,
                DEFAULT_NAME
        );
    }

    public PromotionTestBuilder condition(PromotionCondition condition) {
        return new PromotionTestBuilder(
                condition, duration, name
        );
    }

    public PromotionTestBuilder name(String name) {
        return new PromotionTestBuilder(
                condition, duration, name
        );
    }

    public PromotionTestBuilder duration(PromotionDuration duration) {
        return new PromotionTestBuilder(
                condition, duration, name
        );
    }

    public Promotion build() {
        return new Promotion(condition, duration, name);
    }

}
