package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FrameNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RollingsTest {

    private FrameNumber normalNumber;
    private FrameNumber finalNumber;

    @BeforeEach
    void setUp() {
        normalNumber = new FrameNumber(9);
        finalNumber = new FrameNumber(10);
    }

    @DisplayName("투구는 3번 이상 던질 수 없다.(파이널 - 3회, 노멀 - 2회)")
    @Test
    void validate() {
        assertThatThrownBy(() -> new Rollings(Arrays.asList(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다음 투구를 던질수 있을지 결정 판단한다. (던질수 없다: end = true)")
    @Test
    void end_normal() {
        assertThat(new Rollings(Arrays.asList(10)).isEnd(normalNumber)).isTrue();
        assertThat(new Rollings(Arrays.asList(2, 5)).isEnd(normalNumber)).isTrue();
        assertThat(new Rollings(Arrays.asList(4)).isEnd(normalNumber)).isFalse();
        assertThat(new Rollings(Arrays.asList(4)).isEnd(normalNumber)).isFalse();
    }

    @DisplayName("다음 투구를 던질 수 있는 경우 (던질수 있다: end = false)")
    @Test
    void end_final() {
        assertThat(new Rollings(Arrays.asList(5, 5)).isEnd(finalNumber)).isFalse();
        assertThat(new Rollings(Arrays.asList(10, 2)).isEnd(finalNumber)).isFalse();
        assertThat(new Rollings(Arrays.asList(10, 10)).isEnd(finalNumber)).isFalse();
    }

    @DisplayName("다음 투구를 던질 수 없는 경우 (던질 수 없다: end = true)")
    @Test
    void end_final_true() {
        assertThat(new Rollings(Arrays.asList(10, 2, 3)).isEnd(finalNumber)).isTrue();
        assertThat(new Rollings(Arrays.asList(5, 5, 2)).isEnd(finalNumber)).isTrue();
        assertThat(new Rollings(Arrays.asList(3, 4)).isEnd(finalNumber)).isTrue();
    }

    @DisplayName("Pins 상태에 따른 결과값")
    @Test
    void result() {
        assertThat(new Rollings(Arrays.asList(10)).result()).isEqualTo("X");
        assertThat(new Rollings(Arrays.asList(10, 10)).result()).isEqualTo("XX");
        assertThat(new Rollings(Arrays.asList(10, 10, 10)).result()).isEqualTo("XXX");
        assertThat(new Rollings(Arrays.asList(0, 10)).result()).isEqualTo("0|/");
        assertThat(new Rollings(Arrays.asList(2, 5)).result()).isEqualTo("2|5");
        assertThat(new Rollings(Arrays.asList(5, 5)).result()).isEqualTo("5|/");
        assertThat(new Rollings(Arrays.asList(5, 5, 3)).result()).isEqualTo("5|/|3");
        assertThat(new Rollings(Arrays.asList(10, 2, 8)).result()).isEqualTo("X|2|/");
    }

    @DisplayName("투구를 던질때 Pins 의 상태")
    @Test
    void pitch_result() {
        assertThat(new Rollings(Arrays.asList(4)).pitch(6, normalNumber).result()).isEqualTo("4|/");
        assertThat(new Rollings(Arrays.asList(4)).pitch(6, finalNumber).result()).isEqualTo("4|/");

        assertThat(new Rollings(Arrays.asList(2)).pitch(5, normalNumber).result()).isEqualTo("2|5");
        assertThat(new Rollings(Arrays.asList(2)).pitch(5, finalNumber).result()).isEqualTo("2|5");

        assertThat(new Rollings(Arrays.asList(2)).pitch(0, normalNumber).result()).isEqualTo("2|-");
        assertThat(new Rollings(Arrays.asList(2)).pitch(0, finalNumber).result()).isEqualTo("2|-");

        assertThat(new Rollings(Arrays.asList(10)).pitch(2, finalNumber).result()).isEqualTo("X|2");
    }

    @DisplayName("투구를 던질때 Pins 의 상태")
    @Test
    void pitch_error() {
        assertThatThrownBy(() -> new Rollings(Arrays.asList(10)).pitch(1, normalNumber))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Rollings(Arrays.asList(2, 3)).pitch(1, finalNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 일때 점수 계산")
    @Test
    void strike_score() {
        Rollings rollings = new Rollings(Arrays.asList(10));
        Rolling current = rollings.currentRolling();
        int sum = rollings.sum();
        rollings.pitch(1, new FrameNumber(10));
        rollings.pitch(2, new FrameNumber(10));
        Rolling next = rollings.next(current);
        int score = sum + next.value() + rollings.next(next).value();

        assertThat(score).isEqualTo(13);
    }

    @DisplayName("스페어 일때 점수 계산")
    @Test
    void spare_score() {
        Rollings rollings = new Rollings(Arrays.asList(9, 1));
        Rolling current = rollings.currentRolling();
        int sum = rollings.sum();
        rollings.pitch(1, new FrameNumber(10));
        int score = sum + rollings.next(current).value();

        assertThat(score).isEqualTo(11);
    }

    @DisplayName("미스,거터 점수 계산")
    @Test
    void miss_gutter_score() {
        Rollings rollings = new Rollings(Arrays.asList(2));
        Rolling current = rollings.currentRolling();
        int sum = rollings.sum();
        rollings.pitch(3, new FrameNumber(1));
        int score = sum + rollings.next(current).value();

        assertThat(score).isEqualTo(5);
    }
}