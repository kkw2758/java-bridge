package bridge.exception;

public enum ErrorMessage {
    INVALID_GAME_STATUS("Q 또는 R을 입력해주세요."),
    INVALID_BRIDGE_STATUS("U 또는 D를 입력해주세요.");

    private final String message;
    private final String PREFIX = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
