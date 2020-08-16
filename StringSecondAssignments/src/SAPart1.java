import java.awt.PrintGraphics;
import java.io.ObjectInputStream.GetField;

public class SAPart1 {
	public static void main(String[] args) {
		/*
		 * String dnas[]=new String[5];
		 * dnas[0]="acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatg";
		 * dnas[1]="aggaatgttcccaatagtagacata"; dnas[2]="gagaggccaacattttttgaaatttt";
		 * dnas[3]="atgttcccaatagtagactaaaagtctt";
		 * dnas[4]="atgttcccaatagtagafcctaaaagtctt"; for(String dna:dnas)
		 * System.out.println("Gene : "+findGene(dna.toUpperCase()));
		 */
		//System.out.println(findGene("AATGCTAACTAGCTGACTAAT"));;
		printAllGenes("ATGTAAGATGCCCTAGT");
	}
	public static String findGene(String dna)
	{
		String result="";
		int minIndex=Integer.MAX_VALUE;
		String startCodon="ATG";
		String endCodons[]= {"TAA","TAG","TGA"};
		int endIndices[]=new int[endCodons.length];
		int startCodonIndex=dna.indexOf("ATG");
		if(startCodonIndex!=-1)
		{
			for(int i=0;i<endCodons.length;i++)
			{
				endIndices[i]=findStopCodon(dna , startCodonIndex, endCodons[i]);
				if(endIndices[i]<minIndex && endIndices[i]!=-1)
					minIndex=endIndices[i];
			}
		}
		if(minIndex!=Integer.MAX_VALUE)
			result=dna.substring(startCodonIndex, minIndex+3);
		return result;
	}
	public static int findStopCodon(String dna, int startIndex, String stopCodon) 
	{
		int endCodonIndex=dna.indexOf(stopCodon, startIndex+3);
		if(endCodonIndex!=-1)
		{
			if((endCodonIndex-startIndex)%3==0)
			{
				return endCodonIndex;
			}
		}
		return -1;
	}
	public static void testFindStopCodon()
	{
		findStopCodon("ATGTAAGATGCCCTAGT",0,"TAA");
	}
	public static void printAllGenes(String dna)
	{
		int index=0;
		String result="";
		while(index<dna.length())
		{
			result=findGene(dna.substring(index));
			if(result.length()!=0)
			{
				System.out.println(result);
				index=dna.indexOf(result)+result.length();
			}
			
		}
		
	}
}
