# PROJECT REPORT
# PATHFINDING VISUALIZER USING JAVA

**Student Name:** Vaishnavi Tiwari  
**Program:** B.Tech Computer Science (AI & ML)

---

## 1. Abstract

Pathfinding is the process of finding a route between two points while avoiding obstacles. Pathfinding algorithms are important in Artificial Intelligence, robotics, computer games, navigation and many other applications.

This project implements a Java-based Pathfinding Visualizer. It represents the environment as a two-dimensional grid and demonstrates four search algorithms: BFS, DFS, Dijkstra and A*. The program displays the grid in the console and marks explored nodes and the final path using different symbols.

---

## 2. Introduction

Finding an efficient route between two locations is a common computational problem. A grid can be treated as a graph where each free cell represents a node and adjacent cells represent connections.

Different algorithms use different strategies to explore these nodes. By visualizing the search process, the differences between these algorithms become easier to understand.

---

## 3. Problem Statement

Graph traversal algorithms are often difficult for beginners to understand only through theory. The purpose of this project is to create a simple Java program that demonstrates how different pathfinding algorithms explore a grid and find a route around obstacles.

---

## 4. Objectives

- To implement pathfinding algorithms in Java.
- To represent a graph using a two-dimensional array.
- To understand BFS and DFS traversal.
- To implement shortest-path algorithms.
- To use Java's Queue, Stack and PriorityQueue.
- To display visited nodes and the final path.
- To connect Data Structures and Artificial Intelligence concepts with a practical project.

---

## 5. Proposed System

The system creates a 10 × 20 grid.

The following symbols are used:

- `S` – Start
- `E` – End
- `#` – Wall
- `.` – Empty cell
- `*` – Visited cell
- `P` – Final path

Sample obstacles are automatically added to demonstrate how algorithms work around walls.

The user selects one of four algorithms, after which the program performs the search and displays the progress.

---

## 6. Technologies Used

### Java

Java is used as the main programming language because it provides built-in data structures and is suitable for implementing algorithms using object-oriented programming.

### Data Structures

The project uses:

- 2D arrays for the grid.
- Queue for BFS.
- Stack for DFS.
- PriorityQueue for Dijkstra and A*.
- Arrays for visited, distance and parent information.

---

# 7. Algorithm Design

## 7.1 Breadth-First Search

BFS explores all neighbouring nodes at the current distance before moving to the next level.

### Steps

1. Put the start node into a queue.
2. Mark it visited.
3. Remove one node from the queue.
4. Check its valid neighbours.
5. Add unvisited neighbours to the queue.
6. Store their parent.
7. Continue until the destination is reached.
8. Trace parent nodes to construct the path.

### Advantage

BFS finds a shortest path in an unweighted grid.

### Complexity

O(V + E)

---

## 7.2 Depth-First Search

DFS explores one branch as deeply as possible before moving to another branch.

### Steps

1. Push the start node onto a stack.
2. Mark it visited.
3. Pop a node.
4. Add its unvisited neighbours to the stack.
5. Continue until the destination is found.

### Limitation

DFS does not guarantee the shortest path.

### Complexity

O(V + E)

---

## 7.3 Dijkstra's Algorithm

Dijkstra's algorithm maintains the smallest known distance from the start node.

### Steps

1. Set the start distance to zero.
2. Set other distances to infinity.
3. Select the node with the smallest distance.
4. Relax its neighbouring nodes.
5. Store parent information.
6. Continue until the destination is reached.
7. Reconstruct the path.

The Java implementation uses `PriorityQueue`.

---

## 7.4 A* Search

A* uses both the distance travelled and an estimate of the remaining distance.

The formula is:

`f(n) = g(n) + h(n)`

Where:

- `g(n)` = actual cost from start to current node.
- `h(n)` = estimated cost from current node to end.
- `f(n)` = total estimated cost.

The project uses Manhattan distance:

`h(n) = |x1 - x2| + |y1 - y2|`

because movement is restricted to four directions.

---

# 8. System Workflow

```text
Start Program
      |
      v
Create Grid
      |
      v
Add Sample Obstacles
      |
      v
Display Grid
      |
      v
Select Algorithm
      |
      +-----------------------------+
      |       |        |            |
     BFS     DFS   Dijkstra         A*
      |       |        |            |
      +-------+--------+------------+
                  |
                  v
          Explore Valid Nodes
                  |
                  v
            Reach Destination?
              /                      Yes           No
             |             |
             v             v
       Reconstruct     No Path
          Path
             |
             v
        Display Result
```

---

# 9. Implementation

The main Java class is `PathfindingVisualizer`.

Important methods include:

- `createGrid()` – creates the grid.
- `displayGrid()` – prints the grid.
- `bfs()` – performs Breadth-First Search.
- `dfs()` – performs Depth-First Search.
- `dijkstra()` – performs Dijkstra's algorithm.
- `aStar()` – performs A* search.
- `showPath()` – reconstructs and displays the final path.
- `getNeighbors()` – returns valid neighbouring cells.
- `heuristic()` – calculates the Manhattan-distance heuristic.

---

# 10. Testing

| Test Case | Expected Result |
|---|---|
| BFS selected | BFS explores the grid and finds a path |
| DFS selected | DFS explores a branch-based route |
| Dijkstra selected | Shortest route is found |
| A* selected | Heuristic-guided route is found |
| Walls present | Algorithms avoid wall cells |
| Destination blocked by walls | Program reports no path |
| Invalid menu option | Program displays invalid-choice message |

---

# 11. Sample Output

```text
===== PATHFINDING VISUALIZER =====

S . . . . . . . . . . . . . . . . . . .
. . . . . . . . . . . . . . . . . . . .
. . . . . . . . # . . . . . . . . . . .
. . . . . . . . # . . . . . . # . . . .
. . . . . . . . # . . . . . . # . . . .
. . . . . . . . . . . . . . . # . . . .
. . . . . . . . # . . . . . . . . . . .
. . . . . . . . # . . . . . . # . . . .
. . . . . . . . . . . . . . # . . . . .
. . . . . . . . . . . . . . . . . . . E

Choose an algorithm:
1. Breadth First Search (BFS)
2. Depth First Search (DFS)
3. Dijkstra's Algorithm
4. A* Search

Enter choice: 4
```

After execution, `*` cells represent explored nodes and `P` cells represent the reconstructed path.

---

# 12. Advantages

1. Simple and beginner-friendly.
2. Uses standard Java features.
3. Demonstrates important algorithms practically.
4. Shows the relationship between graphs and pathfinding.
5. Uses appropriate data structures for different algorithms.
6. Useful as an educational AI/DSA project.

---

# 13. Limitations

1. The current version is console-based.
2. Walls are predefined rather than entered interactively.
3. Movement is limited to four directions.
4. The grid size is fixed.
5. It does not currently provide a graphical interface.

---

# 14. Future Scope

The project can be improved by:

- Developing a Java Swing or JavaFX graphical interface.
- Allowing users to draw walls using the mouse.
- Adding diagonal movement.
- Adding weighted cells.
- Adding maze-generation algorithms.
- Adding animation speed controls.
- Displaying algorithm statistics.
- Comparing the number of visited nodes and path lengths.

---

# 15. Learning Outcomes

After completing this project, the student can understand:

- Graph representation using grids.
- BFS and DFS.
- Shortest-path algorithms.
- Heuristic search and A*.
- Queues and stacks.
- Priority queues.
- Parent-based path reconstruction.
- Java collections.
- Basic application of Artificial Intelligence algorithms.

---

# 16. Conclusion

The Pathfinding Visualizer successfully demonstrates the practical implementation of BFS, DFS, Dijkstra and A* search algorithms using Java.

The project combines Data Structures and Algorithms with Artificial Intelligence concepts. By displaying visited nodes and the final path in the console, it provides an easy way for students to understand how different pathfinding techniques operate.

The project can be further developed into a graphical Java application with interactive obstacles and additional algorithms.

---

# 17. References

1. Java programming and Java Collections concepts.
2. Standard graph traversal and shortest-path algorithm concepts.
3. Artificial Intelligence search algorithm concepts.
4. Classroom notes on Data Structures and Algorithms.
