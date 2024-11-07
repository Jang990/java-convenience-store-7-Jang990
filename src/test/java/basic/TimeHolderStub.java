package basic;

import java.time.LocalDate;

public class TimeHolderStub extends TimeHolder {
    private LocalDate testNow;

    public void setTestNow(LocalDate testNow) {
        this.testNow = testNow;
    }

    @Override
    public LocalDate now() {
        return testNow;
    }
}