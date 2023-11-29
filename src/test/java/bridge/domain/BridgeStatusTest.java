package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import bridge.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeStatusTest {

    @DisplayName("0을 입력하면 DOWN, 1을 입력하면 UP 을 반환한다.")
    @Test
    void findBridgeStatusSuccessTest() {
        // when & then
        assertAll(
                () -> assertThat(BridgeStatus.findBridgeStatus(0)).isEqualTo(BridgeStatus.DOWN),
                () -> assertThat(BridgeStatus.findBridgeStatus(1)).isEqualTo(BridgeStatus.UP)
        );
    }

    @DisplayName("0과 1 이외의 값을 입력하면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 2})
    void findBridgeStatusExceptionTest(int flag) {
        // when & then
        assertThatThrownBy(() -> BridgeStatus.findBridgeStatus(flag))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_BRIDGE_STATUS.getMessage());
    }
}
