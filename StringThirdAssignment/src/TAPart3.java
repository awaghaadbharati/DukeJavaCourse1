import java.security.Principal;

import edu.duke.*;

import edu.duke.StorageResource;

public class TAPart3 {
	public static void main(String[] args) {
		//testGetAllGenes();
		int x = 7;
		int y = x;
		x = 2;
		System.out.println(x + ", " + y);
	}

	public static void testGetAllGenes() {
		///StringThirdAssignment/src/Axl2p.fa
		// brca1line.fa
		///brca1.fa
		///StringThirdAssignment/src/GRch38dnapart.fa
		///StringThirdAssignment/src/mansmall.fa
		//
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		//System.out.println(countGenes(dna));
		StorageResource result = getAllGenes(dna.toUpperCase());
		//processGenes(result);
		int length=Integer.MIN_VALUE;
		 
		for(String gene: result.data())
		{
		if(length<gene.length())
			length=gene.length();
		}
		System.out.println(length);
		 
	}

	public static StorageResource getAllGenes(String dna) {
		StorageResource sr = new StorageResource();
		String result = "";
		int index = 0;
		boolean flag = true;
		while (index < dna.length() && flag) {
			result = findGene(dna, index);
			if (result.length() != 0) {
				String indices[] = findGene(dna, index).split(":");
				int startIndex = Integer.parseInt(indices[0]);
				int endIndex = Integer.parseInt(indices[1]) + 3;
				result = dna.substring(startIndex, endIndex);
				sr.add(result);
				index = endIndex;
			} else
				flag = false;
		}
		return sr;
	}

	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		// System.out.println("FindCOdon");
		int endCodonIndex = startIndex;
		
		while (endCodonIndex != -1) {
			endCodonIndex = dna.indexOf(stopCodon, endCodonIndex + 3);
			if (endCodonIndex != -1) {
				// System.out.println(stopCodon+" : found");
				if ((endCodonIndex - startIndex) % 3 == 0) {
					return endCodonIndex;
				}
			}
		}
		return -1;
	}

	public static String findGene(String dna, int startIndex) {
		String result = "";
		int minIndex = Integer.MAX_VALUE;
		String startCodon = "ATG";
		String endCodons[] = { "TAA", "TAG", "TGA" };
		int endIndices[] = new int[endCodons.length];
		dna = dna.toUpperCase();
		int startCodonIndex = dna.indexOf("ATG", startIndex);
		if (startCodonIndex != -1) {
			// System.out.println("ATG found");
			for (int i = 0; i < endCodons.length; i++) {
				endIndices[i] = findStopCodon(dna, startCodonIndex, endCodons[i]);
				if (endIndices[i] < minIndex && endIndices[i] != -1)
					minIndex = endIndices[i];
			}
		}
		if (minIndex != Integer.MAX_VALUE)
			result = startCodonIndex + ":" + minIndex;
		return result;
	}

	
	public static void processGenes(StorageResource sr) 
	{
		int countLengthNine =0, countCGRatio = 0;
		for (String line : sr.data())
		{
		  if (line.length() > 60) 
		  { 
			  System.out.println(line); 
			  countLengthNine++; 
		  }
	  
		  if (cgRatio(line) > 0.35) 
		  {
			  System.out.println(line);
			  countCGRatio++;
		  }
		}
		   System.out.println(countLengthNine);
		   System.out.println(countCGRatio); //
		  //System.out.println(getMaximumL); }
	}
	public static void printAllGenes(String dna) {
		int index = 0;
		String result = "";
		while (index < dna.length()) {
			result = findGene(dna, index);
			if (result.length() != 0) {
				String indices[] = result.split(":");
				int startIndex = Integer.parseInt(indices[0]);
				int endIndex = Integer.parseInt(indices[1]) + 3;
				result = dna.substring(startIndex, endIndex);
				index = endIndex;
				System.out.println(result);
			} else {
				break;
			}

		}

	}
	public static int countGenes(String dna) {
		int index = 0, count = 0;
		String result = "";
		while (index < dna.length()) {
			result = findGene(dna,index);
			if (result.length() != 0) {
				count++;
				String indices[]=result.split(":");
				index = Integer.parseInt(indices[1]);
			}
			else
				break;
		}
		return count;

	}
	
	public static float cgRatio(String dna) {
		int countCG = 0;
		for (char c : dna.toCharArray()) {
			if (c == 'C' || c == 'G')
				countCG++;
		}
		return (float) countCG / dna.length();
	}
}
