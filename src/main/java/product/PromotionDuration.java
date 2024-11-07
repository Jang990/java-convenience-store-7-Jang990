package product;

import java.time.LocalDate;
import java.util.Objects;

public class PromotionDuration {
    private final LocalDate startDate;
    private final LocalDate endDate;


    public PromotionDuration(LocalDate startInclusive, LocalDate endInclusive) {
        this.startDate = startInclusive;
        this.endDate = endInclusive;
    }

    public boolean isWithIn(LocalDate now) {
        return startDate.equals(now)
                || endDate.equals(now)
                || (startDate.isBefore(now) && endDate.isAfter(now));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionDuration that = (PromotionDuration) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
