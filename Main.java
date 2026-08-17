
import java.util.*;

public class Main {

    static int n, m;
    static String[] colors;
    static int[] colorAssigned;
    static boolean[][] graph;
    static boolean isSafe(int state, int color) {
        for (int i = 0; i < n; i++) {
            if (graph[state][i] && colorAssigned[i] == color) {
                return false;
            }
        }
        return true;
    }

    static boolean solve(int state) {

        if (state == n) {
            return true;
        }

        for (int c = 0; c < m; c++) {

            if (isSafe(state, c)) {
                colorAssigned[state] = c;

                if (solve(state + 1)) {
                    return true;
                }

                colorAssigned[state] = -1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of states: ");
        n = sc.nextInt();
        System.out.print("Enter the colors: ");
        sc.nextLine();
        String colorInput = sc.nextLine();

        colors = colorInput.trim().split("\\s+");
        m = colors.length;

        graph = new boolean[n][n];

        System.out.println("Enter the connections(-1 for end):");

        while (true) {
            int u = sc.nextInt();

            if (u == -1) {
                break;
            }

            int v = sc.nextInt();
            u--;
            v--;

            graph[u][v] = true;
            graph[v][u] = true;
        }
        colorAssigned = new int[n];
        Arrays.fill(colorAssigned, -1);
        if (solve(0)) {
            System.out.println("Output:");
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + " - " + colors[colorAssigned[i]]);
            }
        } else {
            System.out.println("No valid coloring possible.");
        }
        sc.close();
    }
}