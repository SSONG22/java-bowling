package bowling.domain.frame;

import bowling.domain.Rollings;

public interface Frame {
    boolean isEnd();
    Rollings pitch(int countOfPins);
    String valueOfFrame();
    Frame next();
    int getScore();
}
