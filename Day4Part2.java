import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day4Part2 {

	public static void main(String[] args) {
		// Create 'input.txt' file and paste the puzzle input
		try {
			Scanner fs = new Scanner(new File("input.txt"));
			int card = 1;
			HashMap<Integer, Integer> mp = new HashMap<>();
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
				int n = s.length, counter = 0;
				String temp = "";
				boolean ownList = false;
				HashSet<Integer> hs = new HashSet<>();
				for (int i = 0; i < n; i++) {
					if (Character.isDigit(s[i])) {
						temp += s[i];
					} else if (s[i] == '|') {
						ownList = true;
					}
					if ((s[i] == ' ' && temp != "") || (i == n - 1)) {
						int num = Integer.parseInt(temp);
						if (!ownList) {
							hs.add(num);
						} else {
							if (hs.contains(num)) {
								counter++;
							}
						}
						temp = "";
					}
				}
				int copies = mp.getOrDefault(card, 0) + 1;
				mp.put(card, copies);
				for (int i = 1; i <= counter; i++) {
					int nextCard = card + i;
					mp.put(nextCard, mp.getOrDefault(nextCard, 0) + copies);
				}
				card++;
			}
			int total = 0;
			for (int cardNum : mp.keySet()) {
				total += mp.get(cardNum);
			}
			System.out.println(total);
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
