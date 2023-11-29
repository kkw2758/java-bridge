package bridge.domain;

import bridge.exception.ErrorMessage;
import java.util.Arrays;

public enum GameStatus {
    EXIT("Q", false),
    RETRY("R", true);

    private final String flag;
    private final boolean status;

    GameStatus(String flag, boolean status) {
        this.flag = flag;
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public boolean getStatus() {
        return status;
    }

    public static GameStatus findGameStatus(String flag) {
        return Arrays.stream(GameStatus.values())
                .filter(gameStatus -> gameStatus.getFlag().equals(flag))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_GAME_STATUS.getMessage()));
    }
}
