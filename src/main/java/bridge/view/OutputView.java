package bridge.view;


import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String BRIDGE_GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";
    private static final String RESULT_TAG = "최종 게임 결과";
    private static final String TOTAL_TRY_COUNT = "총 시도한 횟수: %d";

    public static void printlnMessage(String message) {
        System.out.println(message);
    }

    public static void printlnFormat(String message, Object... args) {
        printlnMessage(String.format(message, args));
    }

    public static void printFormat(String message, Object... args) {
        printMessage(String.format(message, args));
    }

    public static void printMessage(String message) {
        System.out.print(message);
    }


    public void printBridgeGameStartMessage() {
        printlnMessage(BRIDGE_GAME_START_MESSAGE);
    }
    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<List<String>> bridgeMap) {
        printlnMessage("[ " + String.join(" | ", bridgeMap.get(1)) + " ]");
        printlnMessage("[ " + String.join(" | ", bridgeMap.get(0)) + " ]");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean isWin, int tryCount) {
        printIsWin(isWin);
        printTotalTryCount(tryCount);
    }

    private void printIsWin(boolean isWin){
        if (isWin) {
            printlnMessage("게임 성공 여부: 성공");
            return;
        }
        printlnMessage("게임 성공 여부: 실패");
    }
    private void printTotalTryCount(int tryCount) {
        printlnFormat(TOTAL_TRY_COUNT, tryCount);
    }

    public void printResultTag() {
        printlnMessage(RESULT_TAG);
    }
}
