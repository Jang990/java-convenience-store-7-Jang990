package product;

import java.time.LocalDate;

public class Promotion {
    private final PromotionDuration duration;
    private final String name;

    public Promotion(PromotionDuration duration, String name) {
        this.duration = duration;
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
