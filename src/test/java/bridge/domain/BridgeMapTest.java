package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeMapTest {
    private BridgeMap bridgeMap;

    @BeforeEach
    void beforeEach(){
        bridgeMap = new BridgeMap();
    }

    @DisplayName("다리의 위치와 이동 가능 여부를 입력받아 기록한다.")
    @Test
    void addMoveStatusTest() {
        // given
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);

        // when & then
        assertAll(
                () -> assertThat(bridgeMap.getSize()).isEqualTo(1),
                () -> assertThat(bridgeMap.calculateCanMoveCount()).isEqualTo(1)
        );
    }

    @DisplayName("갈 수 없는 위치로 이동을 시도 했는지 확인한다.")
    @Test
    void hasCanNotMoveStatusTest() {
        // given
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_NOT_MOVE);

        // when & then
        assertThat(bridgeMap.hasCanNotMoveStatus()).isEqualTo(true);
    }

    @DisplayName("갈 수 있는 위치로 이동한 횟수를 계산한다.")
    @Test
    void calculateCanMoveCount() {
        // given
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_NOT_MOVE);

        // when & then
        assertThat(bridgeMap.calculateCanMoveCount()).isEqualTo(2);
    }

    @DisplayName("기록한 이동 기록의 횟수를 구한다.")
    @Test
    void bridgeMapSizeTest(){
        // given
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_NOT_MOVE);

        // when & then
        assertThat(bridgeMap.getSize()).isEqualTo(3);
    }

    @DisplayName("기록한 이동 기록을 초기화 한다.")
    @Test
    void initBridgeMapTest(){
        // given
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);
        bridgeMap.addMoveStatus(BridgeStatus.UP, MoveStatus.CAN_MOVE);

        // when
        bridgeMap.initBridgeMap();

        // then
        assertThat(bridgeMap.getSize()).isEqualTo(0);
    }
}
