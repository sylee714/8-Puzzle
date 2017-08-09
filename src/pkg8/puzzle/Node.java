package pkg8.puzzle;

/**
 * This class represents a node. Nodes stores the values of hamming, manhattan,
 * depth, state, and parent. It implements Comparable to use priority queues.
 */
public class Node implements Comparable<Node> {
    private final String BLANK = "0";
    // To indicate what h(n) to use
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

    public String[] getState() {
        return state;
    }

    public int getHamming() {
        return hamming;
    }

    public int getManhattan() {
        return manhattan;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public Node getParent() {
        return parent;
    }
    
    /**
     * To print out the state of the node in 2-D array form.
     */
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

    /**
     * Compares this object with the specified object for order.
     * @param o - the object to be compared
     * @return a negative integer, zero, or a positive integer 
     *         as this object is less than, equal to, or greater than 
     *         the specified object.
     */
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
