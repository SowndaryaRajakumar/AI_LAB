import java.util.*;

public class FourQueens {

static int N = 4;

static class State {
ArrayList<Integer> board;
int g;
int h;

State(ArrayList<Integer> b, int cost) {
board = new ArrayList<>(b);
g = cost;
h = conflicts();
}

int conflicts() {
int cnt = 0;
for (int i = 0; i < board.size(); i++) {
for (int j = i + 1; j < board.size(); j++) {
if (board.get(i).equals(board.get(j)) ||
Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
cnt++;
}
}
}
return cnt;
}

boolean isGoal() {
return board.size() == N && h == 0;
}
}

static void printBoard(ArrayList<Integer> board) {
for (int i = 0; i < N; i++) {
for (int j = 0; j < N; j++) {
if (board.get(i) == j)
System.out.print("Q ");
else
System.out.print(". ");
}
System.out.println();
}
}

static void bfs() {
System.out.println("\nBFS Solution:");

Queue<State> queue = new LinkedList<>();
queue.add(new State(new ArrayList<>(), 0));

int nodes = 0;

while (!queue.isEmpty()) {
State cur = queue.poll();
nodes++;

if (cur.isGoal()) {
printBoard(cur.board);
System.out.println("Expanded Nodes: " + nodes);
return;
}

if (cur.board.size() >= N)
continue;

for (int col = 0; col < N; col++) {
ArrayList<Integer> next = new ArrayList<>(cur.board);
next.add(col);
queue.add(new State(next, cur.g + 1));
}
}

System.out.println("No Solution");
}

static void dfs() {
System.out.println("\nDFS Solution:");

Stack<State> stack = new Stack<>();
stack.push(new State(new ArrayList<>(), 0));

int nodes = 0;

while (!stack.isEmpty()) {
State cur = stack.pop();
nodes++;

if (cur.isGoal()) {
printBoard(cur.board);
System.out.println("Expanded Nodes: " + nodes);
return;
}

if (cur.board.size() >= N)
continue;

for (int col = N - 1; col >= 0; col--) {
ArrayList<Integer> next = new ArrayList<>(cur.board);
next.add(col);
stack.push(new State(next, cur.g + 1));
}
}

System.out.println("No Solution");
}

static void aStar() {
System.out.println("\nA* Solution:");

PriorityQueue<State> pq = new PriorityQueue<>(
(a, b) -> (a.g + a.h) - (b.g + b.h)
);

pq.add(new State(new ArrayList<>(), 0));

int nodes = 0;

while (!pq.isEmpty()) {
State cur = pq.poll();
nodes++;

if (cur.isGoal()) {
printBoard(cur.board);
System.out.println("Expanded Nodes: " + nodes);
return;
}
if (cur.board.size() >= N)
continue;

for (int col = 0; col < N; col++) {
ArrayList<Integer> next = new ArrayList<>(cur.board);
next.add(col);
pq.add(new State(next, cur.g + 1));
}
}

System.out.println("No Solution");
}

public static void main(String[] args) {

bfs();
dfs();
aStar();

System.out.println("\nPerformance Comparison:");

System.out.println("\nBFS");
System.out.println("Optimal : Yes");
System.out.println("Time : O(b^d)");
System.out.println("Space : O(b^d)");

System.out.println("\nDFS");
System.out.println("Optimal : No");
System.out.println("Time : O(b^m)");
System.out.println("Space : O(bm)");

System.out.println("\nA*");
System.out.println("Optimal : Yes (with admissible heuristic)");
System.out.println("Time : Depends on heuristic");
System.out.println("Space : O(b^d)");
}
}
