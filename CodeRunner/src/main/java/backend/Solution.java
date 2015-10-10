package backend;
import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;import java.util.*;import java.lang.*;public class Solution {	public boolean isPalindrome(int x) {

		if (x < 0) {
			return false;
		}
		int div = 1;
		while (x / div >= 10) {
			div = div * 10;
		}
		while (div >= 10) {
			if (x / div != x % 10) {
				return false;
			}
			x = (x % div) / 10;
			div = div / 100;
		}
		return true;
	}

	public static void main(String[] args) {

		Solution s = new Solution();

		System.out.println(s.isPalindrome(1111));

	}
}
