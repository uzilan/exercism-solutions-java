import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    private static final int b10000 = 0b10000;
    private static final int b1000 = 0b1000;
    private static final int b100 = 0b100;
    private static final int b10 = 0b10;

    List<Signal> calculateHandshake(int number) {
        final List<Signal> list = new ArrayList<>();
        int current = number;
        boolean reverse = false;
        while (current > 0) {
            if (current >= b10000) {
                reverse = !reverse;
                current -= b10000;
            } else if (current >= b1000) {
                list.add(0, Signal.JUMP);
                current -= b1000;
            } else if (current >= b100) {
                list.add(0, Signal.CLOSE_YOUR_EYES);
                current -= b100;
            } else if (current >= b10) {
                list.add(0, Signal.DOUBLE_BLINK);
                current -= b10;
            } else {
                list.add(0, Signal.WINK);
                current--;
            }
        }
        if (reverse) {
            Collections.reverse(list);
        }
        return list;
    }

}
