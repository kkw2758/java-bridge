package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void beforeEach(){
        bridgeGame = BridgeGame.of(List.of("U", "D", "D"));
    }

    @DisplayName("다리를 건널 수 있으면 true 아니면 false 를 반환한다.")
    @Test
    void moveTest() {
        // when & then
        assertAll(
                () -> assertThat(bridgeGame.move(BridgeStatus.UP)).isEqualTo(true),
                () -> assertThat(bridgeGame.move(BridgeStatus.UP)).isEqualTo(false)
        );
    }

    @DisplayName("다리를 건널 수 있다면 다리를 건너고 위치를 증가시켜야한다.")
    @Test
    void afterMovePositionTest() {
        // when
        bridgeGame.move(BridgeStatus.UP);
        bridgeGame.move(BridgeStatus.DOWN);

        // then
        assertThat(bridgeGame.getPosition()).isEqualTo(2);
    }
    @DisplayName("다리를 건널 수 있다면 다리를 건너고 위치를 증가시켜야한다.")
    @Test
    void afterNotMovePositionTest() {
        // when
        bridgeGame.move(BridgeStatus.DOWN);

        // then
        assertThat(bridgeGame.getPosition()).isEqualTo(0);
    }

    @DisplayName("이동할 수 없는 경우 게임을 종료한다.")
    @Test
    void isGameEndAtCanNotMoveTest() {
        // given
        bridgeGame.move(BridgeStatus.DOWN);

        // when & then
        assertThat(bridgeGame.isGameEnd()).isEqualTo(true);
    }

    @DisplayName("다리의 길이만큼 이동한 경우 게임을 종료한다.")
    @Test
    void isGameEndAtMaximumMoveTest() {
        // given
        bridgeGame.move(BridgeStatus.UP);
        bridgeGame.move(BridgeStatus.DOWN);
        bridgeGame.move(BridgeStatus.DOWN);

        // when & then
        assertThat(bridgeGame.isGameEnd()).isEqualTo(true);
    }

    @DisplayName("다리의 길이 만큼 이동에 성공하면 게임에서 승리한다.")
    @Test
    void isWinTest() {
        // given
        bridgeGame.move(BridgeStatus.UP);
        bridgeGame.move(BridgeStatus.DOWN);
        bridgeGame.move(BridgeStatus.DOWN);

        // when & then
        assertThat(bridgeGame.isWin()).isEqualTo(true);
    }
}
