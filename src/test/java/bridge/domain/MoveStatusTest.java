package bridge.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveStatusTest {
    @DisplayName("이동 가능하다면 O, 이동 불가능하면 X로 나타낸다.")
    @Test
    void findMoveStatusTest() {
        // when & then
        assertAll(
                () -> assertThat(MoveStatus.findMoveStatus(true)).isEqualTo(MoveStatus.O),
                () -> assertThat(MoveStatus.findMoveStatus(false)).isEqualTo(MoveStatus.X)
        );
    }
}
