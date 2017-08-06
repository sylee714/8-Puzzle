/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

/**
 *
 * @author MingKie
 */
public class Checker {
    private final String BLANK = "0";
    private String[] state;
    private int inversion;
    private boolean solvable;

    public Checker(String[] state, int inversion, boolean solvable) {
        this.state = state;
        this.inversion = inversion;
        this.solvable = solvable;
    }
    /**
     * Check the number of inversion. If it's odd, the puzzle is not
     * solvable. If it's even, the puzzle is solvable.
     */
    public void check() {
        for (int i = 0; i < state.length; ++i) {
            for (int j = i; j < state.length; ++j) {
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
        if (inversion%2 == 0) {
            solvable = true;
        }
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public void setInversion(int inversion) {
        this.inversion = inversion;
    }

    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }

    public String[] getState() {
        return state;
    }

    public int getInversion() {
        return inversion;
    }

    public boolean isSolvable() {
        return solvable;
    }
    
    
}
