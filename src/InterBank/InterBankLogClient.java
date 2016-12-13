package InterBank;

import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Client.Client;

public class InterBankLogClient {

	public static void main(String[] args) {
		try {
	    	  if (args.length < 1) {
					System.out.println("Please use : java InterBankLogClient <interbank port>");
	    	  } else {
	    		  String interbankPort = args[0];
	    		  BankTransact interbank;
					
	    		  // create and initialize the ORB
	    		  String arg[] = {"-ORBInitialPort",interbankPort,"-ORBInitialHost","localhost"};
		          ORB orb = ORB.init(arg, null);
		          
		          org.omg.CORBA.Object objRef;
		          objRef = orb.resolve_initial_references("NameService");
		          NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	
		          objRef = ncRef.resolve_str("interbank");
		          interbank = BankTransactHelper.narrow(objRef);
		       
		          
		          while(true) {
		        	  Transact ts[] = interbank.passTransacts(0);
		        	  System.out.println("Log :");
		        	  for (Transact t : ts)
		        		  System.out.println(t);
		        	  Thread.sleep(5000);
		          }
	    	  }
	          
	      } catch (Exception e) {
	          System.err.println(e.getMessage());
	          e.printStackTrace();
	      }
		
	}

}
