package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import bridge.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {
    @DisplayName("Q를 입력하면 Exit, R을 입력하면 Retry 를 반환한다.")
    @Test
    void findGameStatusSuccessTest() {
        // when & then
        assertAll(
                () -> assertThat(GameStatus.findGameStatus("Q")).isEqualTo(GameStatus.EXIT),
                () -> assertThat(GameStatus.findGameStatus("R")).isEqualTo(GameStatus.RETRY)
        );
    }

    @DisplayName("Q, R 이외의 값을 입력하면 에러가 발생한다.")
    @Test
    void findGameStatusExceptionTest() {
        // when & then
        assertThatThrownBy(() -> GameStatus.findGameStatus("W"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_GAME_STATUS.getMessage());
    }
}
