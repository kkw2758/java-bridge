package bridge.domain;

import bridge.exception.ErrorMessage;
import java.util.Arrays;

public enum BridgeStatus {

    UP(1, "U"),
    DOWN(0, "D");

    private final int flag;
    private final String status;

    BridgeStatus(int flag, String status) {
        this.flag = flag;
        this.status = status;
    }

    public int getFlag() {
        return flag;
    }

    public String getStatus() {
        return status;
    }

    public static BridgeStatus findBridgeStatus(int flag) {
        return Arrays.stream(BridgeStatus.values())
                .filter(bridgeStatus -> bridgeStatus.getFlag() == flag)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BRIDGE_STATUS.getMessage()));
    }

    public static BridgeStatus findBridgeStatusByStatus(String status) {
        return Arrays.stream(BridgeStatus.values())
                .filter(bridgeStatus -> bridgeStatus.getStatus().equals(status))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_BRIDGE_STATUS.getMessage()));
    }
}
