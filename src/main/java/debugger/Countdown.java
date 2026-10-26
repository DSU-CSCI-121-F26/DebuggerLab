package debugger;

/**
 * Part 1 warm-up. There is no bug here.
 * Use the debugger to WATCH the loop run, one line at a time.
 */
public class Countdown {

    public static void main(String[] args) {
        int yardsToGo = 10;
        int play = 1;

        while (yardsToGo > 0) {
            int gain = play * 2;
            yardsToGo = yardsToGo - gain;
            play++;
        }

        System.out.println("First down after " + (play - 1) + " plays.");
        System.out.println("Yards to go at the end: " + yardsToGo);
    }
}
