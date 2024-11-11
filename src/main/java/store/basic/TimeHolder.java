package store.basic;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;

public class TimeHolder {
    public LocalDate now() {
        return LocalDate.from(DateTimes.now());
    }
}
