import java.util.ArrayList; 
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.util.Hashtable;
public class PasswordCrack{

	public static void main(String args[])throws Exception{
	
	
		
		long start = System.currentTimeMillis();		
		
		/*get user and dictionary data*/
    	ArrayList<String> words = getWords(args[0]);     
		ArrayList<ArrayList<String>> users = getUsers(args[1]);
		jcrypt jj = new jcrypt();
		int number_cracked=0;
		/*CRACK PASSWORDS*/ 
		/*iterating through the user list*/
		for (int i = users.size() - 1; i >=0 ;i--){
			boolean cracked = false;

			System.out.println("\n"+  users.get(i));	
			/*get password w/o salt*/
			String encrypted_password = users.get(i).get(1).substring(2); 
			/*get salt*/
			String salt = users.get(i).get(1).substring(0,2);

			/*add first and last name to list of words*/
            String firstlast_name=users.get(i).get(4);
            String[] names = firstlast_name.split("[ ]");
            words.add(names[0]);
            words.add(names[1]);

			/*for debugging purposes*/
			System.out.println("attempting to crack: "+ users.get(i).get(1));

			/*attempt to crack users password*/
			int old = number_cracked;
			number_cracked+=crack(cracked, encrypted_password, salt, words, jj, users);
			if (old != number_cracked)
				users.remove(i);
			
		}

		try_combinations(users, jj, words);

		/*print out runtime*/
	    long end = System.currentTimeMillis();
	    System.out.println("\nruntime: " + (end - start)*1.0/1000 + " seconds");
	    System.out.println("Total number passwords: " + 20);
		System.out.println("Passwords cracked: " + number_cracked);
		System.out.println("Passwords failed: " + (20 - number_cracked)+"\n");

		System.out.println("SIZE: "+users.size());
		System.out.println("USERS: " + users);
	}

	public static void try_combinations(ArrayList<ArrayList<String>> users, jcrypt jj, ArrayList<String> words){

		Hashtable<String, Integer> passwords_hash = new Hashtable<String, Integer>();
		/*insert passwords into hashtable*/
		for (int i = 0; i < users.size(); i++){
			passwords_hash.put(users.get(i).get(1), 1);
		}

		int[] combinations = new int[9];
		for (int i = 0; i < combinations.length; i++){

			for (int j = 0; j < combinations.length;j++){
				combinations[i]=1;
				combinations[j]=1;
				for (int w_index = 0; w_index<words.size(); w_index++){

					String word = crack_again(combinations, words.get(w_index));
					
					combinations[i]=1;
					combinations[j]=1;
				}

			}
		
		}		

	}

	public static String crack_again(int[] combinations, String word){

		if (combinations[0] == 1){
			word = remove_First(word);
			combinations[0]=0;
			crack_again(combinations, word);
		} 
		else if (combinations[1]==1){
			word = remove_Last(word);
			combinations[1]=0;
			crack_again(combinations, word);
		}
		else if (combinations[2]==1){
			word=reverse(word);
			combinations[2]==0;
			crack_again(combinations, word);
		}
		else if (combinations[3]==1){
			word=duplicate(word);
			combinations[3]=0;
			crack_again(combinations, word);
		}
		else if (combinations[4]==1){
			word=reflect(word);
			combinations[4]=0;
			crack_again(combinations, word);
		}
		else if (combinations[5]==1){
			word=word.toUpperCase();
			combinations[5]=0;
			crack_again(combinations, word);
		}
		else if (combinations[6]==1){
			word=word.toLowerCase();
			combinations[6]=0;
			crack_again(combinations, word);
		}
		else if (combinations[7]==1){
			word=capitalize(word);
			combinations[7]=0;
			crack_again(combinations, word);
		}
		else if (combinations[8]==1){
			word=ncapitalize(word);
			combinations[8]=0;
			crack_again(combinations, word);
		} else {

			return word;
		}



	}
	
	/*iterate thorugh the dication and find the password*/
	public static int crack(boolean cracked, String encrypted_password, String salt,
		ArrayList<String> words, jcrypt jj, ArrayList<ArrayList<String>> users){
	
	    int w_index = 0;
		String word="", ans="";
		/*iterating through dicationary and find password*/
	    for (w_index=0; w_index<words.size();w_index++){
		    word=words.get(w_index);
		    ans=word;
			String encryption=jj.crypt(salt, word);	
			/*check any word unaltered is password*/
			if (encryption.equals(salt + encrypted_password)){
		        cracked = true;
				break;
			}
			/*check if password is word prepended with symbol*/
			if ((ans=prepend(word, encrypted_password, salt, jj))!=null){
				cracked = true;
				break;
			}
			/*check if password is word appended with symbol*/
			if ((ans=append(word, encrypted_password, salt, jj))!=null){
				cracked = true;
				break;
			}
			/*check if password is word with first letter removed*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=remove_First(word)))){
				cracked = true;
				break;
			}
			/*check if password is word with last letter removed*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=remove_Last(word)))){
				cracked = true;
				break;
			}
			/*check if password is word reversed*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=reverse(word)))){
				cracked = true;
				break;
			}
			/*check if password is word duplicated*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=duplicate(word)))){
				cracked = true;
				break;
			}
			/*check if password is word reflected against itself*/
			if((ans=reflect(word, encrypted_password, salt, jj))!=null){
				cracked = true;
				break;
			}
			/*check if password is word with all uppercase*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=word.toUpperCase()))){
				cracked = true;
				break;
			}
			/*check if password is word all lowercased*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=word.toLowerCase()))){
				cracked = true;
				break;
			}
			/*check if password is word captilalized*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=capitalize(word)))){
				cracked = true;
				break;
			}
			/*check if password is word ncap*/
			if((salt+encrypted_password).equals(jj.crypt(salt, ans=ncapitalize(word)))){
				cracked = true;
				break;
			}
			if((ans=toggle(word, encrypted_password, salt, jj))!=null){
				cracked = true;
				break;
			}




			
		}	
		
		results(cracked, ans);
		return cracked?1:0;
		
	}


	/*method that prints results of attempt at cracking passwrod*/
	public static void results(boolean cracked, String word){
    	
		if (cracked)
			System.out.println("\033[32mfound password\033[0m: "+word);	
	    else
			System.out.println("\033[31mFAILED\033[0m");
			
	
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
	public static String prepend(String word, String encrypted_password, String salt,
		jcrypt jj){
		StringBuilder build = new StringBuilder();
		for (int i = 32; i < 127; i++){
			build.append((char)i+word);
			if ((salt+encrypted_password).equals(jj.crypt(salt, build.toString()))){
				return build.toString();
			}
			build = new StringBuilder();
		}
		return null;
	}
    /*append symbol to string*/
	public static String append(String word, String encrypted_password, String salt,
		jcrypt jj){
	StringBuilder build = new StringBuilder();
		for (int i = 32; i < 127; i++){
			build.append(word+(char)i);
			if ((salt+encrypted_password).equals(jj.crypt(salt, build.toString()))){
				return build.toString();
			}
			build = new StringBuilder();
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
	public static String reflect(String word, String encrypted_password, String salt,
		jcrypt jj){
		String ans="";
		if((salt+encrypted_password).equals(jj.crypt(salt,ans=reverse(word)+word)))
			return ans;
		if((salt+encrypted_password).equals(jj.crypt(salt,ans=word+reverse(word))))
			return ans;

		return null;
	}
	/*creates two toggles and returns results in arrayList*/
	public static String toggle(String word, String encrypted_password, String salt,
		jcrypt jj){
		ArrayList<String> results = new ArrayList<String>();
		String ans="";
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
		if((salt+encrypted_password).equals(jj.crypt(salt,ans=results.get(0))))
			return ans;
		else if((salt+encrypted_password).equals(jj.crypt(salt,ans=results.get(1))))
			return ans;
		return null;
	}
}
