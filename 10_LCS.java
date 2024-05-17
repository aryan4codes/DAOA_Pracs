class LongestCommonSubsequence {
    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int k = s2.length();
        
        int[][] dp = new int[m + 1][k + 1];
        
        // Build the dp array from bottom up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= k; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct LCS from the dp table
        StringBuilder lcsStr = new StringBuilder();
        int i = m, j = k;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsStr.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // The LCS is built backwards, so reverse it at the end
        return lcsStr.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        String lcs = lcs(s1, s2);
        System.out.println("The length of the Longest Common Subsequence is: " + lcs.length());
        System.out.println("The Longest Common Subsequence is: " + lcs);
    }
}
