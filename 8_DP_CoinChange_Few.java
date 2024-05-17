import java.util.Arrays;

class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); 

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
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
