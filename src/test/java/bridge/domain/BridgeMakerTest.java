package bridge.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import bridge.BridgeRandomNumberGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeMakerTest {
    @DisplayName("다리 생성 테스트")
    @Test
    void makeBridgeTest() {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> bridge = bridgeMaker.makeBridge(3);

        // when
        int actualSize = bridge.size();
        int expectSize = 3;

        // then
        assertThat(actualSize).isEqualTo(expectSize);
    }
}
