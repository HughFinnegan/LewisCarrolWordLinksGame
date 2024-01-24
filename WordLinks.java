

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordLinks {
	String isValid;

	public ArrayList<String> readDictionary(BufferedReader br)
	{	

		ArrayList<String> words = new ArrayList<String>();
		try {
			/*Scanner s = new Scanner(new File("C:\\Users\\hughf\\OneDrive\\"
					+ "Documents\\College\\Programming I\\src\\College\\directory\\words.txt"));*/
			Scanner s = new Scanner(new File("words.txt"));

			while (s.hasNext()){
				words.add(s.next());
			}
			s.close();


		}
		catch(Exception e)
		{
			System.err.println(e);
		}   


		return words;
	}

	public String[] readWordList(String oneNA)
	{
		String[] givenStringSplit = oneNA.split(",");
		return givenStringSplit;
	}

	public boolean isUniqueList(String[] one)
	{ 	
		boolean yesOrNo = true;
	
		for(int i=0;i<one.length;i++)
		{
			String baseWord = one[i];
			for(int j=i+1;j<one.length;j++)
			{
				String checkWord = one[j];
				if(checkWord==baseWord) {
					yesOrNo = false;
					break;
				}
			}
			if(yesOrNo == false) {
				i = one.length -1;
			}
		}
		return yesOrNo;		
	}

	public boolean isEnglishWord(String[] one, String[] two) 
	{	
		
		for(int i=0;i<one.length;i++)
		{	boolean yesOrNo = false;
			String baseWord = one[i];
			for(int j=0;j<two.length;j++)
			{	
				yesOrNo = false;
				String checkWord = one[j];
				if(checkWord==baseWord) {
					yesOrNo = true;
					break;}
			}
			if(yesOrNo == false) {
				return false;
			}
		}
		return true;
	}
	
	/*public boolean isEnglishWord(String[] one, String[] two) 
	{
		static int binarySearch(String[] arr, String x)
	    {
	        int l = 0, r = arr.length - 1;
	        while (l <= r) {
	            int m = l + (r - l) / 2;
	 
	            int res = x.compareTo(arr[m]);
	 
	            // Check if x is present at mid
	            if (res == 0)
	                return m;
	 
	            // If x greater, ignore left half
	            if (res > 0)
	                l = m + 1;
	 
	            // If x is smaller, ignore right half
	            else
	                r = m - 1;
	        }
	 
	        return -1;
	    }
	}*/

	public boolean isDifferentByOne(String[] one)
	{
		for(int i = 1; i<one.length;i++) {
			String lengthCheck = one[0];
			String alternatingWord = one[i];
			if(lengthCheck.length()!= alternatingWord.length()) {
				return false;
			}
		}
		
		int count=0;
		
		for(int i = 1; i<one.length;i++) {
			String firstWord = one[i-1];
			String alternatingWord = one[i];
			String[] firstWordArray = firstWord.split("");
			String[] alternatingWordArray = alternatingWord.split("");
			
			for(int t = 0;t<firstWordArray.length;t++) {
				String letterOneCheck = firstWordArray[t];
				String letterTwoCheck = alternatingWordArray[t];
				if(!letterOneCheck.equals(letterTwoCheck)) {
					count = count +1;
				}
				if(count>1) {
					return false;
				}
			}
			count =0;
		}
		
		return true;
	}

	public void isWordChain(String[] one, String[] two)
	{
		boolean englishWord=  isEnglishWord(two, one);
		boolean offByOne = isDifferentByOne(two);
		
		if(englishWord == true && offByOne == true) {
			isValid = "Valid chain of words from Lewis Carroll's word-links game.";
		}
		else {
			isValid = "Not a valid chain of words from Lewis Carroll's word-links game";
		}
		
	}

	public static void main(String[] args) {
		WordLinks w = new WordLinks();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a comma separated list of words (or an empty list to quit):");
		
		StringBuilder sb = new StringBuilder();

		while (scanner.hasNextLine()) {
		    String line = scanner.nextLine();
		    if (line.equals("")) {
		        break;
		    }
		    sb.append(line);
		}

		String stringOfWords = sb.toString();
		
		
		try {
			String noWhitespace = stringOfWords.replaceAll(" ", "");
			System.out.println(stringOfWords);
			
			/*FileInputStream p = new FileInputStream("C:\\Users\\hughf\\OneDrive"
					+ "\\Documents\\College\\Programming I\\src\\College\\directory\\words.txt");
			FileReader fr = new FileReader("C:\\Users\\hughf\\OneDrive\\Documents\\College"
					+ "\\Programming I\\src\\College\\directory\\words.txt");    */
			
			FileInputStream p = new FileInputStream("words.txt");
			FileReader fr = new FileReader("words.txt");    
			
			BufferedReader brIF = new BufferedReader(fr);
			ArrayList<String> one = w.readDictionary(brIF);
			String[] oneNotList =  new String[one.size()];
			for(int l=0;l<one.size();l++) {
				String first = one.get(l);
				oneNotList[l] = first;
				
			}
			String[] userWords = w.readWordList(noWhitespace);
			w.isWordChain(oneNotList, userWords );
			
			System.out.println(w.isValid);
			
			brIF.close();    
			fr.close(); 
		}
		catch(Exception e)
		{
			System.err.println(e);
		}   

		


		
	}

}

