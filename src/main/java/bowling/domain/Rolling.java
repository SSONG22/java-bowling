package bowling.domain;

import java.util.Objects;

public class Rolling {

    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;

    private final int countOfPins;

    public Rolling(final int countOfPins) {
        validate(countOfPins);
        this.countOfPins = countOfPins;
    }

    private void validate(final int countOfPins) {
        if (countOfPins < MIN_PINS || countOfPins > MAX_PINS) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 볼링 핀의 갯수는 0에서 10개 입니다.");
        }
    }

    public int value() {
        return countOfPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rolling rolling = (Rolling) o;
        return countOfPins == rolling.countOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins);
    }

}
