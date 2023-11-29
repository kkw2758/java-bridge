package bridge.domain;

import java.util.Arrays;

public enum MoveStatus {
    O(true),
    X(false);
    private final boolean status;

    MoveStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public static MoveStatus findMoveStatus(boolean flag) {
        return Arrays.stream(MoveStatus.values())
                .filter(moveStatus -> moveStatus.getStatus() == flag)
                .findAny()
                .get();
    }
}
