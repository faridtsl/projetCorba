package ServeurBancaire;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BankServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try{
		      // create and initialize the ORB
		      ORB orb = ORB.init(args, null);
			  BankCustomerServant servant = new BankCustomerServant(orb);

		      // get reference to rootpoa & activate the POAManager
		      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootpoa.the_POAManager().activate();
			  Policy[] persistent = new Policy[1];
			  persistentPolicy[0] = rootpoa.create_lifespan_policy(		  

		      // create servant and register it with the ORB
		      BankCustomerImpl helloImpl = new BankCustomerImpl(orb,1);
		      
		      // get object reference from the servant
		      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
		      BankCustomer href = BankCustomerHelper.narrow(ref);
		          
		      // get the root naming context
		      // NameService invokes the name service
		      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		      // Use NamingContextExt which is part of the Interoperable
		      // Naming Service (INS) specification.
		      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		      // bind the Object Reference in Naming
		      String name = "Bank1";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);

		      System.out.println("BankServer ready and waiting ...");

		      // wait for invocations from clients
		      orb.run();
		    } 
		        
		      catch (Exception e) {
		        System.err.println("ERROR: " + e);
		        e.printStackTrace(System.out);
		      }
		          
		      System.out.println("BankServer Exiting ...");
	}

}