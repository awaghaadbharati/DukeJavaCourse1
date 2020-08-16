import edu.duke.*;
public class TAPart1 {
	public static void main(String[] args) {
		
		testGetAllGenes();
	}
	public static void testGetAllGenes()
	{
		System.out.println("testGetAllgenes");
	}
	public static StorageResource getAllGenes(String dna)
	{
		StorageResource sr=new StorageResource();
		String result="";
		int index=0;
		while(index<dna.length()) 
		{
			result=findGene(dna.substring(index));
			int geneLength=result.length();
			if(geneLength!=0)
			{
				sr.add(result);
				index=dna.indexOf(result)+geneLength;
			}
		}
		 return sr;
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
	}

}
