
public class SAPart3 {
	public static void main(String[] args) {
		System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
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
				endIndices[i]=findStopCodon(dna, startCodonIndex, endCodons[i]);
				if(endIndices[i]<minIndex && endIndices[i]!=-1)
					minIndex=endIndices[i];
			}
		}
		if(minIndex!=Integer.MAX_VALUE)
			result=dna.substring(startCodonIndex, minIndex+2);
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
		
	}
	public static int countGenes(String dna)
	{
		int index=0,count=0;
		String result="";
		while(index<dna.length())
		{
			result=findGene(dna.substring(index));
			if(result.length()!=0)
			{
				count++;
				index=dna.indexOf(result)+result.length();
			}
			
		}		return count;
		
	}
	}
