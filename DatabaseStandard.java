import java.util.HashMap;

public class DatabaseStandard implements DatabaseInterface{
	HashMap<String, String> database = new HashMap();
	


	public String save(String plainPassword, String encryptedPassword){
		// Stores plainPassword and corresponding encryptedPassword in a map.
	 	// if there was a value associated with this key, it is replaced, 
	 	// and previous value returned; otherwise, null is returned
	 	// The key is the encryptedPassword the value is the plainPassword

		String value = database.get(encryptedPassword);
		database.put(encryptedPassword,plainPassword);
		
		return value;



	} 
	 

	public String decrypt(String encryptedPassword){ 
		// returns plain password corresponding to encrypted password
		String pass = database.get(encryptedPassword);
		if(pass==null) pass="";
		
		return pass;
	}
	
    public int size(){ 
    	// returns the number of password pairs stored in the database
    	return database.size();
    }
	
    public void printStatistics(){ // print statistics based on type of Database
    	System.out.println("Size is "+size()+" passwords");

    } 

}