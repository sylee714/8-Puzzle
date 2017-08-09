package pkg8.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class solves the given puzzle by using A* with graph search. There are 
 * two kinds of h(n), one is hamming and other one is manhattan. It uses both 
 * to solve the puzzle. It stores number of moves to reach the goal, the search 
 * cost, and run time to compare the performance of those two functions.
 */
public class Solver {
    private HashMap<Integer, String[]> exploredStates;
    private Queue<Node> frontier;
    private Node init;
    private Node goal;
    private int depth;
    private boolean useHamming;
    private long startTime;
    private long endTime;
    private long time;
    private int totalMove;
    private int totalCost;
    private final String[] GOAL_STATE = {"0", "1", "2", 
                                         "3", "4", "5", 
                                         "6", "7", "8"};
    private final String BLANK = "0";
    
    public Solver(String[] state, boolean useHamming) {
        exploredStates = new HashMap<Integer, String[]>();
        frontier = new PriorityQueue<Node>();
        totalCost = 0;
        depth = 0;
        this.useHamming = useHamming;
        init = new Node(getHamming(state), getManhattan(state), depth, state, null);
    }
    
    public void solve() {
        startTime = System.currentTimeMillis();
        frontier.add(init);
        if (!frontier.isEmpty()) {     
            boolean reachedGoal = checkGoal(init.getState());
            ArrayList<Node> neighbors;
            Node cur;
            if (useHamming) {
                Node.setUseHamming(true);
            } else {
                Node.setUseHamming(false);
            }
            while (!reachedGoal) {
                // Get the node with the smallest value
                cur = frontier.remove();
                // Check if that node has the goal state
                if (!checkGoal(cur.getState())) {
                    // Add its state to the explored states
                    addExploredStates(cur.getState());
                    // Expand it and do not add ones that are already explored
                    neighbors = (ArrayList<Node>) createNeighbors(
                            (ArrayList<String[]>) getNeighbors(cur), 
                            cur.getDepth() + 1, cur);
                    // Add its neighbors to the frontier
                    addFrontier(neighbors);
                } else {
                    endTime = System.currentTimeMillis();
                    time = endTime - startTime;
                    reachedGoal = true;
                    goal = cur;
                    totalMove = goal.getDepth();
                }
            }
        } else {
            System.out.println("Frontier is empty.");
        }
    }
    
    public void printNeighbors(List<Node> neighbors) {
        System.out.println("Neighbors: ");
        for (int i = 0; i < neighbors.size(); ++i) {
            System.out.println("Hamming: " + neighbors.get(i).getHamming());
            System.out.println("Manhattan: " + neighbors.get(i).getManhattan());
            System.out.println("Depth: " + neighbors.get(i).getDepth());
            neighbors.get(i).printState();
        }
        System.out.println("-----------------------");
    }
    
    public void addExploredStates(String[] state) {
        int key = Arrays.hashCode(state);
        if (!exploredStates.containsKey(key)) {
            exploredStates.put(key, state);
        }
    }
    
    public boolean checkExploredStates(String[] state) {
        int key = Arrays.hashCode(state);
        return exploredStates.containsKey(key);
    }
    
    public void addFrontier(List<Node> neighbors) {
        for (int i = 0; i < neighbors.size(); ++i) {
            frontier.add(neighbors.get(i));
        }
    }
    
    public List<Node> createNeighbors(ArrayList<String[]> neighbors, int depth, Node parent) {
        List<Node> nodes = new ArrayList<>();
        String[] state;
        for (int i = 0; i < neighbors.size(); ++i) {
            state = neighbors.get(i);
            nodes.add(new Node(getHamming(state), getManhattan(state), depth, state, parent));
            totalCost++;
        }
        return nodes;
    }
    
    public List<String[]> getNeighbors(Node node) {
        List<String[]> neighbors = new ArrayList<String[]>();
        int size = 3;
        String[] newState;
        int blankIndex = node.getBlankIndex();
        int blankCol = blankIndex % 3;
        int blankRow = blankIndex / 3;    
        // Up
        if (!((blankRow - 1) < 0)) {
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex - size));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            }
        }     
        // Down
        if (!((blankRow + 1) > (size - 1))) {
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex + size));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            }
        }    
        // Left
        if (!((blankCol - 1) < 0)) {
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex - 1));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            }
        }  
        // Right
        if (!((blankCol + 1) > (size - 1))) {
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex + 1));    
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            }    
        }
        return neighbors;
    }
    
    private String[] swap(String[] state, int blankIndex, int newBlankIndex) {
        String[] newState = state;
        String temp = newState[blankIndex];
        newState[blankIndex] = newState[newBlankIndex];
        newState[newBlankIndex] = temp;
        return newState;
    }
    
    public int getHamming(String[] state) {
        int hamming = 0;
        int goal;
        for (int i = 0; i < state.length; ++i) {
            if (!state[i].equals(BLANK)) {
                goal = Integer.parseInt(state[i]);
                if (goal != i) {
                    hamming++;
                }
            }
        }
        return hamming;
    }
    
    public int getManhattan(String[] state) {
        int manhattan = 0;
        int goal;
        for (int i = 0; i < state.length; ++i) {
            if (!state[i].equals(BLANK)) {
                goal = Integer.parseInt(state[i]);
                if (goal != i) {
                    manhattan = manhattan + calculateManhattan(goal, i);
                }
            }
        }
        return manhattan;
    }
    
    public int calculateManhattan(int goal, int cur) {
        int manhattan = 0;
        int col = Math.abs((goal % 3) - (cur % 3));
        int row = Math.abs((goal / 3) - (cur / 3));
        manhattan = col + row;
        return manhattan;
    }
    
    public boolean checkGoal(String[] state) {
        boolean success = true;
        for (int i = 0; i < state.length; ++i) {
            if (!state[i].equals(GOAL_STATE[i])) {
                success = false;
                break;
            }
        }
        return success;
    }
    
    public void printResult() {
        printSolution(goal);
        System.out.println("Total move: " + totalMove);
        System.out.println("Total cost: " + totalCost);
        System.out.println("Time: " + time + " milliseconds");
        System.out.println();
    }
    
    public void printSolution(Node cur) {
        if (cur.getParent() == null) {
            return;
        }
        printSolution(cur.getParent());
        System.out.println("Depth: " + cur.getDepth());
        cur.printState();
    }

    public long getTime() {
        return time;
    }

    public int getTotalMove() {
        return totalMove;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
