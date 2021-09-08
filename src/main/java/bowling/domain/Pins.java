package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.exception.InputException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pins {
    private static final int FIRST_INDEX = 0;
    private static final int FORMAL_SIZE = 2;
    private static final int MAX_SIZE = 3;
    private static final String EXCESS_PITCH_COUNT_ERROR = "더이상 투구할 수 없습니다.";

    private List<Pin> pins;

    public Pins(final List<Integer> pins) {
        if (pins.size() > MAX_SIZE) {
            throw new InputException(EXCESS_PITCH_COUNT_ERROR);
        }
        this.pins = pins.stream()
                .map(Pin::new)
                .collect(Collectors.toList());
    }

    public boolean isEnd(final FrameNumber frameNumber) {
        if (pins.isEmpty()) {
            return false;
        }
        if (!frameNumber.isLastNumber()) {
            return isStrike() || isSameSize(FORMAL_SIZE);
        }
        return isSameSize(MAX_SIZE) || (!isStrike() && !isSpare() && isSameSize(FORMAL_SIZE));
    }

    private boolean isSameSize(int size) {
        return pins.size() == size;
    }

    private boolean isStrike() {
        return pins.size() < MAX_SIZE
                && pins.get(FIRST_INDEX).equals(new Pin(Pin.MAX_PINS));
    }

    public boolean isSpare() {
        return isSameSize(FORMAL_SIZE)
                && pins.stream().map(Pin::value).reduce(0, Integer::sum) == Pin.MAX_PINS;
    }

    public Pins pitch(final int countOfPins, final FrameNumber frameNumber) {
        if (isEnd(frameNumber)) {
            throw new InputException(EXCESS_PITCH_COUNT_ERROR);
        }
        pins.add(new Pin(countOfPins));
        return this;
    }

    public String result() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < pins.size(); index++) {
            int previous = index - 1;
            if (index > 0) previous = pins.get(index - 1).value();

            stringBuilder.append(ScoreType.findType(previous, pins.get(index).value()));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
