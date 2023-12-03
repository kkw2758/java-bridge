package bridge.service;

import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeMap;
import bridge.domain.BridgeSize;
import bridge.domain.BridgeStatus;
import bridge.domain.MoveStatus;
import bridge.domain.TryCount;
import bridge.dto.GameResultResponse;
import java.util.List;

public class BridgeGame {

    public void move(BridgeMap bridgeMap, BridgeStatus userInputbridgeStatus, BridgeStatus bridgeStatus) {
        bridgeMap.addMoveStatus(bridgeStatus, decideMoveStatus(bridgeStatus, userInputbridgeStatus));
    }

    private MoveStatus decideMoveStatus(BridgeStatus bridgeStatus, BridgeStatus userInputbridgeStatus) {
        return MoveStatus.findMoveStatus(
                bridgeStatus.equals(userInputbridgeStatus));
    }

    public boolean isGameEnd(BridgeMap bridgeMap, List<String> bridge) {
        return bridgeMap.hasCanNotMoveStatus() || bridge.size() == bridgeMap.getSize();
    }

    public boolean isWin(BridgeMap bridgeMap, List<String> bridge) {
        return bridgeMap.calculateCanMoveCount() == bridge.size();
    }

    public void retry(BridgeMap bridgeMap) {
        bridgeMap.initBridgeMap();
    }

    public GameResultResponse getResult(BridgeMap bridgeMap, List<String> bridge, TryCount tryCount) {
        return new GameResultResponse(isWin(bridgeMap, bridge), tryCount.getValue());

    }

    public List<String> createBridge(BridgeSize bridgeSize) {
        return generateBridgeMaker(generateBridgeRandomNumberGenerator()).makeBridge(bridgeSize.getValue());
    }

    private BridgeMaker generateBridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        return new BridgeMaker(bridgeNumberGenerator);
    }

    private BridgeRandomNumberGenerator generateBridgeRandomNumberGenerator() {
        return new BridgeRandomNumberGenerator();
    }

    public BridgeSize generateBridgeSize(int bridgeSize) {
        return BridgeSize.from(bridgeSize);
    }

    public BridgeMap generateBridgeMap() {
        return new BridgeMap();
    }

    public TryCount generateTryCount() {
        return new TryCount();
    }
}
