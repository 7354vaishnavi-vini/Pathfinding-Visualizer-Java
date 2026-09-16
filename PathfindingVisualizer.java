import java.util.*;

public class PathfindingVisualizer {

    // Grid dimension boundaries
    static final int ROWS = 10;
    static final int COLS = 20;

    // Grid tracking array, start position, and destination position
    static char[][] grid = new char[ROWS][COLS];
    static int startRow = 0, startCol = 0;
    static int endRow = ROWS - 1, endCol = COLS - 1;

     /**
     * Initializes the grid with empty tiles ('.'), and places 
     * the Start node ('S') and End node ('E').
     */
    static void createGrid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = '.';// Empty path
            }
        }
        grid[startRow][startCol] = 'S';  // Start
        grid[endRow][endCol] = 'E'; // Target
    }

    /**
     * Renders the current state of the grid to the console.
     */
    static void displayGrid() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Validates whether a given grid coordinate can be visited.
     * Checks bounds, avoids obstacles ('#'), and skips already visited locations.
     */
    static boolean valid(int r, int c, boolean[][] visited) {
        return r >= 0 && r < ROWS && c >= 0 && c < COLS
                && grid[r][c] != '#' && !visited[r][c];
    }
    /**
     * Retrieves all valid orthogonal neighbors (Up, Down, Left, Right)
     * that are inside bounds and not blocked by an obstacle ('#').
     */
    static List<int[]> getNeighbors(int r, int c) {
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        List<int[]> list = new ArrayList<>();

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS
                    && grid[nr][nc] != '#') {
                list.add(new int[]{nr, nc});
            }
        }
        return list;
    }
    /**
     * Marks a tile as explored ('*'), reprints the grid, and pauses the execution
     * briefly to animate the pathfinding progression in the console.
     */
    static void markVisited(int r, int c) {
         // Do not overwrite the visual markers for Start and End nodes
        if ((r == startRow && c == startCol) ||
            (r == endRow && c == endCol)) return;

        grid[r][c] = '*';// Mark as evaluated
        displayGrid();
        try {
            Thread.sleep(80); // Delays execution for animation effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ---------------- BFS (Breadth-First Search) ----------------
    /**
     * Explores the grid layer by layer using a Queue.
     * Guarantees the shortest path on unweighted grids.
     */
    static boolean bfs() {
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] parentR = new int[ROWS][COLS];
        int[][] parentC = new int[ROWS][COLS];

        // Fill tracking parent arrays with -1 to reconstruct the path later

        for (int[] row : parentR) Arrays.fill(row, -1);
        for (int[] row : parentC) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            // Reached target node
            if (r == endRow && c == endCol) {
                showPath(parentR, parentC);
                return true;
            }

            for (int[] n : getNeighbors(r, c)) {
                int nr = n[0], nc = n[1];

                if (valid(nr, nc, visited)) {
                    visited[nr][nc] = true;
                    parentR[nr][nc] = r;// Record parent row for tracking back
                    parentC[nr][nc] = c;// Record parent col for tracking back
                    queue.add(new int[]{nr, nc});
                    markVisited(nr, nc);
                }
            }
        }
        return false; // Path not found
    }
    // ---------------- DFS (Depth-First Search) ----------------
    /**
     * Explores as deep as possible along each branch before backtracking using a Stack.
     * Does not guarantee the shortest path.
     */
    static boolean dfs() {
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] parentR = new int[ROWS][COLS];
        int[][] parentC = new int[ROWS][COLS];

        for (int[] row : parentR) Arrays.fill(row, -1);
        for (int[] row : parentC) Arrays.fill(row, -1);

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int r = cur[0], c = cur[1];

            if (r == endRow && c == endCol) {
                showPath(parentR, parentC);
                return true;
            }

            for (int[] n : getNeighbors(r, c)) {
                int nr = n[0], nc = n[1];

                if (valid(nr, nc, visited)) {
                    visited[nr][nc] = true;
                    parentR[nr][nc] = r;
                    parentC[nr][nc] = c;
                    stack.push(new int[]{nr, nc});
                    markVisited(nr, nc);
                }
            }
        }
        return false;
    }

    // ---------------- Dijkstra's Algorithm ----------------
    /**
     * Explores nodes based on cumulative distance from the start using a PriorityQueue.
     * Guarantees the shortest path. (Equivalent to BFS since weight is uniform here).
     */
    static boolean dijkstra() {
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] distance = new int[ROWS][COLS];
        int[][] parentR = new int[ROWS][COLS];
        int[][] parentC = new int[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            Arrays.fill(parentR[i], -1);
            Arrays.fill(parentC[i], -1);
        }

        // PriorityQueue processes nodes with the smallest calculated distance first
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance));

        distance[startRow][startCol] = 0;
        pq.add(new Node(startRow, startCol, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.row, c = cur.col;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (!(r == startRow && c == startCol)) markVisited(r, c);

            if (r == endRow && c == endCol) {
                showPath(parentR, parentC);
                return true;
            }

            // Relaxation step: update cost if a shorter way is discovered
            for (int[] n : getNeighbors(r, c)) {
                int nr = n[0], nc = n[1];

                if (!visited[nr][nc] && distance[r][c] + 1 < distance[nr][nc]) {
                    distance[nr][nc] = distance[r][c] + 1;
                    parentR[nr][nc] = r;
                    parentC[nr][nc] = c;
                    pq.add(new Node(nr, nc, distance[nr][nc]));
                }
            }
        }
        return false;
    }

    // ---------------- A* Search ----------------
    /**
     * An informed search method using both the path cost (g) and a heuristic (h).
     * Minimizes f(n) = g(n) + h(n) to find the shortest path efficiently.
     */
    static boolean aStar() {
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] g = new int[ROWS][COLS];
        int[][] parentR = new int[ROWS][COLS];
        int[][] parentC = new int[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(g[i], Integer.MAX_VALUE);
            Arrays.fill(parentR[i], -1);
            Arrays.fill(parentC[i], -1);
        }
        // PriorityQueue evaluates nodes based on the lowest total cost f(n)
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance));

        g[startRow][startCol] = 0;
        pq.add(new Node(startRow, startCol, heuristic(startRow, startCol)));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.row, c = cur.col;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (!(r == startRow && c == startCol)) markVisited(r, c);

            if (r == endRow && c == endCol) {
                showPath(parentR, parentC);
                return true;
            }

            for (int[] n : getNeighbors(r, c)) {
                int nr = n[0], nc = n[1];
                int newG = g[r][c] + 1; // Movement weight step cost = 1

                if (newG < g[nr][nc]) {
                    g[nr][nc] = newG;
                    parentR[nr][nc] = r;
                    parentC[nr][nc] = c;

                    // f(n) = g(n) + h(n)
                    int f = newG + heuristic(nr, nc);
                    pq.add(new Node(nr, nc, f));
                }
            }
        }
        return false;
    }
    /**
     * Calculates the Manhattan Distance heuristic from a given position to the end node.
     */
    static int heuristic(int r, int c) {
        return Math.abs(r - endRow) + Math.abs(c - endCol);
    }
    /**
     * Traces the parent tracking matrix backward from the destination ('E')
     * to the start source ('S') to mark the absolute shortest path route using 'P'.
     */
    static void showPath(int[][] parentR, int[][] parentC) {
        int r = endRow;
        int c = endCol;
        int length = 0;

        while (!(r == startRow && c == startCol)) {
            if (r == -1 || c == -1) return;

            if (!(r == endRow && c == endCol)) {
                grid[r][c] = 'P';
            }
            // Retrieve parent pointers for current cell
            int pr = parentR[r][c];
            int pc = parentC[r][c];

             // Step backward to the parent node
            r = pr;
            c = pc;
            length++;
        }

        // Explicitly re-assert start and end markers so path tiles don't overwrite them
        grid[startRow][startCol] = 'S';
        grid[endRow][endCol] = 'E';

        System.out.println("Path found!");
        System.out.println("Path length = " + length);
        displayGrid(); // Render the final solved path grid
    }

    /**
     * Minimal representation of a grid cell configuration.
     * Keeps tracking data neat for PriorityQueues during A* and Dijkstra.
     */
    static class Node {
        int row, col, distance;

        Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance; // Stores absolute distance (Dijkstra) or f-score (A*)
        }
    }


    static void addSampleWalls() {
        // Sample obstacles. '#' represents a wall.
    
        for (int i = 2; i < 8; i++) {
            if (i != 5) grid[i][8] = '#';
        }

        for (int i = 3; i < 9; i++) {
            if (i != 6) grid[i][14] = '#';
        }
    }
    /**
     * Main application thread. Sets up resources, handles terminal setup,
     * processes selected choices, and drives execution flags.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        createGrid();
        addSampleWalls();

        System.out.println("===== PATHFINDING VISUALIZER =====");
        System.out.println("S = Start");
        System.out.println("E = End");
        System.out.println("# = Wall");
        System.out.println("* = Visited Node");
        System.out.println("P = Final Path");

        displayGrid();

        // Display selection panel options
        System.out.println("Choose an algorithm:");
        System.out.println("1. Breadth First Search (BFS)");
        System.out.println("2. Depth First Search (DFS)");
        System.out.println("3. Dijkstra's Algorithm");
        System.out.println("4. A* Search");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        boolean found = false;// Evaluates completion condition status

        // Dispatch selection logic matching algorithm handlers
        switch (choice) {
            case 1:
                System.out.println("\nRunning BFS...");
                found = bfs(); // Run unweighted queue solver
                break;

            case 2:
                System.out.println("\nRunning DFS...");
                found = dfs();// Run stack tracking branch depth solver
                break;

            case 3:
                System.out.println("\nRunning Dijkstra...");
                found = dijkstra(); // Run uniform distance weight calculation
                break;

            case 4:
                System.out.println("\nRunning A*...");
                found = aStar();// Run smart heuristic distance calculation
                break;

            default:
                System.out.println("Invalid choice.");
        }
        // Graceful failure check when path loops map without catching 'E'
        if (!found && choice >= 1 && choice <= 4) {
            System.out.println("No path found.");
        }

        sc.close();// Clean up keyboard scanner input stream
    }
}
