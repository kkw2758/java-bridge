package bridge.domain;

public class TryCount {
    private static final int INITIAL_TRY_COUNT = 0;
    private static final int STEP_SIZE = 1;
    private int value = INITIAL_TRY_COUNT;

    public TryCount() {
    }

    public void add() {
        value += STEP_SIZE;
    }

    public int getValue() {
        return value;
    }
}
