package bridge.domain;


public enum MoveStatus {
    CAN_MOVE("O"),
    CAN_NOT_MOVE("X"),
    NONE(" ");
    private final String status;

    MoveStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static MoveStatus findMoveStatus(boolean flag) {
        if (flag) {
            return CAN_MOVE;
        }
        return CAN_NOT_MOVE;
    }
}
