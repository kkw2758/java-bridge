package bridge.domain;

import bridge.dto.MapResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BridgeMap {

    private final HashMap<BridgeStatus, List<MoveStatus>> bridgeMap = new HashMap<>();

    public BridgeMap() {
        initBridgeMap();
    }

    public void addMoveStatus(BridgeStatus bridgeStatus, MoveStatus moveStatus) {
        for (BridgeStatus status : BridgeStatus.values()) {
            if (status.equals(bridgeStatus)) {
                bridgeMap.get(status).add(moveStatus);
                continue;
            }
            bridgeMap.get(status).add(MoveStatus.NONE);
        }
    }

    public void initBridgeMap() {
        bridgeMap.clear();
        bridgeMap.put(BridgeStatus.UP, new ArrayList<MoveStatus>());
        bridgeMap.put(BridgeStatus.DOWN, new ArrayList<MoveStatus>());
    }

    public boolean hasCanNotMoveStatus() {
        return bridgeMap.values().stream()
                .flatMap(Collection::stream)
                .anyMatch(moveStatus -> moveStatus.equals(MoveStatus.CAN_NOT_MOVE));
    }

    public int getSize() {
        return bridgeMap.get(BridgeStatus.UP).size();
    }

    public MapResponse toResponse() {
        Map<String, List<String>> collect = bridgeMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getStatus(),
                        entry -> entry.getValue().stream()
                                .map(MoveStatus::getStatus)
                                .collect(Collectors.toList())
                ));
        return new MapResponse(collect);
    }

    public int calculateCanMoveCount() {
        return (int) bridgeMap.values().stream()
                .flatMap(Collection::stream)
                .filter(moveStatus -> moveStatus.equals(MoveStatus.CAN_MOVE))
                .count();
    }
}
