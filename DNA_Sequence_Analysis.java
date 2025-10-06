import java.util.*;

public class DNA_Sequence_Analysis {

    // ---------- TASK 1: Longest Common Subsequence (LCS) ----------
    public static void findLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        int[][] dp = new int[m + 1][n + 1];
        char[][] direction = new char[m + 1][n + 1]; // ↖, ↑, ←

        // Build cost and direction matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    direction[i][j] = 'D'; // Diagonal ↖
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    direction[i][j] = 'U'; // Up ↑
                } else {
                    dp[i][j] = dp[i][j - 1];
                    direction[i][j] = 'L'; // Left ←
                }
            }
        }

        System.out.println("==================================================");
        System.out.println(" TASK 1: LONGEST COMMON SUBSEQUENCE (LCS)");
        System.out.println("==================================================");
        System.out.println("DNA Sequence X: " + X);
        System.out.println("DNA Sequence Y: " + Y);

        // Print cost matrix
        System.out.println("\nCOST MATRIX (LCS):");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3d ", dp[i][j]);
            }
            System.out.println();
        }

        // Print direction matrix with arrows
        System.out.println("\nDIRECTION MATRIX (LCS):");
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                switch (direction[i][j]) {
                    case 'D':
                        System.out.print("↖ ");
                        break;
                    case 'U':
                        System.out.print("↑ ");
                        break;
                    case 'L':
                        System.out.print("← ");
                        break;
                    default:
                        System.out.print("- ");
                        break;
                }
            }
            System.out.println();
        }

        // Reconstruct LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (direction[i][j] == 'D') {
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (direction[i][j] == 'U') {
                i--;
            } else {
                j--;
            }
        }
        lcs.reverse();

        System.out.println("\nFINAL COST (Length of LCS): " + dp[m][n]);
        System.out.println("LCS: " + lcs.toString());
    }

    // ---------- TASK 2: Longest Repeating Subsequence (LRS) ----------
    public static void findLRS(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        char[][] direction = new char[n + 1][n + 1]; // ↖, ↑, ←

        // Apply LCS logic on same string (i != j condition)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    direction[i][j] = 'D'; // ↖
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    direction[i][j] = 'U'; // ↑
                } else {
                    dp[i][j] = dp[i][j - 1];
                    direction[i][j] = 'L'; // ←
                }
            }
        }

        System.out.println("\n==================================================");
        System.out.println(" TASK 2: LONGEST REPEATING SUBSEQUENCE (LRS)");
        System.out.println("==================================================");
        System.out.println("Input DNA Sequence: " + S);

        // Print cost matrix
        System.out.println("\nCOST MATRIX (LRS):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3d ", dp[i][j]);
            }
            System.out.println();
        }

        // Print direction matrix with arrows
        System.out.println("\nDIRECTION MATRIX (LRS):");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                switch (direction[i][j]) {
                    case 'D':
                        System.out.print("↖ ");
                        break;
                    case 'U':
                        System.out.print("↑ ");
                        break;
                    case 'L':
                        System.out.print("← ");
                        break;
                    default:
                        System.out.print("- ");
                        break;
                }
            }
            System.out.println();
        }

        // Reconstruct LRS
        StringBuilder lrs = new StringBuilder();
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (direction[i][j] == 'D') {
                lrs.append(S.charAt(i - 1));
                i--;
                j--;
            } else if (direction[i][j] == 'U') {
                i--;
            } else {
                j--;
            }
        }
        lrs.reverse();

        System.out.println("\nFINAL COST (Length of LRS): " + dp[n][n]);
        System.out.println("LRS: " + lrs.toString());
    }

    // ---------- MAIN FUNCTION ----------
    public static void main(String[] args) {
        String X = "AGCCCTAAGGGCTACCTAGCTT";
        String Y = "GACAGCCTACAAGCGTTAGCTTG";
        String S = "AABCBDC"; // Example for LRS

        findLCS(X, Y);
        findLRS(S);
    }
}
