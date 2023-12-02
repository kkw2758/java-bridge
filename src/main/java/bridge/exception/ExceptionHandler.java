package bridge.exception;

import bridge.view.OutputView;
import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printlnMessage(e.getMessage());
            }
        }
    }
}
