package bowling.domain;

import java.util.List;

public interface Frame {
    Frame initNextFrame();

    void setKnockDownPins(KnockDownPins knockDownPins);

    List<Pitching> getStatus();

    boolean isEnd();

    Frame getNextFrame();

    int getIndex();
}
