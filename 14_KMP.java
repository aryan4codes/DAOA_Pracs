class KMPAlgorithm {

    // Function to create the longest prefix suffix (lps) array
    private static void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;  // Length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // Loop to fill lps array
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // (pat[i] != pat[len])
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // Function to implement KMP algorithm
    public static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // Create lps[] that will hold the longest prefix suffix values for the pattern
        int[] lps = new int[M];
        int j = 0;  // Index for pat[]

        // Preprocess the pattern (calculate lps array)
        computeLPSArray(pat, M, lps);

        int i = 0;  // Index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        KMPSearch(pat, txt);
    }
}
