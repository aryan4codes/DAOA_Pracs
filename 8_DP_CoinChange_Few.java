import java.util.Arrays;

class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        // Create an array to store the minimum number of coins needed for each amount
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // Fill the array with a large value initially
        
        // Base case: 0 coins needed to make 0 amount
        dp[0] = 0;
        
        // Iterate over each amount from 1 to amount
        for (int i = 1; i <= amount; i++) {
            // Iterate over each coin denomination
            for (int coin : coins) {
                // Check if the current coin denomination can be used to make the current amount
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    // Update dp[i] with the minimum of its current value and dp[i - coin] + 1
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // If dp[amount] is still Integer.MAX_VALUE, it means it's not possible to make the amount
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 10};
        int amount = 11;
        int result = coinChange(coins, amount);
        if (result != -1) {
            System.out.println("Minimum number of coins needed: " + result);
        } else {
            System.out.println("It's not possible to make the amount with the given coins.");
        }
    }
}
