package product;

public class PromotionTestBuilder {
    public static final PromotionType DEFAULT_TYPE = PromotionType.ONE_PLUS_ONE;
    public static final PromotionDuration DEFAULT_DURATION = PromotionDurationStub.withInPeriod;
    public static final String DEFAULT_NAME = "이벤트";

    private PromotionType type;
    private PromotionDuration duration;
    private String name;

    protected PromotionTestBuilder(PromotionType type, PromotionDuration duration, String name) {
        this.type = type;
        this.duration = duration;
        this.name = name;
    }

    public static PromotionTestBuilder builder() {
        return new PromotionTestBuilder(
                DEFAULT_TYPE,
                DEFAULT_DURATION,
                DEFAULT_NAME
        );
    }

    public PromotionTestBuilder type(PromotionType type) {
        return new PromotionTestBuilder(
                type, duration, name
        );
    }

    public PromotionTestBuilder name(String name) {
        return new PromotionTestBuilder(
                type, duration, name
        );
    }

    public PromotionTestBuilder duration(PromotionDuration duration) {
        return new PromotionTestBuilder(
                type, duration, name
        );
    }

    public Promotion build() {
        return new Promotion(type, duration, name);
    }

}
