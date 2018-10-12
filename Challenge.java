
public class Challenge{
	static String function;
	static String parameters;
	static final boolean verbose = false;

	public static void main(String[] args){
		ListClass whiteList = new ListClass("whiteList.txt");
		ListClass blackList = new ListClass("blackList.txt");			

		try{
			parseArguments(args);

		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		switch(function){
			case "show-whitelist":
				whiteList.show();
				break;
			case "show-blacklist":
				blackList.show();	
				break;
			case "verify":
				if(whiteList.verify(parameters)){
					System.out.println("safe");
				}else if(blackList.verify(parameters)){
					System.out.println("unsafe");					
				}
				else
					System.out.println("unknown"); 
				break;
			case "add-whitelist":
				whiteList.add(parameters);
				break;
			case "add-blacklist":
				blackList.add(parameters);
				break;
			case "remove-whitelist":
				if(!whiteList.remove(parameters)){
					if(verbose)
						System.out.println( parameters+" dont exist in list");
				}
				break;
			case "remove-blacklist":
				if(!blackList.remove(parameters)){
					if(verbose)
						System.out.println( parameters+" dont exist in list");
				}
				break;

			default:
				System.out.println("Not Available Function");
		}
	}
	public static void parseArguments(String[] args ){		

		if(args.length == 0){
			throw new IllegalArgumentException("Arguments needed.");
		}
		if(args.length == 2){
			function = args[0];
			parameters = args[1];
		}
		else if (args.length > 2){
			throw new IllegalArgumentException("Not supported parameters amount");
		}
		else{
			function = args[0];	
			parameters = null;
		}
	}

}