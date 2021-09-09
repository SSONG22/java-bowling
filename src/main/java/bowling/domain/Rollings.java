package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.exception.InputException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Rollings {
    private static final int FIRST_INDEX = 0;
    private static final int FORMAL_SIZE = 2;
    private static final int MAX_SIZE = 3;
    private static final int OPERATION = 1;
    private static final String EXCESS_PITCH_COUNT_ERROR = "더이상 투구할 수 없습니다.";
    private static final String EXCESS_PIN_COUNTS_ERROR = "한 노멀 프레임에서 쓰러트릴 수 있는 핀의 갯수를 초과했습니다.";

    private LinkedList<Rolling> rollings;

    public Rollings(final List<Integer> rollings) {
        if (rollings.size() > MAX_SIZE) {
            throw new InputException(EXCESS_PITCH_COUNT_ERROR);
        }
        this.rollings = new LinkedList<>(rollings.stream()
                .map(Rolling::new)
                .collect(Collectors.toList()));
    }

    public boolean isEnd(final FrameNumber frameNumber) {
        if (rollings.isEmpty()) {
            return false;
        }
        if (!frameNumber.isLastNumber()) {
            return isStrike() || isSameSize(FORMAL_SIZE);
        }
        return isSameSize(MAX_SIZE) || (!isStrike() && !isSpare() && isSameSize(FORMAL_SIZE));
    }

    private boolean isSameSize(int size) {
        return rollings.size() == size;
    }

    private boolean isStrike() {
        return rollings.size() < MAX_SIZE
                && rollings.get(FIRST_INDEX).equals(new Rolling(Rolling.MAX_PINS));
    }

    public boolean isSpare() {
        return isSameSize(FORMAL_SIZE) && sum() == Rolling.MAX_PINS;
    }

    public Rollings pitch(final int countOfPins, final FrameNumber frameNumber) {
        if (isEnd(frameNumber)) {
            throw new InputException(EXCESS_PITCH_COUNT_ERROR);
        }

        if (!frameNumber.isLastNumber() && isOverPins(countOfPins)) {
            throw new InputException(EXCESS_PIN_COUNTS_ERROR);
        }
        rollings.add(new Rolling(countOfPins));
        return this;
    }

    private boolean isOverPins(final int countOfPins) {
        return sum() + countOfPins > Rolling.MAX_PINS;
    }

    public String result() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = FIRST_INDEX; index < rollings.size(); index++) {
            int previous = index - OPERATION;
            if (index > FIRST_INDEX) previous = rollings.get(index - OPERATION).value();

            stringBuilder.append(ScoreType.findType(previous, rollings.get(index).value()));
        }
        return stringBuilder.toString();
    }

    public Rolling currentRolling() {
        return rollings.getLast();
    }

    public Rolling next(Rolling rolling) {
        int index = rollings.indexOf(rolling);
        return rollings.get(index + OPERATION);
    }

    public int sum() {
        return rollings.stream().map(Rolling::value).reduce(0, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rollings rollings1 = (Rollings) o;
        return Objects.equals(rollings, rollings1.rollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollings);
    }

}
