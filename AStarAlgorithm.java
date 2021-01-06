import java.util.Stack;
import java.util.Queue;
import java.util.*;
public class AStarAlgorithm{

    public static int ROW = 9;
    public static int COL = 10;

    public static boolean isValid(int[][] grid, int row, int col){
        return row>=0 && row<grid.length && col>=0 && col<grid[0].length;
    }

    public static boolean isOpen(int[][] grid, int row, int col){
        if (grid[row][col] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean arrived(int row, int col, int endX, int endY){
        if (row == endX && col == endY){
            return true;
        }
        else{
            return false;
        }
    }

    public static double calc_heuristic(int row, int col, int endX, int endY){
        return Math.sqrt((row-endX)*(row-endX)+(col-endY)*(col-endY));
    }

    public static void tracePath(Node nodeDetails[][], int endX, int endY){

        System.out.println("\nThe Path is");

        int row = endX;
        int col = endY;

        Stack<Integer> PathX = new Stack<Integer>();
        Stack<Integer> PathY = new Stack<Integer>() ;

        while (!(nodeDetails[row][col].parent_i == row && nodeDetails[row][col].parent_j == col)){
            PathX.push(row);
            PathY.push(col);
            row = nodeDetails[row][col].parent_i;
            col = nodeDetails[row][col].parent_j;
        }
        PathX.push(row);
        PathY.push(col);

        while (!(PathX.empty() && PathY.empty())){
            int x = PathX.pop();
            int y = PathY.pop();
            System.out.print("-> (" + x + ", " + y +")");
        }

        return;
    }

    public static void algorithm(int grid[][], int startX, int startY, int endX, int endY){

        if (isValid(grid, startX, startY) == false){
            System.out.println("Start is not valid");
            return;
        }

        if (isValid(grid, endX, endY) == false){
            System.out.println("End is not valid");
            return;
        }

        if (isOpen(grid, startX, startY) == false){
            System.out.print("Start is Blocked");
            return;
        }

        if (isOpen(grid, endX, endY) == false){
            System.out.print("End is Blocked");
            return;
        }


        boolean[][] closed = new boolean[ROW][COL];

        Node[][] nodeDetails = new Node[ROW][COL];

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++){
                Node temp_node = new Node(-1, -1);
                nodeDetails[i][j] = temp_node;
                nodeDetails[i][j].f = 99999;
                nodeDetails[i][j].g = 99999;
                nodeDetails[i][j].h = 99999;
            }
        }

        int x = startX;
        int y = startY;
        
        nodeDetails[x][y].f = 0;
        nodeDetails[x][y].g = 0;
        nodeDetails[x][y].h = 0;
        nodeDetails[x][y].parent_i = x;
        nodeDetails[x][y].parent_j = y;

        boolean found_target = false;

        Queue<info> openList = new LinkedList<info>();

        info temp = new info(0, x, y);

        openList.add(temp);

        while (!openList.isEmpty()){

            info temp_node1 = openList.peek();

            // System.out.println(temp_node1.f + "," + temp_node1.y + "," + temp_node1.x);

            x = temp_node1.x;
            y = temp_node1.y;

            openList.remove();
            closed[x][y] = true;

            double gNew;
            double hNew;
            double fNew;

            if (isValid(grid, x-1, y) == true){
                if (arrived(x-1, y, endX, endY) == true){
                    nodeDetails[x-1][y].parent_i = x;
                    nodeDetails[x-1][y].parent_j = y;
                    tracePath(nodeDetails, endX, endY);
                    found_target = true;
                    return;
                }
                else if (closed[x-1][y] == false && isOpen(grid, x-1, y) == true){
                    gNew = nodeDetails[x][y].g + 1;
                    hNew = calc_heuristic(x-1, y, endX, endY);
                    fNew = gNew + hNew;
                    if(nodeDetails[x-1][y].f == 99999 || nodeDetails[x-1][y].f > fNew){
                        info temp2 = new info(fNew, x-1, y);
                        openList.add(temp2);
                        nodeDetails[x-1][y].f = fNew;
                        nodeDetails[x-1][y].g = gNew;
                        nodeDetails[x-1][y].h = hNew;
                        nodeDetails[x-1][y].parent_i = x;
                        nodeDetails[x-1][y].parent_j = y;
                    }
                }
            }

            if (isValid(grid, x+1, y) == true){
                if (arrived(x+1, y, endX, endY) == true){
                    nodeDetails[x+1][y].parent_i = x;
                    nodeDetails[x+1][y].parent_j = y;
                    tracePath(nodeDetails, endX, endY);
                    found_target = true;
                    return;
                }
                else if (closed[x+1][y] == false && isOpen(grid, x+1, y) == true){
                    gNew = nodeDetails[x][y].g + 1;
                    hNew = calc_heuristic(x+1, y, endX, endY);
                    fNew = gNew + hNew;
                    if(nodeDetails[x+1][y].f == 99999 || nodeDetails[x+1][y].f > fNew){
                        info temp2 = new info(fNew, x+1, y);
                        openList.add(temp2);
                        nodeDetails[x+1][y].f = fNew;
                        nodeDetails[x+1][y].g = gNew;
                        nodeDetails[x+1][y].h = hNew;
                        nodeDetails[x+1][y].parent_i = x;
                        nodeDetails[x+1][y].parent_j = y;
                    }
                }
            }

            if (isValid(grid, x, y-1) == true){
                if (arrived(x, y-1, endX, endY) == true){
                    nodeDetails[x][y-1].parent_i = x;
                    nodeDetails[x][y-1].parent_j = y;
                    tracePath(nodeDetails, endX, endY);
                    found_target = true;
                    return;
                }
                else if (closed[x][y-1] == false && isOpen(grid, x, y-1) == true){
                    gNew = nodeDetails[x][y].g + 1;
                    hNew = calc_heuristic(x, y-1, endX, endY);
                    fNew = gNew + hNew;
                    if(nodeDetails[x][y-1].f == 99999 || nodeDetails[x][y-1].f > fNew){
                        info temp2 = new info(fNew, x, y-1);
                        openList.add(temp2);
                        nodeDetails[x][y-1].f = fNew;
                        nodeDetails[x][y-1].g = gNew;
                        nodeDetails[x][y-1].h = hNew;
                        nodeDetails[x][y-1].parent_i = x;
                        nodeDetails[x][y-1].parent_j = y;

                    }
                }
            }

            if (isValid(grid, x, y+1) == true){
                if (arrived(x, y+1, endX, endY) == true){
                    nodeDetails[x][y+1].parent_i = x;
                    nodeDetails[x][y+1].parent_j = y;
                    tracePath(nodeDetails, endX, endY);
                    found_target = true;
                    return;
                }
                else if (closed[x][y+1] == false && isOpen(grid, x, y+1) == true){
                    gNew = nodeDetails[x][y].g + 1;
                    hNew = calc_heuristic(x, y+1, endX, endY);
                    fNew = gNew + hNew;
                    if(nodeDetails[x][y+1].f == 99999 || nodeDetails[x][y+1].f > fNew){
                        info temp2 = new info(fNew, x, y+1);
                        openList.add(temp2);
                        nodeDetails[x][y+1].f = fNew;
                        nodeDetails[x][y+1].g = gNew;
                        nodeDetails[x][y+1].h = hNew;
                        nodeDetails[x][y+1].parent_i = x;
                        nodeDetails[x][y+1].parent_j = y;
                    }
                }
            }            
        }
        if (found_target == false){
            System.out.println("Failed to find destination");
        }
        return;
    }


    public static void main(String[] args) {
        int grid[][] = { 
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, 
        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
        { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, 
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
        { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
        { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 } 
    }; 

    algorithm(grid, 0, 0, 0, 9);
    }

}