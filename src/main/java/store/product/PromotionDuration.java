package store.product;

import java.time.LocalDate;
import java.util.Objects;

public class PromotionDuration {
    private static final String INVALID_RANGE_ERROR_MESSAGE = "시작 시간은 종료 시간보다 빨라야 합니다.";

    private final LocalDate startDate;
    private final LocalDate endDate;
    
    public PromotionDuration(LocalDate startInclusive, LocalDate endInclusive) {
        if(endInclusive.isBefore(startInclusive)) {
            throw new IllegalArgumentException(INVALID_RANGE_ERROR_MESSAGE);
        }
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
