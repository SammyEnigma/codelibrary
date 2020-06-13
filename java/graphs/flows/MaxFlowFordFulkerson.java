package graphs.flows;

// https://en.wikipedia.org/wiki/Ford–Fulkerson_algorithm in O(V^2 * flow)
public class MaxFlowFordFulkerson {
    public static int maxFlow(int[][] cap, int s, int t) {
        for (int flow = 0;;) {
            int df = findPath(cap, new boolean[cap.length], s, t, Integer.MAX_VALUE);
            if (df == 0)
                return flow;
            flow += df;
        }
    }

    static int findPath(int[][] cap, boolean[] vis, int u, int t, int f) {
        if (u == t)
            return f;
        vis[u] = true;
        for (int v = 0; v < vis.length; v++)
            if (!vis[v] && cap[u][v] > 0) {
                int df = findPath(cap, vis, v, t, Math.min(f, cap[u][v]));
                if (df > 0) {
                    cap[u][v] -= df;
                    cap[v][u] += df;
                    return df;
                }
            }
        return 0;
    }

    // Usage example
    public static void main(String[] args) {
        int[][] capacity = {{0, 3, 2}, {0, 0, 2}, {0, 0, 0}};
        System.out.println(4 == maxFlow(capacity, 0, 2));
    }
}
