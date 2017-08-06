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
public class Node implements Comparable<Node> {
    private final String BLANK = "0";
    private static boolean useHamming;
    private int hamming;
    private int manhattan;
    private int depth;
    private int blankIndex;
    private String[] state;
    private Node parent;

    public Node(int hamming, int manhattan, int depth, String[] state, Node parent) {
        this.hamming = hamming;
        this.manhattan = manhattan;
        this.depth = depth;
        this.state = state;
        this.parent = parent;
        setBlankIndex();
    }
    
    private void setBlankIndex() {
        for (int i = 0; i < state.length; ++i) {
            if (state[i].equals(BLANK)) {
                blankIndex = i;
            }
        }
    }
    
    public static boolean isUseHamming() {
        return useHamming;
    }

    public static void setUseHamming(boolean useHamming) {
        Node.useHamming = useHamming;
    }
    
    public int getBlankIndex() {
        return blankIndex;
    }

    public void setBlankIndex(int blankIndex) {
        this.blankIndex = blankIndex;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public int getHamming() {
        return hamming;
    }

    public void setHamming(int hamming) {
        this.hamming = hamming;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public void printState() {
        for (int i = 0; i < state.length; ++i) {
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");    
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public int compareTo(Node o) {
        int total1 = depth + hamming;
        int total2 = o.getDepth() + o.getHamming();
        if (!useHamming) {
            total1 = depth + manhattan;
            total2 = o.getDepth() + o.getManhattan();
        }
        if (total1 > total2) {
            return 1;
        } else if(total1 < total2) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
