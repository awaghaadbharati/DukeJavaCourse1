import edu.duke.FileResource;

public class TAPart2 {
	public static void main(String[] args) {
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		System.out.println(countCTG(dna.toUpperCase()));
	}
	public static float cgRatio(String dna) {
		int countCG=0;
		for(char c:dna.toCharArray())
		{
			if(c=='C' || c=='G')
				countCG++;
		}
		return (float)countCG/dna.length();
	}
public static int countCTG(String dna) {
		int index=dna.indexOf("CTG");
		int countCTG=0;
		while(index!=-1 && index<dna.length()-3)
		{
			countCTG++;
			index=dna.indexOf("CTG", index+3);
		}
		return countCTG;
	}
}
