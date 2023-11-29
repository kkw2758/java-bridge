package bridge.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static final int INITIAL_POSITION = 0;
    private int position = INITIAL_POSITION;

    private final List<String> bridge;
    private List<MoveStatus> moveInfo = new ArrayList<>();

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    private BridgeGame(List<String> bridge) {
        this.bridge = bridge;
    }

    public static BridgeGame of(List<String> bridge) {
        return new BridgeGame(bridge);
    }

    public boolean isWin() {
        return moveInfo.stream()
                .filter(moveStatus -> moveStatus.equals(MoveStatus.O))
                .count() == bridge.size();
    }

    public boolean isGameEnd() {
        return hasNotMoveStatus() || moveInfo.size() == bridge.size();
    }

    private boolean hasNotMoveStatus() {
        return moveInfo.contains(MoveStatus.X);
    }

    public int getPosition() {
        return position;
    }

    public boolean move(BridgeStatus bridgeStatus) {
        MoveStatus moveStatus = MoveStatus.findMoveStatus(bridge.get(position).equals(bridgeStatus.getStatus()));
        moveInfo.add(moveStatus);
        if (moveStatus.getStatus()) {
            position += 1;
        }
        return moveStatus.getStatus();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        moveInfo = new ArrayList<>();
        position = INITIAL_POSITION;
    }
}
