package bridge.controller;

import bridge.BridgeNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeSize;
import bridge.domain.BridgeStatus;
import bridge.domain.GameStatus;
import bridge.exception.ExceptionHandler;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeController(InputView inputView, OutputView outputView, BridgeNumberGenerator bridgeNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void run() {
        outputView.printBridgeGameStartMessage();
        BridgeSize bridgeSize = ExceptionHandler.handle(this::requestBridgeSize);
        BridgeGame bridgeGame = BridgeGame.of(new BridgeMaker(bridgeNumberGenerator).makeBridge(bridgeSize.getValue()));
        playBridgeGame(bridgeGame);
    }

    private void playBridgeGame(BridgeGame bridgeGame) {
        while (true) {
            playOneRound(bridgeGame);
            if (bridgeGame.isWin() || !ExceptionHandler.handle(this::requestGameStatus).getStatus()) {
                break;
            }
            bridgeGame.retry();
        }
        outputView.printResultTag();
        outputView.printMap(bridgeGame.getBridgeMap());
        outputView.printResult(bridgeGame.isWin(), BridgeGame.getTryCount());
    }

    private void playOneRound(BridgeGame bridgeGame) {
        while (!bridgeGame.isGameEnd()) {
            bridgeGame.move(ExceptionHandler.handle(this::requestBridgeStatus));
            outputView.printMap(bridgeGame.getBridgeMap());
        }
    }

    private BridgeSize requestBridgeSize() {
        return BridgeSize.from(inputView.readBridgeSize());
    }

    private BridgeStatus requestBridgeStatus() {
        return BridgeStatus.findBridgeStatusByStatus(inputView.readMoving());
    }

    private GameStatus requestGameStatus() {
        return GameStatus.findGameStatus(inputView.readGameCommand());
    }
}
