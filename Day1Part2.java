import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Part2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		// Create 'input.txt' file first and paste the input
		Scanner fs = new Scanner(new File("src/input.txt"));
		int total = 0;
		while (fs.hasNext()) {
			char[] s = fs.next().toCharArray();
			int n = s.length;
			int num = 0;
			String temp = "";
			for (int i = 0; i < n; i++) {
				if (Character.isDigit(s[i])) {
					num = s[i] - '0';
					break;
				} else {
					temp += s[i];
					int digit = getDigit(temp);
					if (digit > 0) {
						num = digit;
//						System.out.print((i + 1) + "->");
						break;
					}
				}
			}
			assert(num > 0);
//			System.out.println(num);
			temp = "";
			for (int i = n - 1; i >= 0; i--) {
				if (Character.isDigit(s[i])) {
					num *= 10;
					num += s[i] - '0';
					break;
				} else {
					temp += s[i];
					String rev = "";
					for (int j = temp.length() - 1; j >= 0; j--) {
						rev += temp.charAt(j);
					}
					int digit = getDigit(rev);
					if (digit > 0) {
						num *= 10;
						num += digit;
						break;
					}
				}
			}
			total += num;
//			System.out.println(num);
		}
		System.out.println(total);
		fs.close();
	}
	
	static int getDigit(String temp) {
		String[] digits = new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		for (int i = 0; i < digits.length; i++) {
			if (temp.contains(digits[i])) {
				return i + 1;
			}
		}
		return 0;
	}
}
