package pkg8.puzzle;

/**
 * This class is the program engine that organizes the logics.
 */
public class ProgramEngine {
    private Solver hammingSolver;
    private Solver manhattanSolver;
    private String[] hammingPuzzle;
    private String[] manhattanPuzzle;
    private double avgHammingMove;
    private double avgHammingCost;
    private double avgHammingTime;
    private double avgManhattanMove;
    private double avgManhattanCost;
    private double avgManhattanTime;
    
    public ProgramEngine(String[] state) {
        hammingPuzzle = state.clone();
        manhattanPuzzle = state.clone();
        avgHammingMove = 0;
        avgHammingCost = 0;
        avgHammingTime = 0;
        avgManhattanMove = 0;
        avgManhattanCost = 0;
        avgManhattanTime = 0;
        hammingSolver = new Solver(hammingPuzzle, true);
        manhattanSolver = new Solver(manhattanPuzzle, false);
    }
    
    public void solve() {
        solveHamming();
        solveManhattan();
    }
    
    private void solveHamming() {
        System.out.println("When h1 = the number of misplaced tiles");
        System.out.println();
        hammingSolver.solve();
        avgHammingCost = avgHammingCost + hammingSolver.getTotalCost();
        avgHammingMove = avgHammingMove + hammingSolver.getTotalMove();
        avgHammingTime = avgHammingTime + hammingSolver.getTime();
        hammingSolver.printResult();
    }
    
    private void solveManhattan() {
        System.out.println("When h2 = the sum of the distances of the "
                + "tiles from their goal position");
        System.out.println();
        manhattanSolver.solve();
        avgManhattanCost = avgManhattanCost + manhattanSolver.getTotalCost();
        avgManhattanMove = avgManhattanMove + manhattanSolver.getTotalMove();
        avgManhattanTime = avgManhattanTime + manhattanSolver.getTime();
        manhattanSolver.printResult();
    }

    public double getAvgHammingMove() {
        return avgHammingMove;
    }

    public double getAvgHammingCost() {
        return avgHammingCost;
    }

    public double getAvgHammingTime() {
        return avgHammingTime;
    }

    public double getAvgManhattanMove() {
        return avgManhattanMove;
    }

    public double getAvgManhattanCost() {
        return avgManhattanCost;
    }

    public double getAvgManhattanTime() {
        return avgManhattanTime;
    }
}
