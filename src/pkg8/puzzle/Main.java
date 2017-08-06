/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author MingKie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Queue<Node> queueHamming = new PriorityQueue<Node>();
        Queue<Node> queueManhattan = new PriorityQueue<Node>();
        
        String[] puzzle1 = {"0", "1", "2", 
                            "3", "4", "5", 
                            "6", "7", "8"};
        
        String[] puzzle2 = {"0", "1", "2", 
                            "3", "4", "5", 
                            "6", "7", "8"};
        
        String[] puzzle3 = {"1", "2", "4", 
                            "0", "5", "6", 
                            "8", "3", "7"};
        
        String[] puzzle6 = {"1", "2", "4", 
                            "0", "5", "6", 
                            "8", "3", "7"};
        
        String[] puzzle4 = {"1", "3", "2", 
                            "4", "5", "6", 
                            "8", "7", "0"};
        
        String[] puzzle7 = {"1", "3", "2", 
                            "4", "5", "6", 
                            "8", "7", "0"};
        
        String[] puzzle5 = {"0", "1", "3", 
                            "4", "2", "5", 
                            "7", "8", "6"};
        
        String[] puzzle8 = {"0", "1", "3", 
                            "4", "2", "5", 
                            "7", "8", "6"};
        
        
        /*
        System.out.println("hashCode: " + Arrays.hashCode(puzzle1));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle2));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle3));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle6));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle4));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle7));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle5));
        System.out.println("hashCode: " + Arrays.hashCode(puzzle8));
        
        System.out.println("Hamming: " + solver.getHamming(puzzle1));
        System.out.println("Hamming: " + solver.getHamming(puzzle2));
        System.out.println("Hamming: " + solver.getHamming(puzzle3));
        System.out.println("Hamming: " + solver.getHamming(puzzle6));
        System.out.println("Hamming: " + solver.getHamming(puzzle4));
        System.out.println("Hamming: " + solver.getHamming(puzzle7));
        System.out.println("Hamming: " + solver.getHamming(puzzle5));
        System.out.println("Hamming: " + solver.getHamming(puzzle8));
        
        System.out.println("Manhattan: " + solver.getManhattan(puzzle1));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle2));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle3));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle6));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle4));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle7));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle5));
        System.out.println("Manhattan: " + solver.getManhattan(puzzle8));
        */
        
        String[] puzzle = {"3", "5", "8", 
                           "6", "0", "2", 
                           "1", "4", "7"};
        
        Solver solver = new Solver(puzzle, false);
        //Solver solver = new Solver(puzzle, true);
        solver.solve();
        System.out.println("Total cost: " + solver.getTotalCost());
        
        //System.out.println("Hamming: " + solver.getHamming(puzzle));
        //System.out.println("Manhattan: " + solver.getManhattan(puzzle));
        
        Node node1 = new Node(solver.getHamming(puzzle1), solver.getManhattan(puzzle5), 0, puzzle1, null);
        Node node2 = new Node(solver.getHamming(puzzle3), solver.getManhattan(puzzle4), 0, puzzle3, null);
        Node node3 = new Node(solver.getHamming(puzzle4), solver.getManhattan(puzzle3), 0, puzzle4, null);
        Node node4 = new Node(solver.getHamming(puzzle5), solver.getManhattan(puzzle1), 0, puzzle5, null);
        Node node5 = new Node(solver.getHamming(puzzle), solver.getManhattan(puzzle), 0, puzzle, null);
        
        Node.setUseHamming(true);
        
        queueHamming.add(node1);
        queueHamming.add(node2);
        queueHamming.add(node3);
        queueHamming.add(node4);
        queueHamming.add(node5);
        
        Node removedNode;
        /*
        while (!queueHamming.isEmpty()) {
            removedNode = queueHamming.remove();
            System.out.println("Hamming + depth: " + (removedNode.getHamming() + removedNode.getDepth()));
            //for (int i = 0; i < removedNode.getState().length; ++i) {
                //System.out.print(removedNode.getState()[i] + " ");
                removedNode.printState();
                //System.out.println("Blank: " + removedNode.getBlankIndex());
            //}
            System.out.println();
        }
        */
        
        /*
        ArrayList<String[]> neighbors = (ArrayList<String[]>) solver.getNeighbors(removedNode);
        System.out.println("Size: " + neighbors.size());
        String[] state = neighbors.remove(0);
         for (int i = 0; i < state.length; ++i) {
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");    
        }
        System.out.println();
        System.out.println("Size: " + neighbors.size());
        state = neighbors.remove(0);
         for (int i = 0; i < state.length; ++i) {
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");    
        }
        System.out.println();
        System.out.println("Size: " + neighbors.size());
        */
        
        /*
        int count = 0;
        while (!queueHamming.isEmpty()) {
            System.out.println("Count: " + count);
            Node removedNode;
            removedNode = queueHamming.remove();
            removedNode.printState();
            ArrayList<String[]> neighbors = (ArrayList<String[]>) solver.getNeighbors(removedNode);
            while (!neighbors.isEmpty()) {
                String[] state = neighbors.remove(0);
                for (int i = 0; i < state.length; ++i) {
                    if ((i % 3) == 0) {
                        System.out.println();
                    }
                    System.out.print(state[i] + " ");    
                }
                System.out.println();
            }
            count++;
            System.out.println();
        }
        
        Node testNode = new Node(5,5,0,puzzle, null);
        if (testNode.getParent() == null) {
            System.out.println("Parent is null");
        }
        */
        
        //removedNode.printState();
        /*
        for (int i = 0 ; i < 3; ++i) {
            String[] state = neighbors.remove(0);
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");
        }
        */
        
        /*
        while (!queueHamming.isEmpty()) {
            removedNode = queueHamming.remove();
            System.out.println("Hamming + depth: " + (removedNode.getHamming() + removedNode.getDepth()));
            //for (int i = 0; i < removedNode.getState().length; ++i) {
                //System.out.print(removedNode.getState()[i] + " ");
                removedNode.printState();
                System.out.println("Blank: " + removedNode.getBlankIndex());
            //}
            System.out.println();
        }
        
        
        
        
        Node.setUseHamming(false);
        
        queueManhattan.add(node1);
        queueManhattan.add(node2);
        queueManhattan.add(node3);
        queueManhattan.add(node4);
        queueManhattan.add(node5);
        
        
        while (!queueManhattan.isEmpty()) {
            removedNode = queueManhattan.remove();
            System.out.println("Manhattan + depth: " + (removedNode.getManhattan() + removedNode.getDepth()));
            //for (int i = 0; i < removedNode.getState().length; ++i) {
                //System.out.print(removedNode.getState()[i] + " ");
                removedNode.printState();
                System.out.println("Blank: " + removedNode.getBlankIndex());
            //}
            System.out.println();
        }
        */
        /*
        Checker checker = new Checker(puzzle, 0, false);
        checker.check();
        
        System.out.println("Num of inversion: " + checker.getInversion());
        System.out.println("Is it solvable: " + checker.isSolvable());
        */
    }
    
}
