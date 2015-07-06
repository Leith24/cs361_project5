import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class PasswordCrack{


	public static void main(String args[])throws Exception{
	
	
		//currentTimeMillis()
		long start = System.currentTimeMillis();			
    	ArrayList<String> words = getWords(args[0]);     
		ArrayList<ArrayList<String>> users = getUsers(args[1]);
		System.out.println(users);
	    
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
}
