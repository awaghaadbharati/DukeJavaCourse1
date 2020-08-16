import edu.duke.*;

public class FAPart4 {
	public static void main(String[] args) {
		URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
		for (String line : urlResource.lines()) { 
			String str = "youtube.com";
			int urlEndIndex, urlStartIndex;
			int strStartIndex = line.toLowerCase().indexOf(str);
			if (strStartIndex != -1) {
				urlStartIndex = line.substring(0, strStartIndex).lastIndexOf("\"");
				urlEndIndex = line.indexOf('\"', strStartIndex);
				System.out.println(line.substring(urlStartIndex, urlEndIndex + 1));
			}
		}

	}
}
