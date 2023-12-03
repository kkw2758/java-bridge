package bridge;

import bridge.controller.BridgeController;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new BridgeController(new InputView(), new OutputView(), new BridgeGame()).run();
    }
}
