import java.util.*;

public class DatabaseMine implements DatabaseInterface{
	int n;
	int size;
	int displacements;
	int probes;
	
	private Entry[] table;

	private class Entry{
		public String key;
		public String val;
		public Entry(String k, String v){
			key = k;
			val = v;
		}
		
	}


	public DatabaseMine(){
		
		n = 127051;
		table = new Entry[n];
	}

	public DatabaseMine(int n){
		table = new Entry[n];
	}


	int hashFunction(String key){
		int address = key.hashCode() %n;
		return (address>=0)?address:(address+n);
	}

	


	public String save(String plainPassword, String encryptedPassword){ 
	 // Stores plainPassword and corresponding encryptedPassword in a map.
	 // if there was a value associated with this key, it is replaced, 
	 // and previous value returned; otherwise, null is returned
	 // The key is the encryptedPassword the value is the plainPassword
		
			String oldPass = get(encryptedPassword);
			put(encryptedPassword,plainPassword);
			return oldPass;
		

	}

	public String decrypt(String encryptedPassword){ 
	// returns plain password corresponding to encrypted password
		String pass = get(encryptedPassword);
		if(pass==null)pass="";
		return pass;
	}
	
    public int size(){ return size;} 
    // returns the number of password pairs stored in the database
	
    public void printStatistics(){  // print statistics based on type of Database
    	System.out.println("Size is "+size+" passwords");
    	System.out.println("Initial Number of Indexes when Created "+n);
    	System.out.println("Load Factor "+((float)size/(float)n));
    	System.out.println("Average number of Probes is "+ ((float)probes/(float)size));
    	System.out.println("Number of displacements (due to collisions) "+ displacements);

 
    }


    public String get(String key){
    	int probe = hashFunction(key);
		while(table[probe] != null && !table[probe].key.equals(key) ){
			probe = (probe+1)%n;
		}
    	if(table[probe] == null) return null;
    	else return table[probe].val;

    }


    public boolean put(String key, String val){
    	int probe = hashFunction(key);
    	int initialPos = probe;
    	int shifts = 1;
    	
		while(table[probe] != null && !table[probe].key.equals(key) ){
			shifts++;
			probe = (probe+1)%n;
		}
    
    	if(table[probe] == null){
    		table[probe] = new Entry(key,val);
    		probes += shifts;
    		size++;
    		if(probe != initialPos) displacements++;
    		return true;
    	} else{
    		table[probe].val = val;
    		return true;
    	}
    	
    }


}