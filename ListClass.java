import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;


// Superclass for White and Black Lists
public class ListClass{ 
	private File file; // Instantiate in the constructor

	private void createFile(File file){
		if(!file.exists()){
			try{
				if(Challenge.verbose)
					System.out.println("Creating File");						
				file.createNewFile();
			}catch(IOException e){
				if(Challenge.verbose)
					System.out.println("Creating File problem");		
				System.exit(0);
			}
		}
		else {
			if(Challenge.verbose)
				System.out.println("Opening "+file);

		}
	} 
	// Constructor 
	public ListClass(String name) {
		file = new File(name);
		createFile(file);

	}
	// Garbage-Collected language, so no need for a destructor method

	public boolean verify(String url){
        String line;
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        while((line = br.readLine()) != null) {
	            if((line.matches(url))) {
	           		if(Challenge.verbose) 	
	                	System.out.println("Achei verify");
	                return true;
	            }
	        }
	    }catch (IOException e){
	        System.out.println(e);
	    }
	    return false;
	}

	public void add(String url){
		if(verify(url)){
				if(Challenge.verbose)
					System.out.println(url+" Already exists in file");
		}else{
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
				bw.write(url);
				bw.newLine();
				if(Challenge.verbose)
					System.out.println("New URL inserted");
			}catch(IOException e){
					System.out.println("URL insertion problems");		
			}
		//"finally" statement to close 'bw' is not needed since 
		// try-with-resources is used 
		}
	}

	public boolean remove(String url){
		File temp = new File("temp");
		boolean flag = false;
		createFile(temp);
        String line;
		try( BufferedReader br = new BufferedReader(new FileReader(file));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(temp,true)); 
			) {
	        while((line = br.readLine()) != null) {
	            if((line.matches(url))) {
					if(Challenge.verbose)	            	
		                //System.out.println("Achei remove");
	                flag = true;	
	            }else{
	            	bw.write(line);
					bw.newLine();
	            }
	        }
	    }catch (IOException e){
	        System.out.println(e);
	    }
	    // Colocar explicação do pq nao precisa do finally  		
 		
 		if(!file.delete()){
        	System.out.println("Could not delete file"); 			  			
        	System.exit(0); 		
 		}
 		if(!temp.renameTo(file)){
        	System.out.println("Could not rename file"); 			  			
        	System.exit(0); 		
 		}
		return flag;
	}
	public void show(){
		String url = null;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			while((url=br.readLine())!= null){
				System.out.println(url);
			}
		}catch(Exception e){
			System.out.println("Error Reading file"+ e);
		}
	}
}