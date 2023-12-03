package bridge.dto;


public class GameResultResponse {
    private final boolean result;
    private final int tryCount;

    public GameResultResponse(boolean result, int tryCount) {
        this.result = result;
        this.tryCount = tryCount;
    }

    public boolean getResult() {
        return result;
    }

    public int getTryCount() {
        return tryCount;
    }
}
