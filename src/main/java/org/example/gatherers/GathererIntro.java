package org.example.gatherers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GathererIntro {

    static Stream<Byte> randomByteStream(){
        Random random = new Random();
        return Stream.generate(() -> (byte) random.nextInt(256));
    }

    public record RGB(byte red, byte green, byte blue){
        public RGB inverted() {
            return new RGB((byte) (Byte.MAX_VALUE - red()), (byte) (Byte.MAX_VALUE - green()), (byte) (Byte.MAX_VALUE - blue()));
        }
        public String toString(){
            return "RGB(" + Byte.toUnsignedInt(red()) + ", " + Byte.toUnsignedInt(green()) + ", " + Byte.toUnsignedInt(blue()) + ")";
        }
    };


    public static void main(String[] args) {

         randomByteStream()
                 .limit(7)
                 .gather(rgbGatherer())
                 .forEach(System.out::println);
        // Hvordan ville du gjort om denne strømmen av bytes, om til en strøm/liste/array av RGB'er?
        // { 128, 54, 23, 16, 234, 213, 134, 243, 43, 76, 34, 180 } -> RGB(128, 54, 23), RGB(16, 234, 213), RGB(134, 243, 43), RGB(76, 34, 180)

    }

    public static Gatherer<Byte, ?, RGB> rgbGatherer() {
        class ColorWindow {
            Byte[] window;
            int at;

            ColorWindow() {
                at = 0;
                window = new Byte[3];
            }

            boolean integrate(Byte element, Gatherer.Downstream<? super RGB> downstream) {
                window[at++] = element;
                if (at < 3) {
                    return true;
                } else {
                    final var oldWindow = window;
                    window = new Byte[3];
                    at = 0;
                    return downstream.push(new RGB(oldWindow[0], oldWindow[1], oldWindow[2]));
                }
            }

            void finish(Gatherer.Downstream<? super RGB> downstream) {
                // Fullfør og push siste RGB-objekt, men kun dersom vi har fått hentet alle 3 farge-bytes
                if (window[2] != null && !downstream.isRejecting()) {
                    downstream.push(new RGB(window[0], window[1], window[2]));
                }
            }
        }

        return Gatherer.<Byte, ColorWindow, RGB>ofSequential(
                ColorWindow::new,
                Gatherer.Integrator.<ColorWindow, Byte, RGB>ofGreedy(ColorWindow::integrate),
                ColorWindow::finish
        );
    }

}
