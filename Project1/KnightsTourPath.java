import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class KnightsTourPath extends JPanel {
    
    static final int N = 8;
    static int[][] board = new int[N][N];
    static final int SIZE = 80;
    static int[] moveX = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};
    static ArrayList<Point> path = new ArrayList<>();
    static KnightsTourPath panel;
    BufferedImage knightImage;

    public KnightsTourPath() {
        try {
            knightImage = ImageIO.read(new File("blackKnight.png"));
        } catch (Exception e) {
            System.out.println("Knight image not found.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Setup GUI window
        JFrame frame = new JFrame("Knight's Tour Visualization");
        panel = new KnightsTourPath();
        frame.add(panel);
        frame.setSize(N * SIZE + 16, N * SIZE + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Initialize board
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = 0;

        // Start the knight at (0, 0)
        board[0][0] = 1;
        path.add(new Point(0, 0));

        // Solve in a new thread to allow GUI to update
        new Thread(() -> solve(0, 0, 2)).start();
    }

    static boolean solve(int x, int y, int moveNum) {
        if (moveNum > N * N) return true;

        java.util.List<Point> moves = getSortedMoves(x, y);
        for (Point move : moves) {
            int nx = move.x;
            int ny = move.y;
            board[nx][ny] = moveNum;
            path.add(new Point(nx, ny));
            panel.repaint();
            sleep(200);

            if (solve(nx, ny, moveNum + 1)) return true;

            board[nx][ny] = 0;
            path.remove(path.size() - 1);
        }

        return false;
    }

    static java.util.List<Point> getSortedMoves(int x, int y) {
        java.util.List<Point[]> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];
            if (isSafe(nx, ny)) {
                int degree = countOnwardMoves(nx, ny);
                moves.add(new Point[]{new Point(nx, ny), new Point(degree, 0)});
            }
        }
        moves.sort(Comparator.comparingInt(a -> a[1].x));
        java.util.List<Point> result = new ArrayList<>();
        for (Point[] pair : moves) result.add(pair[0]);
        return result;
    }

    static int countOnwardMoves(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + moveX[i];
            int ny = y + moveY[i];
            if (isSafe(nx, ny)) count++;
        }
        return count;
    }

    static boolean isSafe(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && board[x][y] == 0;
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Paint method to draw the board, moves, knight, and path
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw chessboard
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                boolean isWhite = (row + col) % 2 == 0;
                g.setColor(isWhite ? Color.WHITE : Color.GRAY);
                g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(col * SIZE, row * SIZE, SIZE, SIZE);

                if (board[row][col] > 0) {
                    g.setColor(Color.BLACK);  // move number
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString(String.valueOf(board[row][col]),
                            col * SIZE + SIZE / 3,
                            row * SIZE + 2 * SIZE / 3);
                }
            }
        }

        // Draw path lines
        g.setColor(Color.BLACK);
        for (int i = 1; i < path.size(); i++) {
            Point p1 = path.get(i - 1);
            Point p2 = path.get(i);
            int x1 = p1.y * SIZE + SIZE / 2;
            int y1 = p1.x * SIZE + SIZE / 2;
            int x2 = p2.y * SIZE + SIZE / 2;
            int y2 = p2.x * SIZE + SIZE / 2;
            g.drawLine(x1, y1, x2, y2);
        }

        // Draw knight image at current location
        if (!path.isEmpty() && knightImage != null) {
            Point last = path.get(path.size() - 1);
            int cx = last.y * SIZE;
            int cy = last.x * SIZE;
            g.drawImage(knightImage, cx, cy, SIZE, SIZE, this);
        }
    }
}
