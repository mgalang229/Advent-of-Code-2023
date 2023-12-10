import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day3Part2 {
	
	static int[] dx = new int[] {1, 0, -1, 0, 1, 1, -1, -1};
	static int[] dy = new int[] {0, 1, 0, -1, 1, -1, 1, -1};
	static char[][] s;
	
	static HashSet<Pair> hs;

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
			HashMap<Pair, ArrayList<Integer>> mp = new HashMap<>();
			hs = new HashSet<>();
			for (int i = 0; i < n; i++) {
				String temp = "";
				for (int j = 0; j < n; j++) {
					if (Character.isDigit(s[i][j])) {
						temp += s[i][j];
						collectGearIndices(i, j, n);
					}
					if ((temp != "") && (!Character.isDigit(s[i][j]) || j == n - 1)) {
						int num = Integer.parseInt(temp);
						for (Pair p : hs) {
							if (mp.get(p) == null) {
								mp.put(p, new ArrayList<>());
							}
							mp.get(p).add(num);
						}
						temp = "";
						hs.clear();
					}
				}
			}
			int total = 0;
			for (Pair p : mp.keySet()) {
				if (mp.get(p).size() != 2) {
					continue;
				}
				total += mp.get(p).get(0) * mp.get(p).get(1);
			}
			System.out.println(total);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static class Pair {
		int first, second;
		
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
		
		@Override
		public int hashCode() {
			final int p = 31;
			return p + first + second;
		}
		
		@Override
		public boolean equals(Object other) {
			Pair o = (Pair) other;
			if (this.first != o.first || this.second != o.second) {
				return false;
			}
			return true;
		}
		
		@Override
		public String toString() {
			return this.first + "," + this.second;
		}
	}
	
	static void collectGearIndices(int row, int col, int n) {
		for (int i = 0; i < 8; i++) {
			int newRow = row + dx[i];
			int newCol = col + dy[i];
			if (withinBounds(newRow, newCol, n)) {
				if (s[newRow][newCol] == '*') {
					hs.add(new Pair(newRow, newCol));
				}
			}
		}
	}
	
	static boolean withinBounds(int newRow, int newCol, int n) {
		return (0 <= newRow && newRow < n && 0 <= newCol && newCol < n);
	}
}
