import java.util.ArrayList ;
import java.io.UnsupportedEncodingException;

public class PasswordCracker {

	

	void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database){
		// receives list of passwords and populates database with entries consisting
		// of (key,value) pairs where the value is the password and the key is the
		// encrypted password (encrypted using Sha1)
		// in addition to passwords in commonPasswords list, this class is 
		// responsible to add mutated passwords according to rules 1-5

		for (int i=0; i< commonPasswords.size() ; i++ ) {
			String pass = commonPasswords.get(i);
			try {pass = Sha1.hash(pass);}
			catch(UnsupportedEncodingException e){System.out.println("encryption error");}
			database.save(commonPasswords.get(i), pass);
			
			int total=32;
			for(int k=0; k<total; k++){
				char[] characters = commonPasswords.get(i).toCharArray();
				
				for (int j =0; j< characters.length  ; j++ ) {



					if (j ==0 && (k%2==1)) characters[j] =  java.lang.Character.toUpperCase(characters[j]);
					if(characters[j] == 'a' && ((k/2)%2==1)) characters[j] = '@';
					if(characters[j] == 'e' && ((k/4)%2==1)) characters[j] = '3';
					if(characters[j] == 'i' && ((k/8)%2==1)) characters[j] = '1';
				}
				String newPass = new String(characters);
				if(k>15) newPass = newPass + "2018";

				try {pass = Sha1.hash(newPass);}
				catch(UnsupportedEncodingException e){System.out.println("encryption error");}
				database.save(newPass, pass);
			}
			
		}
	}

	String crackPassword(String encryptedPassword, DatabaseInterface database) {
	//uses database to crack encrypted password, returning the original password 
		String password = database.decrypt(encryptedPassword);
		return password;

	}

}