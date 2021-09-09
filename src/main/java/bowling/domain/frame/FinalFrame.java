package bowling.domain.frame;


import bowling.domain.Rollings;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    public static final int FINAL_FRAME_NUMBER = 10;

    private final FrameNumber frameNumber;
    private final Rollings pitchRollings;

    public FinalFrame() {
        this.frameNumber = new FrameNumber(FINAL_FRAME_NUMBER);
        this.pitchRollings = new Rollings(new LinkedList<>());
    }

    public FinalFrame(final List<Integer> pins) {
        this.frameNumber = new FrameNumber(FINAL_FRAME_NUMBER);
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
    public int getScore() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitchRollings, that.pitchRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitchRollings);
    }
}
