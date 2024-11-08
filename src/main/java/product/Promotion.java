package product;

import java.time.LocalDate;

public class Promotion {
    private final PromotionDuration duration;
    private final String name;

    public Promotion(LocalDate startDate, LocalDate endDate, String name) {
        duration = new PromotionDuration(startDate, endDate);
        this.name = name;
    }

    public boolean isAvailable(LocalDate now) {
        return duration.isWithIn(now);
    }

    @Override
    public String toString() {
        return name;
    }
}
