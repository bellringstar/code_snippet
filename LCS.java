public class LCS {

	private static int solution(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		int mem[][] = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					mem[i][j] = mem[i-1][j-1] + 1;
				} else {
					mem[i][j] = Math.max(mem[i][j-1], mem[i-1][j]);
				}
			}
		}

		return mem[m][n];

	}
}
