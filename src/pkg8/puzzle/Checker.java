package pkg8.puzzle;

/**
 * This class is responsible to check if a puzzle is solvable or not.
 * It checks the number of inversion.
 */
public class Checker {
    private final String BLANK = "0";
    private String[] state;
    private int inversion;
    private boolean solvable;

    public Checker(String[] state) {
        this.state = state;
        inversion = 0;
    }
    
    /**
     * Check the number of inversion. If it's odd, the puzzle is not
     * solvable. If it's even, the puzzle is solvable.
     */
    public void check() {
        for (int i = 0; i < state.length; ++i) {
            for (int j = i; j < state.length; ++j) {
                // Do not count 0
                if (!state[i].equals(BLANK)) {
                    if (!state[j].equals(BLANK)) {
                        // If left one is greater than right one, it gives
                        // an integer that is greater than 0.
                        if (state[i].compareTo(state[j]) > 0) {
                            inversion++;
                        }
                    }
                }
            }
        }
        // If it's even, it's solvable
        if ((inversion % 2) == 0) {
            solvable = true;
        // If it's odd, it's not solvable
        } else {
            solvable = false;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }
}
