import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3Part1 {
	
	static int[] dx = new int[] {1, 0, -1, 0, 1, 1, -1, -1};
	static int[] dy = new int[] {0, 1, 0, -1, 1, -1, 1, -1};
	static char[][] s;

	public static void main(String[] args) {
		// Create 'input.txt' file and paste the puzzle input
		try {
			Scanner fs = new Scanner(new File("input.txt"));
			ArrayList<String> arr = new ArrayList<>();
			while (fs.hasNextLine()) {
				arr.add(fs.nextLine());
			}
			int n = arr.size();
			s = new char[n][];
			for (int i = 0; i < n; i++) {
				s[i] = arr.get(i).toCharArray();
			}
			int total = 0;
			for (int i = 0; i < n; i++) {
				String temp = "";
				boolean included = false;
				for (int j = 0; j < n; j++) {
					if (Character.isDigit(s[i][j])) {
						temp += s[i][j];
						if (isPartNumber(i, j, n)) {
							included = true;
						}
					}
					if ((temp != "") && (!Character.isDigit(s[i][j]) || j == n - 1)) {
						int num = Integer.parseInt(temp);
						if (included) {
//							System.out.println(num);
							total += num;
							included = false;
						}
						temp = "";
					}
				}
			}
			System.out.println(total);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static boolean isPartNumber(int row, int col, int n) {
		for (int i = 0; i < 8; i++) {
			int newRow = row + dx[i];
			int newCol = col + dy[i];
			if (0 <= newRow && newRow < n && 0 <= newCol && newCol < n) {
				char c = s[newRow][newCol];
				if (!Character.isDigit(c) && c != '.') {
					return true;
				}
			}
		}
		return false;
	}
}
