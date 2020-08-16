import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Part1 {
	public static void main(String[] args) {
		
		//System.out.println(totalBirths());
		//System.out.println(numberOfGenderNames(1900,  "F"));//2225
		//System.out.println(numberOfGenderNames(1905,  "M"));//1421
		//System.out.println(getRank(1960, "Emily", "F"));//251
		//System.out.println(getRank(1971, "Frank", "M"));//54
		//System.out.println(getName(1980, 350, "F"));//Mia
		//System.out.println(getName(1982, 450, "M"));//Forrest
		//whatIsNameInYear("Susan", 1972, 2014, "F");//Addison
		//whatIsNameInYear("Owen", 1974, 2014, "M");//Leonel//erroe
		//System.out.println(yearOfHighestRank("Genevieve", "F"));//1914
		//System.out.println(yearOfHighestRank("Mich", "M"));//1960
		System.out.println(getAverageRank("Susan", "F"));//chuktay//173.0
		//System.out.println(getAverageRank("Robert", "M"));//error//10.0
		//System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));//323200
		//System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));//1498074
	}

	public static int totalBirths() {
		//FileResource fr = new FileResource("\\us_babynames_small\\testing\\yob2013short.csv");
		//FileResource fr = new FileResource("\\us_babynames_by_decade\\testing\\yob2013s.csv");
		FileResource fr = new FileResource("\\us_babynames_by_year\\yob1905.csv");
		CSVParser parser = fr.getCSVParser(false);
		int totalBirths = 0;
		try {
			List<CSVRecord> records = parser.getRecords();
			for (CSVRecord record : records) {
				System.out.println(record.toString());
				if(record.get(1).equals("M"))	
					totalBirths += Integer.parseInt(record.get(2));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// add the values in third column to give total sum

		return totalBirths;
	}
	public static int numberOfGenderNames(int year, String gender)
	{
		FileResource fr = new FileResource("\\us_babynames_by_year\\yob"+year+".csv");
		CSVParser parser = fr.getCSVParser(false);
		int totalNumberOfGenders = 0;
		try {
			List<CSVRecord> records = parser.getRecords();
			for (CSVRecord record : records) {
				System.out.println(record.toString());
				if(record.get(1).equals(gender))	
					totalNumberOfGenders++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// add the values in third column to give total sum

		return totalNumberOfGenders;
	}
	public static int getRank(int year, String name, String gender) {
		//FileResource fr = new FileResource("\\testing\\yob" + year + "short.csv");
		FileResource fr = new FileResource("\\us_babynames_by_year\\yob" + year + ".csv");
		//FileResource fr = new FileResource("\\us_babynames_by_decade\\yob" + year + "s.csv");
		//FileResource fr = new FileResource("\\us_babynames_by_test\\yob" + year + "short.csv");
		CSVParser parser = fr.getCSVParser(false);
		List<CSVRecord> records = new ArrayList<CSVRecord>();
		int rank = 1, notPresent=-1;
		boolean isPresent=false;
		try {
			records = parser.getRecords();
			for (CSVRecord record : records) {
				//System.out.println(record.toString());
				if (record.get(0).equals(name) && record.get(1).equals(gender))
				{
					isPresent=true;
					break;
				}
				if (record.get(1).equals(gender))
					++rank;
			}
			if(isPresent)
				return rank;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notPresent;
	}

	public static String getName(int year, int rank, String gender) {
		//FileResource yearFR = new FileResource("\\testing\\yob" + year + "short.csv");
		FileResource yearFR = new FileResource("\\us_babynames_by_year\\yob" + year + ".csv");
		CSVParser parser=yearFR.getCSVParser(false);
		List<CSVRecord> records = new ArrayList<CSVRecord>();
		try {
			records = parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int currentRank=1;
		for(CSVRecord record: records)
		{
			if(rank==currentRank && record.get(1).equals(gender))
				return record.get(0);
			if(record.get(1).equals(gender))
				currentRank++;
		}
		return "NO NAME";
	}

	public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
		String result = "";
		//FileResource yearFR = new FileResource("\\us_babynames_small\\testing\\yob" + year + "short.csv");
		//FileResource newYearFR = new FileResource("\\us_babynames_small\\testing\\yob" + newYear + "short.csv");
		FileResource yearFR = new FileResource("\\us_babynames_by_year\\yob" + year + ".csv");
		FileResource newYearFR = new FileResource("\\us_babynames_by_year\\yob" + newYear + ".csv");
		CSVParser yearParser=yearFR.getCSVParser(false);
		CSVParser newYearParser=newYearFR.getCSVParser(false);
		int rank=getRank(year, name, gender);
		System.out.println(rank);
		result=getName(newYear, rank, gender);
		System.out.println(name + " born in " + year + " would be " + result + " if she was born in " + newYear);
	}
	public static int yearOfHighestRank(String name, String gender)
	{
		DirectoryResource dr=new DirectoryResource();
		int highestRank=Integer.MAX_VALUE;
		int yearOfHighestRank=-1;
		for(File f:dr.selectedFiles())
		{
			FileResource fr=new FileResource(f);
			CSVParser parser=fr.getCSVParser(false);
			int year= Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
			int rank=getRank(year, name, gender);
			if(highestRank>rank && rank!=-1)
			{
				highestRank=rank;
				yearOfHighestRank=year;
			}
		}
		
		return yearOfHighestRank;
	}
	public static double getAverageRank(String name, String gender)
	{
		DirectoryResource dr=new DirectoryResource();
		int sumOfRanks=0, numberOfFiles=0;
		Iterable<File> selectedFiles=dr.selectedFiles();
		for(File f: selectedFiles)
		{
			int year= Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));
			int rank=getRank(year, name, gender);
			System.out.println(rank);
				sumOfRanks+=rank;
			numberOfFiles++;
			System.out.println(sumOfRanks+" : "+ numberOfFiles);
		}
		return sumOfRanks/numberOfFiles;
	}
	
	public static int getTotalBirthsRankedHigher(int year, String name, String gender) 
	{
		int totalBirthsRankedHigher=0;
			//FileResource fr=new FileResource("\\testing\\yob" + year + "short.csv");
			FileResource fr = new FileResource("\\us_babynames_by_year\\yob" + year + ".csv");
			CSVParser parser=fr.getCSVParser(false);
			List<CSVRecord> records=new ArrayList<CSVRecord>();
			try {
				records = parser.getRecords();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(CSVRecord record: records)
			{
				String currentGender=record.get(1);
				String name1=record.get(0);
				if(name1.equals(name) && currentGender.equals(gender))
					return totalBirthsRankedHigher;
				if(currentGender.equals(gender))
				{
					System.out.println(name1);
					totalBirthsRankedHigher+=Integer.parseInt(record.get(2));
				}
			}
		
		return totalBirthsRankedHigher;
	}
	 

}