 import java.util.Arrays;
 
 class TravelingSalesman {
    private int[][] distances;
    private int n;
    private int[][] dp;
    private int VISITED_ALL;

    public TravelingSalesman(int[][] distances) {
        this.distances = distances;
        this.n = distances.length;
        this.VISITED_ALL = (1 << n) - 1;
        this.dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    public int tsp(int mask, int pos) {
        // Base case: all cities visited
        if (mask == VISITED_ALL) {
            return distances[pos][0];
        }
        // If this state has been solved before
        if (dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        
        int minCost = Integer.MAX_VALUE;
        // Try to visit an unvisited city
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newCost = distances[pos][city] + tsp(mask | (1 << city), city);
                minCost = Math.min(minCost, newCost);
            }
        }
        
        return dp[pos][mask] = minCost;
    }

    public int findMinCost() {
        return tsp(1, 0); // Start the journey from the first city
    }

    public static void main(String[] args) {
        int[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        TravelingSalesman tsp = new TravelingSalesman(distances);
        System.out.println("The minimum cost is: " + tsp.findMinCost());
    }
}
