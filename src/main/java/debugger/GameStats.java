package debugger;

/**
 * Parts 2 and 3. A receiver's yards in each game of the season.
 *
 * This file has 4 bugs: one in each method below main.
 * Find each one with the debugger. Do NOT add println lines.
 */
public class GameStats {

    public static void main(String[] args) {
        int[] yards = {12, 45, 8, 0, 67, 20};

        System.out.println("=== Season stats ===");
        System.out.println("Total yards:   " + totalYards(yards));
        System.out.println("Average yards: " + averageYards(yards));
        System.out.println("Best game:     game " + (bestGame(yards) + 1));
        System.out.println("Big games:     " + countBigGames(yards, 20));
    }

    /** Adds up the yards from every game. */
    public static int totalYards(int[] yards) {
        int total = 0;
        for (int i = 0; i < yards.length; i++) {
            total = total + yards[i];
        }
        return total;
    }

    /** The average yards per game, with decimals. */
    public static double averageYards(int[] yards) {
        int total = totalYards(yards);
        double average = (double) total / yards.length;
        return average;
    }

    /** The index of the game with the most yards. */
    public static int bestGame(int[] yards) {
        int best = 0;
        for (int i = 1; i < yards.length; i++) {
            if (yards[i] > yards[best]) {
                best = i;
            }
        }
        return best;
    }

    /** How many games had at least min yards. */
    public static int countBigGames(int[] yards, int min) {
        int count = 0;
        for (int i = 0; i < yards.length; i++) {
            if (yards[i] > min) {
                count++;
            }
        }
        return count;
    }
}
