package pkg8.puzzle;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class generate a puzzle and shuffles it.
 */
public class Generator {
    private String[] puzzle;
    
    public Generator() {
        puzzle = new String[9];
        initialize();
    }
    
    private void initialize() {
        for (int i = 0; i < puzzle.length; ++i) {
            puzzle[i] = i + "";
        }
    }
    
    public String[] shuffle() {
        Collections.shuffle(Arrays.asList(puzzle));
        return puzzle;
    }   
}
