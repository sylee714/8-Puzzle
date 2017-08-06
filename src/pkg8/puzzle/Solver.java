/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author MingKie
 */
public class Solver {
    
    private HashMap<Integer, String[]> exploredStates;
    private Queue<Node> frontier;
    private Node init;
    private Node goal;
    private int depth;
    private boolean useHamming;
    //private String[] curState;
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
        frontier.add(init);
        if (!frontier.isEmpty()) {     
            boolean reachedGoal = checkGoal(init.getState());
            ArrayList<Node> neighbors;
            Node cur;
            //Node parent;
            if (useHamming) {
                Node.setUseHamming(true);
            } else {
                Node.setUseHamming(false);
            }
            while (!reachedGoal) {
                // Get the node with the smallest value
                cur = frontier.remove();
                System.out.println("Depth: " + cur.getDepth());
                System.out.println("Hamming: " + cur.getHamming());
                System.out.println("Depth: " + cur.getDepth());
                cur.printState();
                // Check if that node has the goal state
                if (!checkGoal(cur.getState())) {
                    // Add its state to the explored states
                    addExploredStates(cur.getState());
                    // Expand it and do not add ones that are already explored
                    neighbors = (ArrayList<Node>) createNeighbors((ArrayList<String[]>) getNeighbors(cur), cur.getDepth() + 1, cur);
                    printNeighbors(neighbors);
                    // Add its neighbors to the frontier
                    addFrontier(neighbors);
                } else {
                    reachedGoal = true;
                    goal = cur;
                }
                //depth++;
            }
        } else {
            System.out.println("Frontier is empty.");
        }
    }
    
    public void printNeighbors(List<Node> neighbors) {
        System.out.println("Neighbors: ");
        for (int i = 0; i < neighbors.size(); ++i) {
            System.out.println("Hamming: " + neighbors.get(i).getHamming());
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
        //System.out.println("Blank Index: " + blankIndex);
        int blankCol = blankIndex % 3;
        int blankRow = blankIndex / 3;    
        // Up
        if (!((blankRow - 1) < 0)) {
            //System.out.println("Up");
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex - size));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            } else {
                System.out.println("It's an explored state.");
            }
        }     
        // Down
        if (!((blankRow + 1) > (size - 1))) {
            //System.out.println("Down");
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex + size));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            } else {
                System.out.println("It's an explored state.");
            }
        }    
        // Left
        if (!((blankCol - 1) < 0)) {
            //System.out.println("Left");
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex - 1));
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            } else {
                System.out.println("It's an explored state.");
            }
        }  
        // Right
        if (!((blankCol + 1) > (size - 1))) {
            //System.out.println("Right");
            String[] curState = node.getState().clone();
            newState = swap(curState, blankIndex, (blankIndex + 1));    
            if (!checkExploredStates(newState)) {
                neighbors.add(newState);
            } else {
                System.out.println("It's an explored state.");
            }    
        }
        return neighbors;
    }
    
    private String[] swap(String[] state, int blankIndex, int newBlankIndex) {
        String[] newState = state;
        String temp = newState[blankIndex];
        newState[blankIndex] = newState[newBlankIndex];
        newState[newBlankIndex] = temp;
        /*
        for (int i = 0; i < state.length; ++i) {
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(newState[i] + " ");    
        }
        System.out.println();
        */
        return newState;
    }
    
    public int getHamming(String[] state) {
        int hamming = 0;
        int goal;
        for (int i = 0; i < state.length; ++i) {
            if (!state[i].equals(BLANK)) {
                goal = Integer.parseInt(state[i]);
                if (goal != i) {
                    //System.out.println("Goal: " + goal);
                    //System.out.println("Cur: " + i);
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
                //System.out.println("Goal: " + goal);
                //System.out.println("Cur: " + i);
                if (goal != i) {
                    manhattan = manhattan + calculateManhattan(goal, i);
                    //System.out.println(manhattan);
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
    
    public void printSolution(Node cur) {
        if (cur.getParent() == null) {
            return;
        }
        printSolution(cur.getParent());
        System.out.println("Move: " + depth);
        cur.printState();
    }
    
    public int getTotalCost() {
        return totalCost;
    }
    
}
