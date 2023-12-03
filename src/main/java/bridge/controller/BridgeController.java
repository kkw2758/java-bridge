package bridge.controller;

import bridge.domain.BridgeMap;
import bridge.domain.BridgeSize;
import bridge.domain.BridgeStatus;
import bridge.domain.GameStatus;
import bridge.domain.TryCount;
import bridge.exception.ExceptionHandler;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;

    public BridgeController(InputView inputView, OutputView outputView, BridgeGame bridgeGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = bridgeGame;
    }

    public void run() {
        outputView.printBridgeGameStartMessage();
        BridgeSize bridgeSize = ExceptionHandler.handle(this::requestBridgeSize);
        List<String> bridge = bridgeGame.createBridge(bridgeSize);
        BridgeMap bridgeMap = bridgeGame.generateBridgeMap();
        TryCount tryCount = bridgeGame.generateTryCount();
        playBridgeGame(bridge, bridgeMap, tryCount);
    }


    private void playBridgeGame(List<String> bridge, BridgeMap bridgeMap, TryCount tryCount) {
        while (true) {
            playOneRound(bridge, bridgeMap);
            tryCount.add();
            if (bridgeGame.isWin(bridgeMap, bridge) || !ExceptionHandler.handle(this::requestGameStatus).getStatus()) {
                break;
            }
            bridgeGame.retry(bridgeMap);
        }
        outputView.printResultTag();
        outputView.printMap(bridgeMap.toResponse());
        outputView.printResult(bridgeGame.getResult(bridgeMap, bridge, tryCount));
    }

    private void playOneRound(List<String> bridge, BridgeMap bridgeMap) {
        for (String bridgeStatus : bridge) {
            bridgeGame.move(bridgeMap, BridgeStatus.findBridgeStatusByStatus(bridgeStatus),
                    ExceptionHandler.handle(this::requestBridgeStatus));
            outputView.printMap(bridgeMap.toResponse());
            if (bridgeGame.isGameEnd(bridgeMap, bridge)) {
                break;
            }
        }
    }

    private BridgeSize requestBridgeSize() {
        return bridgeGame.generateBridgeSize(inputView.readBridgeSize());
    }

    private BridgeStatus requestBridgeStatus() {
        return BridgeStatus.findBridgeStatusByStatus(inputView.readMoving());
    }

    private GameStatus requestGameStatus() {
        return GameStatus.findGameStatus(inputView.readGameCommand());
    }
}
