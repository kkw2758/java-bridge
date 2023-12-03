package bridge.dto;

import java.util.List;
import java.util.Map;

public class MapResponse {
    private final Map<String, List<String>> value;

    public MapResponse(Map<String, List<String>> value) {
        this.value = value;
    }

    public Map<String, List<String>> getValue() {
        return value;
    }
}
