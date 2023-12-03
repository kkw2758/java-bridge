package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TryCountTest {
    @DisplayName("시도 횟수의 초기 값은 0이다.")
    @Test
    void generateTryCountTest() {
        // when & then
        TryCount tryCount = new TryCount();
        assertThat(tryCount.getValue()).isEqualTo(0);
    }

    @DisplayName("시도 횟수는 한번에 1씩 증가한다.")
    @Test
    void addTryCountTest() {
        // given
        TryCount tryCount = new TryCount();

        // when
        tryCount.add();
        int actual = tryCount.getValue();
        int expected = 1;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
