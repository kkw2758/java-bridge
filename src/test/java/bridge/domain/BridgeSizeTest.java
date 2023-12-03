package bridge.domain;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import bridge.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeSizeTest {

    @DisplayName("다리의 길이가 3이상 20이하가 아니라면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1,2,21,40})
    void bridgeSizeExceptionTest(int number) {
        // when & then
        assertThatThrownBy(() -> BridgeSize.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_BRIDGE_SIZE.getMessage());
    }

    @DisplayName("다리의 길이가 3이상 20이하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3,4,19,20})
    void bridgeSizeSuccessTest(int number) {
        // when & then
        assertDoesNotThrow(() -> BridgeSize.from(number));
    }
}
