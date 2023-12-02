package bridge.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static int TRY_COUNT = 1;
    private static final int INITIAL_POSITION = 0;
    private int position = INITIAL_POSITION;

    private final List<String> bridge;
    private List<List<String>> bridgeMap = new ArrayList<>();


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    private BridgeGame(List<String> bridge) {
        initBridgeMap();
        this.bridge = bridge;
    }

    public static int getTryCount() {
        return TRY_COUNT;
    }

    public static BridgeGame of(List<String> bridge) {
        return new BridgeGame(bridge);
    }

    public boolean isWin() {
        return bridgeMap.stream()
                .map(list -> list.stream().filter(str -> str.equals(MoveStatus.CAN_MOVE.getStatus())).count())
                .mapToInt(Long::intValue)
                .sum() == bridge.size();
    }

    public List<List<String>> getBridgeMap() {
        return bridgeMap;
    }

    public boolean isGameEnd() {
        return hasCanNotMoveStatus() || bridgeMap.get(0).size() == bridge.size();
    }

    private boolean hasCanNotMoveStatus() {
        return bridgeMap.stream()
                .flatMap(List::stream)
                .anyMatch(value -> value.equals(MoveStatus.CAN_NOT_MOVE.getStatus()));
    }

    private void initBridgeMap(){
        List<List<String>> bridgeMap = new ArrayList<>();
        bridgeMap.add(new ArrayList<String>());
        bridgeMap.add(new ArrayList<String>());
        this.bridgeMap = bridgeMap;
    }

    public int getPosition() {
        return position;
    }

    public void move(BridgeStatus bridgeStatus) {
        MoveStatus moveStatus = MoveStatus.findMoveStatus(bridge.get(position).equals(bridgeStatus.getStatus()));
        bridgeMap.get(0).add(MoveStatus.NONE.getStatus());
        bridgeMap.get(1).add(MoveStatus.NONE.getStatus());
        bridgeMap.get(bridgeStatus.getFlag()).set(position, moveStatus.getStatus());
        if (moveStatus.equals(MoveStatus.CAN_MOVE)) {
            position += 1;
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        TRY_COUNT += 1;
        initBridgeMap();
        position = INITIAL_POSITION;
    }
}
