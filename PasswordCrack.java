import java.util.ArrayList; 
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.util.Hashtable;
import java.util.*;
public class PasswordCrack{
	public static ArrayList<String> cracked_users = new ArrayList<String>();
	public static Hashtable<String, Integer> passwords_hash = new Hashtable<String, Integer>();
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

			System.out.println();	
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

		number_cracked=try_combinations(users, jj, words, number_cracked);

		/*print out runtime*/
	    long end = System.currentTimeMillis();
	    System.out.println("\nWe can crack " + number_cracked+" cases. \nList of cracked:");
	    for(int i = 0; i<cracked_users.size();i++){
	        System.out.println(cracked_users.get(i));
	    }
	    System.out.println("\nWe can not crack " +(20- number_cracked)+" cases.\nList of uncracked:");
	   
	    Iterator<Map.Entry<String, Integer>> it = passwords_hash.entrySet().iterator();
	    while(it.hasNext()){
	    	Map.Entry<String,Integer> entry = it.next();
	    	System.out.println(entry.getKey());
	    }
	    
	    System.out.println("\nWe can crack "+ number_cracked+" cases in " + (end - start)*1.0/1000 + " seconds");
		}

	public static int try_combinations(ArrayList<ArrayList<String>> users, jcrypt jj, ArrayList<String> words, int number_cracked){

		
		/*insert passwords into hashtable*/
		for (int i = 0; i < users.size(); i++){
			passwords_hash.put(users.get(i).get(1), 1);
		}

		int[] combinations = new int[8];
		
			for (int i = 0; i < combinations.length ; i++){

				for (int j = 0; j < combinations.length ;j++){
					combinations[i]=1;
				
					combinations[j]=1;

					for(int prefix = 0; prefix < users.size(); prefix++){
						String salt_temp = users.get(prefix).get(1).substring(0,2);
						for (int w_index = 0; w_index<words.size(); w_index++){
							String word = crack_again(combinations, words.get(w_index), i , j);
							
							String test_enc=jj.crypt(salt_temp, word);
							if (passwords_hash.containsKey(test_enc)){
								passwords_hash.remove(test_enc);
								cracked_users.add(word);
								System.out.println("\nattemping to crack: " + users.get(prefix).get(1));
								System.out.println("\033[32mfound password\033[0m: "+word);
								number_cracked++;
							}

							//System.out.println(word);

							combinations[i]=1;
							combinations[j]=1;
					
							
						}
					}

				}

			
			}	
		
		return number_cracked;	

	}

	public static String crack_again(int[] combinations, String word, int i , int j){

		if (combinations[0] == 1){
			word = remove_First(word);
			combinations[0]=0;
			word=crack_again(combinations, word,i,j);
			
		} 
		else if (combinations[1]==1){
			word = remove_Last(word);
			combinations[1]=0;
			word=crack_again(combinations, word,i,j);
		}
		else if (combinations[2]==1){
			if(combinations[0]==1){
				word=remove_First(word);
				combinations[0]=0;
			}
			else if(combinations[1]==1){
				word=remove_Last(word);
				combinations[1]=0;
			}
			else if(combinations[3]==1){
				word=duplicate(word);
				combinations[3]=0;
			}
			else if(combinations[4]==1){
				combinations[4]=0;
				word=word.toUpperCase();
			}
			else if(combinations[5]==1){
				combinations[5]=0;
				word=word.toLowerCase();
			}
			else if(combinations[6]==1){
				word=capitalize(word);
				combinations[6]=0;
			}
			else if(combinations[7]==1){
				word=ncapitalize(word);
				combinations[7]=0;
			}
			word=reverse(word);
			combinations[2]=0;
		    word=crack_again(combinations, word,i,j);
		}


		else if (combinations[3]==1){
			word=duplicate(word);
			combinations[3]=0;
			word=crack_again(combinations, word,i,j);
		}
		else if (combinations[4]==1){
			word=word.toUpperCase();
			combinations[4]=0;
			word=crack_again(combinations, word,i,j);
		}
		else if (combinations[5]==1){
			word=word.toLowerCase();
			combinations[5]=0;
			word=crack_again(combinations, word,i,j);
		}
		else if (combinations[6]==1){

			word=capitalize(word);
			combinations[6]=0;
			assert (!word.equals("Preset")) : "found it";
			word=crack_again(combinations, word,i,j);

		}
		else if (combinations[7]==1){
			word=ncapitalize(word);
			combinations[7]=0;
			word=crack_again(combinations, word,i,j);
		}
		return word;
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
    		
		if (cracked){	
			cracked_users.add(word);
			System.out.println("\033[32mfound password\033[0m: "+word);	
		}
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
		if(word.length()>1)
		return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
		else
		return word.toUpperCase();
	}
	/*inverse of capitalize*/
	public static String ncapitalize(String word){
		if(word.length()>1)
			return word.substring(0,1).toLowerCase()+word.substring(1).toUpperCase();
		else
			return word.toLowerCase();
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
