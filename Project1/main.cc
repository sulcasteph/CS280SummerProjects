
#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>

const int boardSize = 8;

int chessBoard[8][8];

//possible moves for the knight
int xMoves[] = {2, 1, -1, -2, -2, -1, 1, 2};
int yMoves[] = {1, 2, 2, 1, -1, -2, -2, -1};

// Function declarations
bool solveKnightTour(int x, int y, int moveCount);
std::vector<std::vector<int>> getSortedMoves(int x, int y);
int countForwardMoves(int x, int y);
bool isSafe(int x, int y);
bool isValidStart(int x, int y);
void printBoard();

//solving the knight's tour with backtracking + Warnsdorff' rule
bool solveKnightTour(int x, int y, int moveCount) {
    if(moveCount > boardSize * boardSize) {
        return true; // all squares are visited
    }

    std::vector<std::vector<int>> nextMoves = getSortedMoves(x, y);

    for (int i = 0; i < nextMoves.size(); i++) {
        int nextX = nextMoves[i][0];
        int nextY = nextMoves[i][1];

        // mark the square as visited
        chessBoard[nextX][nextY] = moveCount;

        // recursive call to continue the tour
        if(solveKnightTour(nextX, nextY, moveCount + 1)) {
            return true; // found a solution
        }

        // backtrack
        chessBoard[nextX][nextY] = 0;
    }

    return false; // no solution 
}

// getting next possible moves for the knight --> sorted by Warnsdorff's Rule (least onward moves)
std::vector<std::vector<int>> getSortedMoves(int x, int y) {
    std::vector<std::vector<int>> moves;

    for(int i = 0; i < 8; i++) {
        int nextX = x + xMoves[i];
        int nextY = y + yMoves[i];

        if(isSafe(nextX, nextY)) {
            int forwardMoves = countForwardMoves(nextX, nextY);
            moves.push_back({nextX, nextY, forwardMoves});
        }
    }

    // sort by the number of forward moves
    std::sort(moves.begin(), moves.end(), [](const std::vector<int>& a, const std::vector<int>& b) {
        return a[2] < b[2];
    });

    std::vector<std::vector<int>> result;
    for (int i = 0; i < moves.size(); i++) {
        result.push_back({moves[i][0], moves[i][1]});
    }

    return result; 
}

int countForwardMoves(int x, int y) {
    int count = 0;

    for (int i = 0; i < 8; i++) {
        int nextX = x + xMoves[i];
        int nextY = y + yMoves[i];

        if(isSafe(nextX, nextY)) {
            count++;
        }
    }
    return count;
}

bool isSafe(int x, int y) {
    return (x >= 0 && y >= 0 && x < boardSize && y < boardSize && chessBoard[x][y] == 0);
}

bool isValidStart(int x, int y) {
    if(x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
        return false;
    }

    return true;
}

void printBoard() {
    for(int i = 0; i < 8; i++) {
        for(int j = 0; j < 8; j++) {
            std::cout << std::setw(2) << chessBoard[i][j] << " ";
        }
        std::cout << std::endl;
    }
}

int main() {
    //initializing unvisited spaces with 0 
    for(int i = 0; i < 8; i++) {
        for(int j = 0; j < 8; j++) {
            chessBoard[i][j] = 0;
        }
    }

    //starting position of the knight
    int startX;
    int startY;
    std::cout << "Enter the starting position of your knight (x, y): " << std::endl;
    
    std::cin >> startX >> startY;
    chessBoard[startX][startY] = 1; 

    // checking if the starting position is valid
    if(!isValidStart(startX, startY)) {
        std::cout << "Invalid starting position. Please enter a new starting position:" << std::endl;
        return 0;
    }

    // solve the knights tour
    if (solveKnightTour(startX, startY, 2)) {
        printBoard();
    }
    else {
        std::cout << "No solution found :(" << std::endl;
    }

    return 0;
}