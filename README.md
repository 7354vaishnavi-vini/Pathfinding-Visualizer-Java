# Pathfinding Visualizer in Java

**Student:** Vaishnavi Tiwari  
**Program:** B.Tech Computer Science (AI & ML)

## 1. Project Overview

Pathfinding Visualizer is a Java console-based project that demonstrates how graph-search algorithms find a route from a start node to an end node while avoiding obstacles.

The project implements four important algorithms:

- Breadth-First Search (BFS)
- Depth-First Search (DFS)
- Dijkstra's Algorithm
- A* Search

The grid is printed after nodes are explored, creating a simple console visualization.

## 2. Symbols Used

| Symbol | Meaning |
|---|---|
| S | Start node |
| E | End node |
| # | Wall/obstacle |
| * | Visited node |
| P | Final path |
| . | Empty cell |

## 3. Objectives

1. Implement pathfinding algorithms using Java.
2. Understand graph traversal practically.
3. Use queues, stacks, sets/arrays and priority queues.
4. Visualize algorithm exploration in the console.
5. Compare different pathfinding approaches.

## 4. Technologies

- Java
- Object-Oriented Programming
- Arrays
- Queue
- Stack
- PriorityQueue
- Collections Framework

## 5. How to Run

### Step 1: Compile

```bash
javac PathfindingVisualizer.java
```

### Step 2: Run

```bash
java PathfindingVisualizer
```

### Step 3: Select an algorithm

Enter:
- `1` for BFS
- `2` for DFS
- `3` for Dijkstra
- `4` for A*

The program contains sample walls so the search can be visualized immediately.

## 6. Algorithm Explanation

### BFS
BFS uses a queue and explores nodes level by level. For an unweighted grid, it can find the shortest path.

### DFS
DFS uses a stack and explores one branch deeply before backtracking. It can find a path but does not guarantee the shortest path.

### Dijkstra
Dijkstra selects the node having the smallest known distance from the source. It guarantees a shortest path when edge weights are non-negative.

### A*
A* uses:

`f(n) = g(n) + h(n)`

where `g(n)` is the distance travelled and `h(n)` is the Manhattan-distance heuristic to the destination.

## 7. Complexity

Let V be the number of cells and E the number of connections.

- BFS: O(V + E)
- DFS: O(V + E)
- Dijkstra: approximately O((V + E) log V) with the PriorityQueue implementation
- A*: depends on the heuristic and explored nodes; worst-case behaviour can approach Dijkstra's search

## 8. Project Structure

```text
Pathfinding_Visualizer_Java/
├── PathfindingVisualizer.java
├── README.md
└── Project_Report.md
```

## 9. Future Enhancements

- Allow users to enter their own walls.
- Add a Java Swing/JavaFX graphical interface.
- Add diagonal movement.
- Add weighted nodes.
- Add maze generation.
- Add animation-speed controls.
- Display execution statistics.

## 10. Learning Outcome

This project demonstrates practical use of data structures and algorithms in Java. It also shows how pathfinding concepts used in Artificial Intelligence can be implemented in a simple CS student project.
