package bridge.domain;

import java.util.Arrays;

public enum BridgeStatus {

    UP(1,"U"),
    DOWN(0,"D"),
    None(-1, "NONE");

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

    public static String findBridgeStatus(int flag) {
        return Arrays.stream(BridgeStatus.values())
                .filter(bridgeStatus -> bridgeStatus.getFlag() == flag)
                .findAny()
                .orElse(BridgeStatus.None)
                .getStatus();
    }
}
