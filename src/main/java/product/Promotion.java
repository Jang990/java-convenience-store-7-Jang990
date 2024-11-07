package product;

import java.time.LocalDate;

public class Promotion {
    private final PromotionDuration duration;

    public Promotion(LocalDate startDate, LocalDate endDate) {
        duration = new PromotionDuration(startDate, endDate);
    }

    public boolean isAvailable(LocalDate now) {
        return duration.isWithIn(now);
    }
}
