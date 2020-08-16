import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.*;
//passed
public class Part1 {
	public static void main(String[] args) {
		tester();
	}

	public static void tester() {
		FileResource fr = new FileResource("cs.csv");
		CSVParser parser = fr.getCSVParser();
		List<CSVRecord> records=new ArrayList<CSVRecord>();
		try {
			records = parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double data[]=new double[records.size()];
		//ArrayList<Double> data=new ArrayList<Double>();
			for(int i=0;i<records.size();i++)
			{
				System.out.println(records.get(i));
				data[i]= Double.parseDouble(records.get(i).get(4));
			}
			System.out.print("{ ");
			for(double d:data)
			{
				System.out.print(d+", ");
			}
			System.out.print("}");
		//bigExporters(parser, "$999,999,999,999");
		//System.out.println(numberOfExporters(parser, "cocoa"));
		//System.out.println(countryInfo(parser, "Nauru"));
		//listExportersTwoProducts(parser, "cotton", "flowers");
	}

	public static String countryInfo(CSVParser parser, String country) {

		try {
			for (CSVRecord record : parser.getRecords()) {
				if (record.get(0).equals(country)) {
					return record.get(0) + ": " + record.get(1) + ": " + record.get(2);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "NOT FOUND";
	}

	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		try {
			for (CSVRecord record : parser.getRecords()) {
				String recordString = record.get(1);
				if (recordString.contains(exportItem1) && recordString.contains(exportItem2)) {
					System.out.println(record.get(0));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int numberOfExporters(CSVParser parser, String exportItem) {
		int numberOfCountries = 0;
		try {
			for (CSVRecord record : parser.getRecords()) {
				if (record.get(1).contains(exportItem)) {
					numberOfCountries++;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfCountries;
	}

	public static void bigExporters(CSVParser parser, String amount) {
		try {
			for (CSVRecord record : parser.getRecords()) {
				if (record.get(2).length() > amount.length())
					System.out.println(record.get(0) + " : " + record.get(2));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}