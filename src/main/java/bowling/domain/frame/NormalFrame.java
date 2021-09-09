package bowling.domain.frame;

import bowling.domain.Rollings;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final FrameNumber frameNumber;
    private final Rollings pitchRollings;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitchRollings = new Rollings(new LinkedList<>());
    }

    public NormalFrame(final int frameNumber, final List<Integer> pins) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitchRollings = new Rollings(pins);
    }

    @Override
    public boolean isEnd() {
        return pitchRollings.isEnd(frameNumber);
    }

    @Override
    public Rollings pitch(final int countOfPins) {
        return pitchRollings.pitch(countOfPins, frameNumber);
    }

    @Override
    public String valueOfFrame() {
        return pitchRollings.result();
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitchRollings, that.pitchRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitchRollings);
    }
}
