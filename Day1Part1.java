import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Part1 {
	
	public static void main(String[] args) throws FileNotFoundException {
                
		Scanner fs = new Scanner(new File("src/input.txt"));
		int total = 0;
		while (fs.hasNext()) {
			char[] s = fs.next().toCharArray();
			int n = s.length;
			int num = 0;
			for (int i = 0; i < n; i++) {
				if (Character.isDigit(s[i])) {
					num = s[i] - '0';
					break;
				}
			}
			for (int i = n - 1; i >= 0; i--) {
				if (Character.isDigit(s[i])) {
					num *= 10;
					num += s[i] - '0';
					break;
				}
			}
			total += num;
		}
		System.out.println(total);
		fs.close();
	}
}
