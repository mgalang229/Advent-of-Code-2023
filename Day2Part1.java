import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day2Part1 {

	public static void main(String[] args) {
		// Create 'input.txt' file and paste the puzzle input
		try {
			Scanner fs = new Scanner(new File("input.txt"));
			int game = 1, ans = 0;
			while (fs.hasNextLine()) {
				String line = fs.nextLine();
				int start = 0;
				for (int i = 1; i < line.length(); i++) {
					if (line.charAt(i) == ' ' && line.charAt(i-1) == ':') {
						start = i + 1;
						break;
					}
				}
				char[] s = line.substring(start).toCharArray();
				int n = s.length;
				HashMap<String, Integer> mp = new HashMap<>();
				mp.put("red", 0);
				mp.put("blue", 0);
				mp.put("green", 0);
				String num = "", color = "";
				boolean valid = true;
				for (int i = 0; i < n; i++) {
					if (Character.isDigit(s[i])) {
						num += s[i];
					} else if (Character.isAlphabetic(s[i])) {
						color += s[i];
					}
					if (s[i] == ',' || s[i] == ';' || i == n - 1) {
						int value = Integer.parseInt(num);
						mp.put(color, value);
						num = color = "";
						if (s[i] == ';' || i == n - 1) {
							if (mp.get("red") > 12 || mp.get("green") > 13 || mp.get("blue") > 14) {
								valid = false;
								break;
							}
							mp.put("red", 0);
							mp.put("blue", 0);
							mp.put("green", 0);
						}
					}
				}
				if (valid) {
					ans += game;
				}
				game++;
			}
			System.out.println(ans);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
