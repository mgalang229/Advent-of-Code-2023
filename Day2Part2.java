import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day2Part2 {

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
				for (int i = 0; i < n; i++) {
					if (Character.isDigit(s[i])) {
						num += s[i];
					} else if (Character.isAlphabetic(s[i])) {
						color += s[i];
					}
					if (s[i] == ',' || s[i] == ';' || i == n - 1) {
						int value = Integer.parseInt(num);
						mp.put(color, Math.max(mp.get(color), value));
						num = color = "";
					}
				}
				int red = mp.get("red"), green = mp.get("green"), blue = mp.get("blue");
				ans += red * green * blue;
			}
			System.out.println(ans);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
