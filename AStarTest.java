public class AStarTest{
    public static void main(String[] args) {
            int grid[][] = { 
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, 
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
            { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, 
            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
            { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
            { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
            { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 } 
        }; 
        AStarAlgorithm alg = new AStarAlgorithm();
        alg.algorithm(grid, 8, 0, 0, 0);
    }
}
