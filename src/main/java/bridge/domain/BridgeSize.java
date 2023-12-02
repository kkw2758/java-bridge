package bridge.domain;

import bridge.exception.ErrorMessage;

public class BridgeSize {
    private static final int MIN_BRIDGE_SIZE = 3;
    private static final int MAX_BRIDGE_SIZE = 20;

    private final int value;

    private BridgeSize(int bridgeSize) {
        this.value = bridgeSize;
    }

    public static BridgeSize from(int bridgeSize) {
        validate(bridgeSize);
        return new BridgeSize(bridgeSize);
    }

    public int getValue() {
        return value;
    }

    private static void validate(int bridgeSize) {
        validateInRange(bridgeSize);
    }

    private static void validateInRange(int bridgeSize) {
        if (checkBridgeSizeNotInRange(bridgeSize)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BRIDGE_SIZE.getMessage());
        }
    }

    private static boolean checkBridgeSizeNotInRange(int bridgeSize) {
        return bridgeSize < MIN_BRIDGE_SIZE || bridgeSize > MAX_BRIDGE_SIZE;
    }
}
