
public class SAPart2 {
	public static void main(String[] args) {
		// System.out.println("Hello");
		System.out.println(howMany("AA", "ATAAAA"));
	}
	public static int howMany(String a, String b) {
		int aLen = a.length();
		int bLen = b.length();
		int startIndex = b.indexOf(a), count = 0;
		while ((startIndex < (bLen - aLen)) && startIndex != -1) {
			count++;
			startIndex = b.indexOf(a, startIndex + 1);
		}
		return count;
	}
}
