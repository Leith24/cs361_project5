import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.lang.StringBuilder;
public class PasswordCrack{


	public static void main(String args[])throws Exception{
	
	
		
		long start = System.currentTimeMillis();		

		/*get user and dictionary data*/
    	ArrayList<String> words = getWords(args[0]);     
		ArrayList<ArrayList<String>> users = getUsers(args[1]);
	    for (int i=0; i < users.size();i++){
	    	System.out.println(users.get(i));
	    }

	    long end = System.currentTimeMillis();
	    System.out.println("runtime: " + (end - start) + "ms");
	}

	/*import and parse user data into double ArrayList*/
	public static ArrayList<ArrayList<String>> getUsers(String arg)throws Exception{
	
		ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();
		Scanner scan = new Scanner(new File(arg));
		while (scan.hasNextLine()){
		
			String line = scan.nextLine();	
            users.add(new ArrayList<String>(Arrays.asList(line.split(":"))));

		}
		return users;
	
	}

	/*scan dictionary of words into ArrayList*/
	public static ArrayList<String> getWords(String arg)throws Exception{
	
		Scanner scan = new Scanner(new File(arg));
		ArrayList<String> words = new ArrayList<String>();
		while(scan.hasNext()){
			words.add(scan.next());	
		}
		return words;
	}

	/*prepend symbol to string*/
	public static String prepend(String word){
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < 256; i++){
			build.append((char)i+word);
			System.out.print(build.toString() + ", ");
		}
		return null;
	}
    /*append symbol to string*/
	public static String append(String word){
	StringBuilder build = new StringBuilder();
		for (int i = 0; i < 256; i++){
			build.append(word+(char)i);
			System.out.print(build.toString() + ", ");
		}
		return null;
		
	}
	/*remove first character of string*/
	public static String remove_First(String word){
		return word.substring(1);
	}
	/*remove last character of string*/
	public static String remove_Last(String word){
		return word.substring(0, word.length() - 1);
	}
	/*reverse a string*/
	public static String reverse(String word){
		return new StringBuilder(word).reverse().toString();
	}

	/*duplicate a string*/
	public static String duplicate(String word){
		return word + word;
	}
	/*capitilze a string*/
	public static String capitalize(String word){

		return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
	}
	/*inverse of capitalize*/
	public static String ncapitalize(String word){
		return word.substring(0,1).toLowerCase()+word.substring(1).toUpperCase();
	}
	/*create mirror image of string and returns reults in array list*/
	public static ArrayList<String> reflect(String word){
		ArrayList<String> results=new ArrayList<String>();
		results.add(reverse(word)+word);
		results.add(word+reverse(word));
		return results;
	}
	/*creates two toggles and returns results in arrayList*/
	public static ArrayList<String> toggle(String word){
		ArrayList<String> results = new ArrayList<String>();
		for (int j = 0; j < 2;j++){
			StringBuilder build = new StringBuilder();
			
			for (int i = 0 ; i < word.length(); i++){
				if (j == 0){
					if(i % 2 == 0)
						build.append(word.substring(i,i+1).toUpperCase());
					else 
						build.append(word.substring(i,i+1).toLowerCase());
				}
				if (j == 1){
					if(i % 2 == 0)
						build.append(word.substring(i,i+1).toLowerCase());
					else 
						build.append(word.substring(i,i+1).toUpperCase());
				}
			}
			results.add(build.toString());

		}
		return results;
	}
}
