package pkg8.puzzle;

import java.util.Scanner;

/**
 * This class is the main class that executes the program.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prompt();
        String command = "";
        command = sc.nextLine();
        command(command);      
    }
    
    private static void command(String command) {
        Scanner sc = new Scanner(System.in);
        ProgramEngine programEngine;
        String[] puzzle = new String[9];
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
                System.out.println("Enter one row at a time.");
                System.out.println("Ex: 1 2 3");
                boolean[] checkDuplicates = new boolean[9];
                for (int i = 0; i < checkDuplicates.length; ++i) {
                    checkDuplicates[i] = false;
                }
                int checkInt;
                String[] input = new String[3];
                String[] numbers;
                int index = 0;
                for (int i = 0; i < input.length; ++i) {
                    input[i] = sc.nextLine();
                    // Check if inputs have only integers and it does not start
                    // with a blank space
                    if (checkInput(input[i])) {
                        numbers = input[i].split(" ");
                        // Check if inputs have 3 integers
                        if (numbers.length == 3) {
                            // Check if inputs contains only numbers from 0 - 9
                            if (checkValue(numbers)) {
                                // Check duplicates
                                if (checkDuplicates(numbers, checkDuplicates)) {
                                    for (int j = 0; j < numbers.length; ++j) {
                                        checkInt = Integer.parseInt(numbers[j]);
                                        puzzle[index] = numbers[j];
                                        checkDuplicates[checkInt] = true;
                                        index++;
                                    }
                                } else {
                                    System.out.println("No duplicated numbers.");
                                    i--;
                                }
                            } else {
                                System.out.println("Enter valid inputs.");
                                i--;
                            }
                        } else {
                            System.out.println("Enter valid inputs.");
                            i--;
                        }
                    } else {
                        System.out.println("Enter valid inputs.");
                        i--;
                    }
                }
                displayPuzzle(puzzle);
                // Check if the puzzle is solvable
                if (checkPuzzle(puzzle)) {
                    programEngine = new ProgramEngine(puzzle);
                    programEngine.solve();
                } else {
                    System.out.println("It's not solvable.");
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
    
    /**
     * Check if a number is already entered.
     * @param indices, indices that are to be checked
     * @param arr, an array of boolean
     * @return true if no numbers were ever entered before
     */
    private static boolean checkDuplicates(String[] indices, boolean[] arr) {
        boolean success = true;
        int index1 = Integer.parseInt(indices[0]);
        int index2 = Integer.parseInt(indices[1]);
        int index3 = Integer.parseInt(indices[2]);
        if (!checkIndex(index1, arr)) {
            success = false;
        }
        if (!checkIndex(index2, arr)) {
            success = false;
        }
        if (!checkIndex(index3, arr)) {
            success = false;
        }
        return success;
    }
    
    /**
     * Check if the element with the given index is already occupied.
     * @param index, the index of the element
     * @param arr, an array of boolean
     * @return true if it was not occupied
     */
    private static boolean checkIndex(int index, boolean[] arr) {
        boolean success = true;
        // If it's true, return false
        if (arr[index]) {
            success = false;
        }
        return success;
    }
    
    /**
     * Check inputs have only numbers from 0 - 9.
     * @param values, inputs
     * @return true if it's consisted of numbers 0 - 9
     */
    private static boolean checkValue(String[] values) {
        boolean success = true;
        int checkInt;
        for (int i = 0; i < values.length; ++i) {
            checkInt = Integer.parseInt(values[i]);
            if (checkInt > 9 || checkInt < 0) {
                success = false;
                break;
            }
        }
        return success;
    }
    
    /**
     * Check the input has only integers and start with no blank space.
     * @param input, input
     * @return true if it satisfies those two conditions
     */
    private static boolean checkInput(String input) {
        boolean success = true;
        int checkInt;
        if (!(input.charAt(0) == ' ')) {
            for (int i = 0; i < input.length(); ++i) {
                if (!(input.charAt(i) == ' ')) {
                    try {
                        checkInt = Integer.parseInt(input.charAt(i) + "");
                        if (checkInt > 9 || checkInt < 0) {
                            success = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        success = false;
                        break;
                    }
                }
            }
        } else {
            success = false;
        }
        return success;
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

    private static void prompt() {
        System.out.println("Command Options:");
        System.out.println("\t1: Generate a random puzzle");
        System.out.println("\t2: Enter your own puzzle");
    }
}
