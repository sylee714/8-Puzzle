/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8.puzzle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the main class that executes the program.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //prompt();
        //String command = "";
        //command = sc.nextLine();
        //command(command);
        java.io.File file = new java.io.File("depth14.txt");
        String[] puzzle = new String[9];
        ProgramEngine programEngine;
        int testSize = 0;
        double avgHammingMove = 0;
        double avgHammingCost = 0;
        double avgHammingTime = 0;
        double avgManhattanMove = 0;
        double avgManhattanCost = 0;
        double avgManhattanTime = 0;
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String str = input.nextLine();
                System.out.println(str);
                for (int i = 0; i < str.length(); ++i) {
                    puzzle[i] = str.charAt(i) + "";             
                }
                programEngine = new ProgramEngine(puzzle);
                programEngine.solve();
                //programEngine.printResult();
                testSize++;
                avgHammingCost = avgHammingCost + programEngine.getAvgHammingCost();
                avgHammingMove = avgHammingMove + programEngine.getAvgHammingMove();
                avgHammingTime = avgHammingTime + programEngine.getAvgHammingTime();
                avgManhattanCost = avgManhattanCost + programEngine.getAvgManhattanCost();
                avgManhattanMove = avgManhattanMove + programEngine.getAvgManhattanMove();
                avgManhattanTime = avgManhattanTime + programEngine.getAvgManhattanTime();
            }
            System.out.println("Test Size: " + testSize);
            System.out.println("Hamming");
            System.out.println("Average Cost: " + avgHammingCost/testSize);
            System.out.println("Average Move: " + avgHammingMove/testSize);
            System.out.println("Average Time: " + avgHammingTime/testSize + " milliseconds");
            System.out.println("Manhattan");
            System.out.println("Average Cost: " + avgManhattanCost/testSize);
            System.out.println("Average Move: " + avgManhattanMove/testSize);
            System.out.println("Average Time: " + avgManhattanTime/testSize + " milliseconds");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void command(String command) {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        ProgramEngine programEngine;
        String[] puzzle;
        switch(command) {
            // Generate a random puzzle
            case "1":
                puzzle = generatePuzzle();
                displayPuzzle(puzzle);
                programEngine = new ProgramEngine(puzzle);
                programEngine.solve();
                break;
            // User's own puzzle
            case "2":
                puzzle = new String[] {"1", "5", "8", 
                            "2", "0", "7", 
                            "4", "3", "6"};
                displayPuzzle(puzzle);
                programEngine = new ProgramEngine(puzzle);
                programEngine.solve();
                break;
            default:
                System.out.println("Invalid command");
                break;
        }

    }
    
    private static boolean checkPuzzle(String[] puzzle) {
        Checker puzzleChecker = new Checker(puzzle);
        puzzleChecker.check();
        return puzzleChecker.isSolvable();
    }
    
    private static String[] generatePuzzle() {
        String[] puzzle;
        Generator puzzleGenerator = new Generator();
        puzzle = puzzleGenerator.shuffle();
        // Keep shuffling until a valid puzzle is generated
        while (!checkPuzzle(puzzle)) {
            puzzle = puzzleGenerator.shuffle(); 
        }
        return puzzle;
    }
    
    private static void displayPuzzle(String[] puzzle) {
        for (int i = 0; i < puzzle.length; ++i) {
            if ((i % 3) == 0) {
                System.out.println();
            }
            System.out.print(puzzle[i] + " ");    
        }
        System.out.println();
        System.out.println();
    }
}
