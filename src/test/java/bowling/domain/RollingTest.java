package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RollingTest {

    @DisplayName("쓰러트린 공의 갯수는 0개에서 10개다.")
    @Test
    void validate() {
        assertThat(new Rolling(1)).isEqualTo(new Rolling(1));
        assertThatThrownBy(() -> new Rolling(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

}