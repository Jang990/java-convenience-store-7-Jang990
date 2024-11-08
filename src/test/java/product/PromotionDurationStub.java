package product;

import java.time.LocalDate;

public class PromotionDurationStub extends PromotionDuration {
    public static final PromotionDuration withInPeriod = new PromotionDurationStub(true);
    public static final PromotionDuration outsidePeriod = new PromotionDurationStub(false);

    private boolean isWithInReturn;

    public PromotionDurationStub(boolean isWithInReturn) {
        super(LocalDate.now(), LocalDate.now());
        this.isWithInReturn = isWithInReturn;
    }

    @Override
    public boolean isWithIn(LocalDate now) {
        return isWithInReturn;
    }
}
