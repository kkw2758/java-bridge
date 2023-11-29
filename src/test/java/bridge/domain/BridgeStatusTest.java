package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BridgeStatusTest {

    @DisplayName("0을 입력하면 D, 1을 입력하면 U, 그 밖의 값을 입력하면 NONE 을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0, D", "1, U", "-1, NONE", "10, NONE"}, delimiter = ',')
    void findBridgeStatusTest(int flag, String expected) {
        // when
        String actual = BridgeStatus.findBridgeStatus(flag);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
