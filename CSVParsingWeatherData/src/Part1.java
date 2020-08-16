import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.*;

public class Part1 {
	public static void main(String[] args) {
		 //testColdestHourInFile();
		testFileWithColdestTemperature();
		//testLowestHumidityInFile();
		//testLowestHumidityInManyFiles();
		 //testAverageTemperatureInFile();
		//testAverageTemperatureWithHighHumidityInFile();
		// TimeEDT-0,TemperatureF-1,Dew PointF,Humidity-3,Sea Level
		// PressureIn,VisibilityMPH,Wind Direction,Wind SpeedMPH,Gust
		// SpeedMPH,PrecipitationIn,Events,Conditions,WindDirDegrees,DateUTC
	}

	public static void testAverageTemperatureInFile() {
		FileResource fr = new FileResource("\\2013\\weather-2013-08-10.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println(averageTemperatureInFile(parser));
	}

	public static void testColdestHourInFile() {
		FileResource fr = new FileResource("\\2014\\weather-2014-05-01.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println(coldestHourInFile(parser).get(1));
	}

	public static void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource("\\2013\\weather-2013-09-02.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println(averageTemperatureWithHighHumidityInFile(parser, 80));
	}

	public static void testFileWithColdestTemperature() {
		System.out.println(fileWithColdestTemperature());
	}

	public static void testLowestHumidityInFile() {
		FileResource fr = new FileResource("\\2014\\weather-2014-07-22.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println(lowestHumidityInFile(parser).get(13));

	}

	public static void testLowestHumidityInManyFiles() {
		System.out.println(lowestHumidityInManyFiles().get(3));
		System.out.println(lowestHumidityInManyFiles().get(13));
	}

	public static double averageTemperatureInFile(CSVParser parser) {
		ArrayList<CSVRecord> data = new ArrayList<CSVRecord>();
		try {
			data = (ArrayList<CSVRecord>) parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float temperatureSum = 0;
		for (CSVRecord record : data) {
			temperatureSum += Float.parseFloat(record.get(1));
		}
		return temperatureSum / data.size();
	}

	public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double temperatureSum = 0;
		int numberOfRecord = 0;
		try {
			for (CSVRecord record : parser.getRecords()) {
				if (Integer.parseInt(record.get(3)) >= value) {
					temperatureSum += Double.parseDouble(record.get(1));
					numberOfRecord++;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temperatureSum / numberOfRecord;
	}

	public static String fileWithColdestTemperature() {
		DirectoryResource dr = new DirectoryResource();
		float coldestTemperatureInDirectory = Float.MAX_VALUE;
		String result = "";
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			CSVParser parser = fr.getCSVParser();
			float currentColdestTemperature = Float.parseFloat(coldestHourInFile(parser).get(1));
			if (currentColdestTemperature < coldestTemperatureInDirectory) {
				coldestTemperatureInDirectory = currentColdestTemperature;
				result = file.getName();
			}
		}
		return Float.toString(coldestTemperatureInDirectory);
		//return result;
	}

	public static CSVRecord coldestHourInFile(CSVParser parser) {
		// ignore invalid -9999 temperature while calculating lowest temperature
		ArrayList<CSVRecord> records = new ArrayList<CSVRecord>();
		try {
			records = (ArrayList<CSVRecord>) parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVRecord result = records.get(0);
		float minTemperature = Integer.MAX_VALUE;
		for (CSVRecord record : records) {
			float currentTemperture = Float.parseFloat(record.get(1));
			if (currentTemperture < minTemperature && currentTemperture != -9999) {
				minTemperature = currentTemperture;
				result = record;
			}
		}
		//System.out.println(minTemperature);
		return result;
	}

	public static CSVRecord lowestHumidityInFile(CSVParser parser) {
		ArrayList<CSVRecord> records = new ArrayList<CSVRecord>();
		try {
			records = (ArrayList<CSVRecord>) parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVRecord result = records.get(0);
		float lowestHumidity = Integer.MAX_VALUE;
		for (CSVRecord record : records) {
			String humidity = record.get(3);

			if (!humidity.equals("N/A")) {

				float currentHumidity = Float.parseFloat(humidity);
				if (currentHumidity < lowestHumidity && currentHumidity != -9999) {
					lowestHumidity = currentHumidity;
					result = record;
				}
			}
		}
		// System.out.println(result.get(0));
		return result;
	}

	public static CSVRecord lowestHumidityInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		float lowestHumidityInManyFiles = Float.MAX_VALUE;
		CSVRecord result = null;
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			CSVParser parser = fr.getCSVParser();
			CSVRecord currentLowestHumidityRecord = lowestHumidityInFile(parser);
			float currentLowestHumidity = Float.parseFloat(currentLowestHumidityRecord.get(3));
			if (currentLowestHumidity < lowestHumidityInManyFiles) {
				lowestHumidityInManyFiles = currentLowestHumidity;
				result = currentLowestHumidityRecord;
			}
		}
		return result;

	}

}
