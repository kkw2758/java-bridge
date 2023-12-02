package bridge.view;

import bridge.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";
    private static final int MIN_BRIDGE_SIZE = 3;
    private static final int MAX_BRIDGE_SIZE = 20;
    private static final String REQUEST_BRIDGE_SIZE_MESSAGE = "다리의 길이를 입력해주세요.";
    private static final String REQUEST_MOVING_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String REQUEST_GAME_COMMAND = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    public String enterMessage() {
        return validateBlankInput(Console.readLine());
    }

    public String validateBlankInput(String message) {
        checkBlankInput(message);
        return message;
    }

    private void checkBlankInput(String message) {
        if (message.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT.getMessage());
        }
    }

    private void validateNumber(String userInput) {
        if (isNotNumber(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    private boolean isNotNumber(String userInput) {
        return !userInput.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        OutputView.printlnMessage(REQUEST_BRIDGE_SIZE_MESSAGE);
        String userInput = enterMessage();
        validateNumber(userInput);
        validateBridgeSize(userInput);
        return Integer.parseInt(userInput);
    }

    private void validateBridgeSize(String userInput) {
        if (checkBridgeSizeNotInRange(Integer.parseInt(userInput))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BRIDGE_SIZE.getMessage());
        }
    }

    private boolean checkBridgeSizeNotInRange(int bridgeSize) {
        return bridgeSize < MIN_BRIDGE_SIZE || bridgeSize > MAX_BRIDGE_SIZE;
    }
    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        OutputView.printlnMessage(REQUEST_MOVING_MESSAGE);
        return enterMessage();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        OutputView.printlnMessage(REQUEST_GAME_COMMAND);
        return enterMessage();
    }
}
