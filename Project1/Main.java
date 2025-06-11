public class Main {

    static final int boardSize = 8;

    static int[][] chessBoard  = new int[8][8];

    //possible moves for the knight
    static int[] xMoves= {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    //solving the knight's tour with backtracking + Warnsdorff' rule
    public static boolean solveKnightTour(int x, int y, int moveCount){

        if(moveCount > boardSize * boardSize){
            return true; // all squares are visited
        }

        int [][] nextMoves = getSortedMoves(x, y);

        for (int i = 0; i < nextMoves.length; i++){
            int nextX = nextMoves[i][0];
            int nextY = nextMoves[i][1];

            // mark the square as visited
            chessBoard[nextX][nextY] = moveCount;

            // recursive call to continue the tour
            if(solveKnightTour(nextX, nextY, moveCount + 1)){
                return true; // found a solution
            }

            // backtrack
            chessBoard[nextX][nextY] = 0;
        }

        return false; // no solution 
    }

    // getting next possible moves for the knight --> sorted by Warnsdorff's Rule (least onward moves)
    public static int[][] getSortedMoves(int x, int y){
        java.util.List<int[]> moves = new java.util.ArrayList<>();

        for(int i = 0; i < 8; i++){
            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];

            if(isSafe(nextX, nextY)){
                int forwardMoves = countForwardMoves(nextX, nextY);
                moves.add(new int[]{nextX, nextY, forwardMoves});
            }
        }

        // sort by the number of forward moves
        moves.sort(java.util.Comparator.comparingInt(a -> a[2]));

        int[][] result = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++){
            result[i][0] = moves.get(i)[0];
            result[i][1] = moves.get(i)[1];
        }

        return result; 

    }

    public static int countForwardMoves(int x, int y){
        int count = 0;

        for (int i = 0; i < 8; i++){
            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];

            if(isSafe(nextX, nextY)){
                count++;
            }
        }
        return count;
    }

    public static boolean isSafe(int x, int y){
        
        return (x >= 0 && y >= 0 && x < boardSize && y < boardSize && chessBoard[x][y] == 0);
    }

    public static boolean isValidStart(int x, int y){

        if(x < 0 || x >= boardSize || y < 0 || y >= boardSize){
            return false;
        }

        return true;
    }

    public static void printBoard(){
        for(int[] row : chessBoard){
            for(int val : row){
                System.out.printf("%2d ", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        //initializing unvisited spaces with 0 
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                chessBoard[i][j] = 0;
            }
        }

        //starting position of the knight
        int startX;
        int startY;
        System.out.println("Enter the starting position of your knight (x, y): ");
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        startX = scanner.nextInt();
        startY = scanner.nextInt();
        scanner.close();

        // checking if the starting position is valid
        if(!isValidStart(startX, startY)){
            System.out.println("Invalid starting position. Please enter a new starting position:");
            return;
        }

        chessBoard[startX][startY] = 1; 

        //timing the program
        long startTime = System.currentTimeMillis();

        // solve the knights tour
        boolean solved = solveKnightTour(startX, startY, 2);

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        if(solved){
            printBoard();
            System.out.println("Knight's tour completed in " + time + "ms.");
        }
        else{
            System.out.println("No solution found for the knight's tour.");
        }
    }
 
    
}
