
public class FAPart2 {
	public static void main(String[] args) {
		testSimpleGene();
	}
	public static void testSimpleGene()
	{
		String dnas[]=new String[5];
		dnas[0]="acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatg";
		dnas[1]="aggaatgttcccaatagtagacata";
		dnas[2]="gagaggccaacattttttgaaatttt";
		dnas[3]="atgttcccaatagtagactaaaagtctt";
		dnas[4]="atgttcccaatagtagafcctaaaagtctt";
		for(String dna: dnas)
		{
			System.out.println("DNA : "+dna);
			System.out.println("Gene : "+findSimpleGene(dna,"atg","taa"));
		}
	}
	public static String findSimpleGene(String dna, String startCodon,String endCodon) {
		String result="";
		int startCodonIndex=dna.indexOf(startCodon);
		if(startCodonIndex==-1)
			return result;
		else
		{
			int endCodonIndex=dna.indexOf(endCodon, startCodonIndex+3);
			if(endCodonIndex==-1)
				return result;
			else
			{
				if((endCodonIndex-startCodonIndex)%3==0)
				{
					result=dna.substring(startCodonIndex, endCodonIndex+3);
				}
				else
					result=dna.substring(startCodonIndex+3, endCodonIndex);
			}
		}
		return result;
	}

}
