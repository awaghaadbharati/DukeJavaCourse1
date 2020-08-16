
public class FAPart3 {
	public static void main(String[] args) {
		testing();
		}
	public static void testing()
	{
		System.out.println(twoOccurrences("a","banana"));
		System.out.println(twoOccurrences("atg","ctgtatgta"));
		System.out.println(lastPart	("by","A story by Abby Long"));
		System.out.println(lastPart("an", "banana"));;
		System.out.println(lastPart("zoo", "forest"));;
	
	}
	public static boolean twoOccurrences(String a, String b) {
		int firstIndex=b.indexOf(a);
		if(firstIndex!=-1)
		{
			int secondIndex=b.indexOf(a, firstIndex+1);
			if(secondIndex!=-1)
				return true;
		}
		return false;
	}
	public static String lastPart(String a, String b)
	{
		int firstIndex=b.indexOf(a);
		if(firstIndex!=-1)
			return b.substring(firstIndex+a.length(), b.length());
		return b;
	}
}
