import java.util.*;

public class LocalSearch {

static class State {
int[] board;
int conflicts;

State(int[] board) {
this.board = board.clone();
this.conflicts = calculateConflicts();
}

int calculateConflicts() {
int count = 0;
int n = board.length;

for (int i = 0; i < n; i++) {
for (int j = i + 1; j < n; j++) {
if (board[i] == board[j] ||
Math.abs(board[i] - board[j]) == Math.abs(i - j))
count++;
}
}
return count;
}

void printState() {
for (int x : board)
System.out.print(x + " ");
System.out.println();
}
}

static List<State> generateSuccessors(State s) {
List<State> successors = new ArrayList<>();
int n = s.board.length;

for (int col = 0; col < n; col++) {
for (int row = 1; row <= n; row++) {

if (row != s.board[col]) {
int[] newBoard = s.board.clone();
newBoard[col] = row;
successors.add(new State(newBoard));
}
}
}
return successors;
}

public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.print("Enter the matrix size: ");
int n = sc.nextInt();

System.out.print("Enter the beam size: ");
int k = sc.nextInt();

List<State> beam = new ArrayList<>();

for (int i = 1; i <= k; i++) {
System.out.println("Enter the initial state " + i + ":");
int[] board = new int[n];

for (int j = 0; j < n; j++)
board[j] = sc.nextInt();

beam.add(new State(board));
}

int level = 1;

while (true) {

Collections.sort(beam, Comparator.comparingInt(a -> a.conflicts));

if (beam.get(0).conflicts == 0) {
System.out.println("\nSolution Found:");
beam.get(0).printState();
break;
}

List<State> allSuccessors = new ArrayList<>();

for (State s : beam)
allSuccessors.addAll(generateSuccessors(s));

Collections.sort(allSuccessors, Comparator.comparingInt(a -> a.conflicts));

beam.clear();

for (int i = 0; i < Math.min(k, allSuccessors.size()); i++)
beam.add(allSuccessors.get(i));

System.out.println("\nLevel " + level + " Successors:");

for (State s : beam) {
System.out.print("(Conflicts: " + s.conflicts + ") ");
s.printState();
}

level++;

if (level > 20) {
System.out.println("\nSolution not found.");
break;
}
}

sc.close();
}
}
