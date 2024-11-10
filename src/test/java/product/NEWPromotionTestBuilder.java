package product;

public class NEWPromotionTestBuilder {
    public static final PromotionType DEFAULT_TYPE = PromotionType.ONE_PLUS_ONE;
    public static final PromotionDuration DEFAULT_DURATION = PromotionDurationStub.withInPeriod;
    public static final String DEFAULT_NAME = "이벤트";

    private PromotionType type;
    private PromotionDuration duration;
    private String name;

    protected NEWPromotionTestBuilder(PromotionType type, PromotionDuration duration, String name) {
        this.type = type;
        this.duration = duration;
        this.name = name;
    }

    public static NEWPromotionTestBuilder builder() {
        return new NEWPromotionTestBuilder(
                DEFAULT_TYPE,
                DEFAULT_DURATION,
                DEFAULT_NAME
        );
    }

    public NEWPromotionTestBuilder type(PromotionType type) {
        return new NEWPromotionTestBuilder(
                type, duration, name
        );
    }

    public NEWPromotionTestBuilder name(String name) {
        return new NEWPromotionTestBuilder(
                type, duration, name
        );
    }

    public NEWPromotionTestBuilder duration(PromotionDuration duration) {
        return new NEWPromotionTestBuilder(
                type, duration, name
        );
    }

    public NEWPromotion build() {
        return new NEWPromotion(type, duration, name);
    }

}
